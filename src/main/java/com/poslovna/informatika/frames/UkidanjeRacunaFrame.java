package com.poslovna.informatika.frames;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.poslovna.informatika.configuration.ApplicationContextProvider;
import com.poslovna.informatika.entities.RacunPravnogLica;
import com.poslovna.informatika.service.RacunPravnogLicaService;

import net.miginfocom.swing.MigLayout;

public class UkidanjeRacunaFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private String brojRacuna;
	private JPanel jPanel;
	private JPanel smallPanel;
	private JTextField brojRacuna1;
	private JTextField brojRacuna2;
	private JTextField brojRacuna3;
	private JButton check;
	private JButton obrisi;
	private RacunPravnogLicaService rplService = (RacunPravnogLicaService) ApplicationContextProvider.getContext().getBean("racunPravnogLicaService");
	
	public UkidanjeRacunaFrame(){
		setTitle("Ukidanje racuna");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(new Dimension(800, 600));
        setLocationRelativeTo(null);
        jPanel = new JPanel(new MigLayout("fillx"));
        init();
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
	
	private void init(){
		jPanel.add(new JLabel("Molimo Vas da popunite formu..."), "wrap");
        jPanel.add(new JLabel(""), "wrap");
        jPanel.add(new JLabel("Primer: 123-123456-123"), "wrap");
        brojRacuna1 = new JTextField(null, 3);
        brojRacuna2 = new JTextField(null, 6);
        brojRacuna3 = new JTextField(null, 3);
        check = new JButton("Proveri");
        obrisi = new JButton("Obrisi");
        jPanel.add(brojRacuna1);
        jPanel.add(new JLabel("-"));
        jPanel.add(brojRacuna2);
        jPanel.add(new JLabel("-"));
        jPanel.add(brojRacuna3);
        jPanel.add(check);
        jPanel.add(obrisi, "wrap");
        smallPanel = new JPanel();
        smallPanel.setSize(300, 300);
        jPanel.add(smallPanel);
        
        
        check.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				smallPanel.removeAll();
				smallPanel.revalidate();
				smallPanel.repaint();
				
				String brojRacuna = brojRacuna1.getText() + "-" + brojRacuna2.getText() + "-" + brojRacuna3.getText();
				RacunPravnogLica rpl = rplService.findByBrojRacuna(brojRacuna);
				if(rpl != null){
					jPanel.add(new JLabel("Detalji: "), "wrap");
					JTextField brojRc = new JTextField(rpl.getBrojRacuna());
					JTextField datumOtvaranja = new JTextField(rpl.getDatumOtvaranja().toString());
					JTextField valuta = new JTextField(rpl.getValuta().getNaziv());
					JTextField pravnoLice = new JTextField(rpl.getPravnoLice().getNaziv());
					
					jPanel.add(new JLabel("Broj racuna: "));
					jPanel.add(brojRc, "wrap");
					jPanel.add(new JLabel("Pravno lice: "));
					jPanel.add(pravnoLice, "wrap");
					jPanel.add(new JLabel("Valuta: "));
					jPanel.add(valuta, "wrap");
					jPanel.add(new JLabel("Datum otvaranja: "));
					jPanel.add(datumOtvaranja, "wrap");
				}else{
					jPanel.add(new JLabel("Ne postoji racun pravnog lica sa zadatim brojem racuna."));
				}
				
				
			}
        	
        });
        
        obrisi.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Da li zelite da obrisete ovaj racun?", "Potrvrda", JOptionPane.YES_NO_OPTION)
                        == JOptionPane.YES_OPTION) {
					String brojRacuna = brojRacuna1.getText() + "-" + brojRacuna2.getText() + "-" + brojRacuna3.getText();
					RacunPravnogLica rpl = rplService.findByBrojRacuna(brojRacuna);
					if(rpl == null){
						JOptionPane.showMessageDialog(null, "Racun je nevazeci.");
					}else{
						rplService.remove(rpl.getId());
						JOptionPane.showMessageDialog(null, "Uspesno ste obrisali racun.");
					}
                    dispose();
                }
				
				
			}
        	
        });
        
        
	}
	
}
