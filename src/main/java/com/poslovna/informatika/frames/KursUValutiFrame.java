package com.poslovna.informatika.frames;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.poslovna.informatika.configuration.ApplicationContextProvider;
import com.poslovna.informatika.entities.KursUValuti;
import com.poslovna.informatika.entities.KursnaLista;
import com.poslovna.informatika.entities.Valuta;
import com.poslovna.informatika.service.KursUValutiService;
import com.poslovna.informatika.service.KursnaListaService;
import com.poslovna.informatika.service.ValutaService;

import net.miginfocom.swing.MigLayout;

public class KursUValutiFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private ValutaService valutaService = (ValutaService) ApplicationContextProvider.getContext().getBean("valutaService");
	private KursUValutiService kuvService = (KursUValutiService) ApplicationContextProvider.getContext().getBean("kursUValutiService");
	private KursnaListaService klService = (KursnaListaService) ApplicationContextProvider.getContext().getBean("kursnaListaService");
	private JPanel jPanel;
	private JComboBox<Valuta> jValute;
	private JComboBox<KursnaLista> jKursneListe;
	private JTextField kupovni;
	private JTextField srednji;
	private JTextField prodajni;
	private JButton dodaj;
	
	public KursUValutiFrame(){
		setTitle("Kurs u valuti");
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
		jPanel.add(new JLabel("Dodaj novui kurs"), "wrap");
		
		//Valute
		List<Valuta> valute = valutaService.findAll();
		jValute = new JComboBox<Valuta>();
		for(Valuta v : valute){
			jValute.addItem(v);
		}
		jPanel.add(new JLabel("Valuta:"));
		jPanel.add(jValute, "wrap");
		
		//Kupovni
		kupovni = new JTextField(null, 6);
		jPanel.add(new JLabel("Kupovni:"));
		jPanel.add(kupovni);
		
		//Srednji
		srednji = new JTextField(null, 6);
		jPanel.add(new JLabel("Srednji:"));
		jPanel.add(srednji);
		
		//Prodajni
		prodajni = new JTextField(null, 6);
		jPanel.add(new JLabel("Prodajni:"));
		jPanel.add(prodajni, "wrap");
		
		//Kursna lista
		jKursneListe = new JComboBox<KursnaLista>();
		List<KursnaLista> kListe = klService.findAll();
		for(KursnaLista kl : kListe){
			jKursneListe.addItem(kl);
		}
		jPanel.add(new JLabel("Kursna lista za dan:"));
		jPanel.add(jKursneListe);
		
		//Dodaj
		dodaj = new JButton("Dodaj");
		jPanel.add(dodaj, "wrap");
		
		
		dodaj.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(kupovni.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Unesite kupovni kurs.");
				else if(prodajni.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Unesite unesite prodajni kurs.");
				else if(srednji.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Unesite srednji kurs.");
				else{
					try{
						Double kp = Double.parseDouble(kupovni.getText());
						Double pr = Double.parseDouble(prodajni.getText());
						Double sr = Double.parseDouble(srednji.getText());
						
						Valuta valuta = (Valuta) jValute.getSelectedItem();
						KursnaLista kl = (KursnaLista) jKursneListe.getSelectedItem();
						
						KursUValuti kuv = new KursUValuti();
						kuv.setKupovni(kp.doubleValue());
						kuv.setProdajni(pr.doubleValue());
						kuv.setSrednji(sr.doubleValue());
						kuv.setValuta(valuta);
						kuv.setKursnaLista(kl);
						
						kuv = kuvService.save(kuv);
						kuv.setRedniBroj(kuv.getId());
						kuvService.save(kuv);
						
						jPanel.removeAll();
	                    jPanel.revalidate();
	                    jPanel.repaint();
	                    init();
						
					}catch(NumberFormatException e){
						JOptionPane.showMessageDialog(null, "Pogresan format za kurs. npr. 123.33");
					}
				}
				
			}
			
		});
		
		
		
	}

}
