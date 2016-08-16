package com.poslovna.informatika.frames;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.poslovna.informatika.configuration.ApplicationContextProvider;
import com.poslovna.informatika.entities.Drzava;
import com.poslovna.informatika.entities.Valuta;
import com.poslovna.informatika.service.DrzavaService;
import com.poslovna.informatika.service.ValutaService;

import net.miginfocom.swing.MigLayout;

public class ValutaFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private DrzavaService drzavaService = (DrzavaService) ApplicationContextProvider.getContext().getBean("drzavaService");
	private ValutaService valutaService = (ValutaService) ApplicationContextProvider.getContext().getBean("valutaService");
	private JPanel jPanel;
	private JComboBox<Drzava> jDrzave;
	private JComboBox<Valuta> jValute;
	private JTextField sifraValute;
	private JTextField nazivValute;
	private JCheckBox domicilna;
	private JButton dodaj;
	private JButton obrisi;
	
	public ValutaFrame(){
		setTitle("Valuta");
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

		jPanel.add(new JLabel("============================== Dodaj novu valutu ================================="), "wrap");
		
		//Drzava
		List<Drzava> drzave = drzavaService.findAll();
		jDrzave = new JComboBox<Drzava>();
		for(Drzava d : drzave){
			jDrzave.addItem(d);
		}
		jPanel.add(new JLabel("Drzava:"));
		jPanel.add(jDrzave, "wrap");
		
		//Sifra valute
		sifraValute = new JTextField(null, 3);
		jPanel.add(new JLabel("Sifra valute:"));
		jPanel.add(sifraValute, "wrap");
		
		//Naziv valute
		nazivValute = new JTextField(null, 9);
		jPanel.add(new JLabel("Naziv valute:"));
		jPanel.add(nazivValute, "wrap");
		
		//Domicilna ??
		domicilna = new JCheckBox();
		domicilna.setSelected(false);
		jPanel.add(new JLabel("Domicilna:"));
		jPanel.add(domicilna, "wrap");
		
		//Dodaj
		dodaj = new JButton("Dodaj");
		jPanel.add(dodaj, "wrap");
		jPanel.add(new JLabel(""), "wrap");
		
		jPanel.add(new JLabel("============================== Postojece valute ================================="), "wrap");
		
		
		
		//Valute
		List<Valuta> valute = valutaService.findAll();
		jValute = new JComboBox<Valuta>();
		for(Valuta v : valute){
			jValute.addItem(v);
		}
		jPanel.add(new JLabel("Izaberite valutu koju zelite obrisati"), "wrap");
		jPanel.add(jValute);
		
		//Obrisi
		obrisi = new JButton("Obrisi");
		jPanel.add(obrisi);
		
		
		
		dodaj.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(sifraValute.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Unesite sifru valute.");
				else if(nazivValute.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Unesite naziv valute.");
				else{
					Valuta valuta = new Valuta();
					Drzava drzava = (Drzava) jDrzave.getSelectedItem();
					valuta.setDrzava(drzava);
					valuta.setSifraValute(sifraValute.getText());
					valuta.setNaziv(nazivValute.getText());
					valuta.setDomicilna(domicilna.isSelected());
					
					valutaService.save(valuta);
					
					jPanel.removeAll();
                    jPanel.revalidate();
                    jPanel.repaint();
                    init();
				}
			}
			
			
		});
		
		obrisi.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Valuta valuta = (Valuta) jValute.getSelectedItem();
				if(valuta != null){
					valutaService.remove(valuta.getId());
					
					jPanel.removeAll();
                    jPanel.revalidate();
                    jPanel.repaint();
                    init();
					
				}
			}
			
		});
		
		
	}

}
