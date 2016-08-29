package com.poslovna.informatika.frames;

import com.poslovna.informatika.configuration.ApplicationContextProvider;
import com.poslovna.informatika.entities.DnevnoStanjeRacuna;
import com.poslovna.informatika.entities.PravnoLice;
import com.poslovna.informatika.entities.PrenosIzvoda;
import com.poslovna.informatika.entities.RacunPravnogLica;
import com.poslovna.informatika.formatters.DateLabelFormatter;
import com.poslovna.informatika.service.PravnoLiceService;
import com.poslovna.informatika.service.PrenosIzvodaService;
import com.poslovna.informatika.service.RacunPravnogLicaService;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Date;
import java.util.Properties;

public class GenerisanjePrenosaIzvodaFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private PravnoLiceService pravnoLiceService = (PravnoLiceService) ApplicationContextProvider.getContext().getBean("pravnoLiceService");
    private RacunPravnogLicaService racunPravnogLicaService = (RacunPravnogLicaService) ApplicationContextProvider.getContext().getBean("racunPravnogLicaService");
    private PrenosIzvodaService piService = (PrenosIzvodaService) ApplicationContextProvider.getContext().getBean("prenosIzvodaService");
    private JComboBox<PravnoLice> pravnoLiceJComboBox;
    private JComboBox<RacunPravnogLica> racunPravnogLicaJComboBox;
    private JPanel jPanel;
    private JPanel smallPanel;
    private JDatePickerImpl datumIzvoda;
    private JButton generisi;
    private PrenosIzvoda pi;

    public GenerisanjePrenosaIzvodaFrame() {
        setTitle("Generisanje prenosa izvoda");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(new Dimension(800, 600));
        setLocationRelativeTo(null);
        jPanel = new JPanel(new MigLayout("fillx"));
        smallPanel = new JPanel(new MigLayout("fillx"));
        smallPanel.setSize(500, 500);
        jPanel.add(smallPanel, BorderLayout.SOUTH);
        init();
        listeners();
        add(jPanel);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                if (JOptionPane.showConfirmDialog(null, "Da li ste sigurni da želite da zatvorite ovaj prozor?", "Potrvrda", JOptionPane.YES_NO_OPTION)
                        == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });
    }

    private void init() {
        jPanel.add(new JLabel("=== PRENOS IZVODA  ============================================================================"), "span 3, wrap");

        jPanel.add(new JLabel("Pravno lice"));
        pravnoLiceJComboBox = new JComboBox<PravnoLice>();
        for (PravnoLice pl : pravnoLiceService.findAll()) {
            pravnoLiceJComboBox.addItem(pl);
        }
        jPanel.add(pravnoLiceJComboBox, "wrap");

        jPanel.add(new JLabel("Racun pravnog lica"));
        racunPravnogLicaJComboBox = new JComboBox<RacunPravnogLica>();
        jPanel.add(racunPravnogLicaJComboBox, "wrap");
        
        //Datum izvoda
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datumIzvoda = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        jPanel.add(new JLabel("Datum naloga"));
        jPanel.add(datumIzvoda, "wrap");
        
        //Button generisi
        generisi = new JButton("Generisi izvod");
        jPanel.add(generisi, "wrap");
        
       
        generisi.addActionListener(new ActionListener(){
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				pi = new PrenosIzvoda();
				RacunPravnogLica rpl = (RacunPravnogLica) racunPravnogLicaJComboBox.getSelectedItem();
				Date dateIzvoda = (Date) datumIzvoda.getModel().getValue();
				int brojPromenaUKorist = 0;
				int brojPromenaNaTeret = 0;
				double ukupnoUKorist = 0.0;
				double ukupnoNaTeret = 0.0;
				int brojPogresnihUKorist = 0;
				int brojPogresnihNaTeret = 0;
				if(rpl == null){
					JOptionPane.showMessageDialog(null, "Niste selektovali racun pravnog lica.");
				}else if(dateIzvoda == null){
					JOptionPane.showMessageDialog(null, "Selektujte datum za koji zelite presek stanja.");
				}else{
					for(DnevnoStanjeRacuna dsr : rpl.getDnevnaStanjaRacuna())
					{
						if((dsr.getDatumPrometa().getDate() == dateIzvoda.getDate()) && (dsr.getDatumPrometa().getMonth() == dateIzvoda.getMonth()) && (dsr.getDatumPrometa().getYear() == dateIzvoda.getYear())){
							if(dsr.getPrometUKorist() > 0){
								brojPromenaUKorist ++;
								ukupnoUKorist += dsr.getPrometUKorist();
							}
							if(dsr.getPrometNaTeret() > 0){
								brojPromenaNaTeret ++;
								ukupnoNaTeret += dsr.getPrometNaTeret();
							}
						}
					}
					pi.setBrojPogresnihStavkiNaTeret(brojPogresnihNaTeret);
					pi.setBrojPogresnihStavkiUKorist(brojPogresnihUKorist);
					pi.setBrojPromenaNaTeret(brojPromenaNaTeret);
					pi.setBrojPromenaUKorist(brojPromenaUKorist);
					pi.setUkupnoNaTeret(ukupnoNaTeret);
					pi.setUkupnoUKorist(ukupnoUKorist);
					pi.setDatumNaloga(dateIzvoda);
					pi = piService.save(pi);
					pi.setBrojPreseka(pi.getId());
					piService.save(pi);
					
					PravnoLice pl = (PravnoLice) pravnoLiceJComboBox.getSelectedItem();
					pl.getPrenosiIzvoda().add(pi);
					pravnoLiceService.save(pl);
					
					smallPanel.removeAll();
					smallPanel.revalidate();
					smallPanel.repaint();
					
					smallPanel.add(new JLabel("====== Nalog za prenos izvoda ========="), "span 3, wrap");
					
					JTextField jPromeneUKorist = new JTextField(pi.getBrojPromenaUKorist().toString());
					jPromeneUKorist.setEnabled(false);
					smallPanel.add(new JLabel("Broj promena u korist:"));
					smallPanel.add(jPromeneUKorist, "wrap");
					
					JTextField jUkupnoUKorist = new JTextField(String.valueOf(pi.getUkupnoUKorist()));
					jUkupnoUKorist.setEnabled(false);
					smallPanel.add(new JLabel("Ukupno u korist:"));
					smallPanel.add(jUkupnoUKorist, "wrap");
					
					JTextField jPromeneNaTeret = new JTextField(pi.getBrojPromenaNaTeret().toString());
					jPromeneNaTeret.setEnabled(false);
					smallPanel.add(new JLabel("Broj promena na teret:"));
					smallPanel.add(jPromeneNaTeret, "wrap");
					
					JTextField jUkupnoNaTeret = new JTextField(String.valueOf(pi.getUkupnoNaTeret()));
					jUkupnoNaTeret.setEnabled(false);
					smallPanel.add(new JLabel("Ukupno na teret:"));
					smallPanel.add(jUkupnoNaTeret, "wrap");
					
					JTextField jBrojPogresnihUKorist = new JTextField(pi.getBrojPogresnihStavkiUKorist().toString());
					jBrojPogresnihUKorist.setEnabled(false);
					smallPanel.add(new JLabel("Broj pogresnih stavki u korist:"));
					smallPanel.add(jBrojPogresnihUKorist, "wrap");
					
					JTextField jBrojPogresnihNaTeret = new JTextField(pi.getBrojPogresnihStavkiNaTeret().toString());
					jBrojPogresnihNaTeret.setEnabled(false);
					smallPanel.add(new JLabel("Broj pogresnih stavki na teret:"));
					smallPanel.add(jBrojPogresnihNaTeret, "wrap");
					
					JButton generisiXML = new JButton("Generisi XML");
					smallPanel.add(generisiXML);
					
					smallPanel.repaint();
					smallPanel.revalidate();
					
					jPanel.repaint();
					jPanel.revalidate();

					generisiXML.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							try{
								JAXBContext context = JAXBContext.newInstance(PrenosIzvoda.class);
					            Marshaller m = context.createMarshaller();
					            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
					            String FILE_NAME = "PrenosIzvoda-" + pi.getId().toString() + ".xml";
					            m.marshal(pi, new File(FILE_NAME));
					            JOptionPane.showMessageDialog(null, "Uspesno ste eksportovali medjubankarski transfer u xml fajl.");
							}catch(JAXBException e){
								JOptionPane.showMessageDialog(null, "Neuspesno generisanje XML fajla.");
							}	
						}
						
					});
					
					
				}
			}
        	
        });

    }

    private void listeners() {
        pravnoLiceJComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PravnoLice pl = (PravnoLice) pravnoLiceJComboBox.getSelectedItem();
                racunPravnogLicaJComboBox.removeAllItems();
                for (RacunPravnogLica rpl : racunPravnogLicaService.findByPravnoLiceId(pl.getId())) {
                    racunPravnogLicaJComboBox.addItem(rpl);
                }
            }
        });
        
        
    }
    

}
