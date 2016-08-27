package com.poslovna.informatika.frames;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.poslovna.informatika.configuration.ApplicationContextProvider;
import com.poslovna.informatika.entities.MedjubankarskiTransfer;
import com.poslovna.informatika.service.MedjubankarskiTransferService;

import net.miginfocom.swing.MigLayout;

public class EksportMBTFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private MedjubankarskiTransferService mbtService = (MedjubankarskiTransferService) ApplicationContextProvider.getContext().getBean("medjubankarskiTransferService");
	private JPanel jPanel;
	private JTextField idMBT;
	private JButton export;
	
	public EksportMBTFrame(){
		setTitle("Eksport medjubankarskih transfera");
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
		jPanel.add(new JLabel("Eksport medjubankarskog transfera u xml"), "wrap");
		jPanel.add(new JLabel(""), "wrap");
		
		jPanel.add(new JLabel("ID MBT: "));
		idMBT = new JTextField(null, 3);
		jPanel.add(idMBT);
		
		export = new JButton("Export");
		jPanel.add(export);
		
		
		export.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Integer id = null;
				try{
					id = Integer.parseInt(idMBT.getText());
					MedjubankarskiTransfer mbt = mbtService.findOne(id);
					if(mbt != null){
						JAXBContext context = JAXBContext.newInstance(MedjubankarskiTransfer.class);
			            Marshaller m = context.createMarshaller();
			            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			            String FILE_NAME = idMBT.getText() + ".xml";
			            m.marshal(mbt, new File(FILE_NAME));
			            
			            JOptionPane.showMessageDialog(null, "Uspesno ste eksportovali medjubankarski transfer u xml fajl.");
						
					}else{
						JOptionPane.showMessageDialog(null, "Nije pronadjen medjubankarski transfer sa zadatim IDem.");
					}
				}catch(NumberFormatException | JAXBException e){
					JOptionPane.showMessageDialog(null, "ID medjubankarskog transfera mora biti broj");
					e.printStackTrace();
				}
				
			}
			
		});
	}
}
