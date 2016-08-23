package com.poslovna.informatika.frames;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.poslovna.informatika.configuration.ApplicationContextProvider;
import com.poslovna.informatika.entities.KursUValuti;
import com.poslovna.informatika.entities.KursnaLista;
import com.poslovna.informatika.formatters.DateLabelFormatter;
import com.poslovna.informatika.service.KursUValutiService;
import com.poslovna.informatika.service.KursnaListaService;

import net.miginfocom.swing.MigLayout;

public class KursnaListaFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private KursUValutiService kuvService = (KursUValutiService) ApplicationContextProvider.getContext().getBean("kursUValutiService");
	private KursnaListaService klService = (KursnaListaService) ApplicationContextProvider.getContext().getBean("kursnaListaService");
	private JScrollPane jScrollPane;
	private JPanel jPanel;
	private JDatePickerImpl datumKursa;
	private JTextField brojKursneListe;
	private JButton dodaj;
	private JButton proveri;
	private JButton obrisi;
	
	public KursnaListaFrame(){
		setTitle("Kursna lista");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(new Dimension(800, 600));
        setLocationRelativeTo(null);
        jPanel = new JPanel(new MigLayout("fillx"));
        init();
        jScrollPane = new JScrollPane(jPanel);
        add(jScrollPane);
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
	
	private void init(){
		jPanel.add(new JLabel("Dodaj kursnu listu"), "wrap");
		
		
		//Primenjuje se od
		jPanel.add(new JLabel("Primenjuje se od:"));
		Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datumKursa = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        jPanel.add(datumKursa, "wrap");
        
        //Dodaj
		jPanel.add(new JLabel(""));
		dodaj = new JButton("Dodaj");
		jPanel.add(dodaj, "wrap");
		
		jPanel.add(new JLabel("Kursne liste"), "wrap");
		
		//Kursne liste
		List<KursnaLista> kursneListe = klService.findAll();
		jPanel.add(new JLabel("Broj kursne liste"));
		jPanel.add(new JLabel("Primenjuje se od"));
		jPanel.add(new JLabel("Valuta"));
		jPanel.add(new JLabel("Kupovni"));
		jPanel.add(new JLabel("Srednji"));
		jPanel.add(new JLabel("Prodajni"), "wrap");
		
		for(KursnaLista k : kursneListe){
			JTextField brojKursneListe = new JTextField(k.getBrojKursneListe().toString());
			brojKursneListe.setEditable(false);
			jPanel.add(brojKursneListe);
			JTextField datum = new JTextField(k.toString());
			datum.setEditable(false);
			jPanel.add(datum, "wrap");
			for(KursUValuti kuv : k.getKurseviUValuti()){
				jPanel.add(new JLabel(""));
				jPanel.add(new JLabel(""));
				JTextField valuta = new JTextField(kuv.getValuta().getNaziv());
				valuta.setEditable(false);
				JTextField kupovni = new JTextField(String.valueOf(kuv.getKupovni()));
				kupovni.setEditable(false);
				JTextField srednji = new JTextField(String.valueOf(kuv.getSrednji()));
				srednji.setEditable(false);
				JTextField prodajni = new JTextField(String.valueOf(kuv.getProdajni()));
				prodajni.setEditable(false);
				jPanel.add(valuta);
				jPanel.add(kupovni);
				jPanel.add(srednji);
				jPanel.add(prodajni, "wrap");
			}
			
			
		}
		
		
		jPanel.add(new JLabel("Obrisite kursnu listu"), "wrap");
		
		//Brisanje kursne liste
		brojKursneListe = new JTextField(null, 3);
		jPanel.add(new JLabel("Broj kursne liste:"));
		jPanel.add(brojKursneListe);
		proveri = new JButton("Proveri");
		jPanel.add(proveri);
		obrisi = new JButton("Obrisi");
		jPanel.add(obrisi, "wrap");
		
		
		
		
		obrisi.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(brojKursneListe.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Unesite broj kursne liste.");
				else{
					try{
						KursnaLista kl = klService.findOne(Integer.parseInt(brojKursneListe.getText()));
						if(kl != null){
							for(KursUValuti kuv : kl.getKurseviUValuti()){
								kuvService.remove(kuv.getId());
							}
							klService.remove(kl.getId());
							JOptionPane.showMessageDialog(null, "Uspesno ste obrisali kursnu listu.");
							
							jPanel.removeAll();
		                    jPanel.revalidate();
		                    jPanel.repaint();
		                    init();
							
						}else{
							JOptionPane.showMessageDialog(null, "Ne postoji kursna lista sa tim brojem liste.");
						}
					}catch(NumberFormatException m){
						JOptionPane.showMessageDialog(null, "Broj kursne liste mora biti numerickog tipa. Npr. 3");
					}
				}
			}
			
		});
		
		proveri.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(brojKursneListe.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Unesite broj kursne liste.");
				else{
					try{
						KursnaLista kl = klService.findOne(Integer.parseInt(brojKursneListe.getText()));
						if(kl != null){
							JOptionPane.showMessageDialog(null, "Kursna lista postoji!");
						}else{
							JOptionPane.showMessageDialog(null, "Ne postoji kursna lista sa tim brojem liste.");
						}
					}catch(NumberFormatException e){
						JOptionPane.showMessageDialog(null, "Broj kursne liste mora biti numerickog tipa. Npr. 3");
					}
					
				}
			}
			
		});
		dodaj.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Date primenjujeSeOd = (Date) datumKursa.getModel().getValue();
				Date datum = new Date();
				
				KursnaLista kl = new KursnaLista();
				kl.setDatum(datum);
				kl.setPrimenjujeSeOd(primenjujeSeOd);
				
				kl = klService.save(kl);
				kl.setBrojKursneListe(kl.getId());
				klService.save(kl);
				
				jPanel.removeAll();
                jPanel.revalidate();
                jPanel.repaint();
                init();
				
			}
			
		});
		
	}

}
