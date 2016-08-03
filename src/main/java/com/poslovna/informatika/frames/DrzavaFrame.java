package com.poslovna.informatika.frames;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.poslovna.informatika.configuration.ApplicationContextProvider;
import com.poslovna.informatika.entities.Drzava;
import com.poslovna.informatika.service.DrzavaService;

import net.miginfocom.swing.MigLayout;

public class DrzavaFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JPanel jPanel;
	private JTextField sifraDrzave;
	private JTextField nazivDrzave;
	private JButton dodajDrzavu;
	private DrzavaService drzavaService = (DrzavaService) ApplicationContextProvider.getContext().getBean("drzavaService");
	private List<Drzava> drzave;
	
	public DrzavaFrame(){
		setTitle("Drzava");
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
		drzave = drzavaService.findAll();

		sifraDrzave = new JTextField(null, 3);
		nazivDrzave = new JTextField(null, 9);
		
		jPanel.add(new JLabel("Sifra drzave:"));
		jPanel.add(sifraDrzave);
		jPanel.add(new JLabel("Naziv drzave:"));
		jPanel.add(nazivDrzave);
		
		dodajDrzavu = new JButton("Dodaj drzavu");
		jPanel.add(dodajDrzavu, "wrap");
		
		jPanel.add(new JLabel("Lista drzava: "), "wrap");
		jPanel.add(new JLabel(""), "wrap");
		jPanel.add(new JLabel("Sifra drzave"));
		jPanel.add(new JLabel("Naziv drzave"), "wrap");
		
		for(Drzava drzava : drzave){
			JTextField sifra = new JTextField(drzava.getSifraDrzave(), 3);
			JTextField naziv = new JTextField(drzava.getNazivDrzave(), 6);
			sifra.setEditable(false);
			naziv.setEditable(false);
			jPanel.add(sifra);
			jPanel.add(naziv, "wrap");
			
		}
		
		dodajDrzavu.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Drzava drzava = new Drzava();
				drzava.setNazivDrzave(nazivDrzave.getText());
				drzava.setSifraDrzave(sifraDrzave.getText());
				
				if(!nazivDrzave.getText().equals("")&&!sifraDrzave.getText().equals("")){
					drzavaService.save(drzava);
					drzave = drzavaService.findAll();
					
					jPanel.removeAll();
					jPanel.revalidate();
					jPanel.repaint();
					
					init();

				}
			}
			
		});
		
	}
	

}
