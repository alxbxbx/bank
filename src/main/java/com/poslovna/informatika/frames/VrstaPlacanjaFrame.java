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
import com.poslovna.informatika.entities.VrstaPlacanja;
import com.poslovna.informatika.service.VrstaPlacanjaService;

import net.miginfocom.swing.MigLayout;

public class VrstaPlacanjaFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private VrstaPlacanjaService vpService = (VrstaPlacanjaService) ApplicationContextProvider.getContext().getBean("vrstaPlacanjaService");
	private JPanel jPanel;
	private JTextField oznakaVrste;
	private JTextField nazivVrstePlacanja;
	private JButton dodaj;
	private JButton obrisi;
	private JComboBox<VrstaPlacanja> jVrstePlacanja;
	
	public VrstaPlacanjaFrame(){
		setTitle("Vrsta placanja");
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
		jPanel.add(new JLabel("Vrste placanja"), "wrap");
		jPanel.add(new JLabel(""), "wrap");
		
		List<VrstaPlacanja> vrstePlacanja = vpService.findAll();
		
		//Oznaka vrste placanja
		oznakaVrste = new JTextField(null, 3);
		jPanel.add(new JLabel("Oznaka vrste"), "wrap");
		jPanel.add(oznakaVrste, "wrap");
		
		//Naziv vrste placanja
		nazivVrstePlacanja = new JTextField(null, 9);
		jPanel.add(new JLabel("Naziv vrste placanja"), "wrap");
		jPanel.add(nazivVrstePlacanja, "wrap");
		
		//Dodaj
		dodaj = new JButton("Dodaj");
		jPanel.add(dodaj, "wrap");
		
		jPanel.add(new JLabel("Postojece vrste placanja"), "wrap");
		jPanel.add(new JLabel(""), "wrap");
		
		//Lista vrsti placanja
		jPanel.add(new JLabel("Oznaka vrste"));
		jPanel.add(new JLabel("Naziv vrste placanja"), "wrap");
		
		for(VrstaPlacanja vp : vrstePlacanja){
			JTextField oznaka = new JTextField(vp.getOznakaVrste());
			JTextField naziv = new JTextField(vp.getNazivVrstePlacanja());
			oznaka.setEditable(false);
			naziv.setEditable(false);
			jPanel.add(oznaka);
			jPanel.add(naziv, "wrap");
		}
		
		//Brisanje 
		jPanel.add(new JLabel("Obrisi vrstu placanja"), "wrap");
		jPanel.add(new JLabel(""), "wrap");
		
		
		//Vrste placanja
		jVrstePlacanja = new JComboBox<VrstaPlacanja>();
		for(VrstaPlacanja v : vrstePlacanja){
			jVrstePlacanja.addItem(v);
		}
		jPanel.add(new JLabel("Vrsta placanja"), "wrap");
		jPanel.add(jVrstePlacanja);
		
		//Obrisi
		obrisi = new JButton("Obrisi");
		jPanel.add(obrisi, "wrap");
		
		
		
		dodaj.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(oznakaVrste.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Unesite oznaku vrste placanja.");
				else if(nazivVrstePlacanja.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Unesite naziv vrste placanja.");
				else{
					VrstaPlacanja vp = new VrstaPlacanja();
					vp.setOznakaVrste(oznakaVrste.getText());
					vp.setNazivVrstePlacanja(nazivVrstePlacanja.getText());
					vpService.save(vp);
					
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
				if(jVrstePlacanja.getSelectedItem() != null){
					VrstaPlacanja vp = (VrstaPlacanja) jVrstePlacanja.getSelectedItem();
					vpService.remove(vp.getId());
					
					JOptionPane.showMessageDialog(null, "Uspesno ste obrisali vrstu placanja.");
					
					jPanel.removeAll();
	                jPanel.revalidate();
	                jPanel.repaint();
	                init();
					
				}else{
					JOptionPane.showMessageDialog(null, "Nemoguce je obrisati.");
				}
			}
			
			
		});
		
		
	}
	
	

}
