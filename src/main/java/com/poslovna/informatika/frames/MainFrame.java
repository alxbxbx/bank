package com.poslovna.informatika.frames;

import com.poslovna.informatika.BankApplication;
import com.poslovna.informatika.configuration.ApplicationContextProvider;
import com.poslovna.informatika.entities.User;
import com.poslovna.informatika.service.UserService;

import net.miginfocom.swing.MigLayout;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;

import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@PropertySource({"classpath:application.properties"})
public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private static MainFrame mainFrame;
    private static User loggedUser = null;
    private static boolean isLoggedIn = false;

    UserService userService = (UserService) ApplicationContextProvider.getContext().getBean("userService");
    Environment env = ApplicationContextProvider.getContext().getEnvironment();
    private JPanel jPanel;

    private MainFrame() {
        setTitle("Bank");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(new Dimension(800, 600));
        setLocationRelativeTo(null);
        jPanel = new JPanel(new MigLayout("fillx"));
        initializeMainMenu();
        initializeHomePage();
        add(jPanel);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                if (JOptionPane.showConfirmDialog(null, "Da li ste sigurni da želite da zatvorite aplikaciju?",
                        "Potrvrda", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    private void initializeMainMenu() {
        JMenuBar menuBar0 = new JMenuBar();

        // Banka
        JMenu menu0 = new JMenu("Banka");
        JMenuItem jMenuItem = new JMenuItem("O aplikaciji");
        JMenuItem jMenuItem1 = new JMenuItem("Odjavi se");
        menu0.add(jMenuItem);
        menu0.add(jMenuItem1);
        jMenuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BankApplication.isLoggedIn = false;
                BankApplication.loggedUser = null;
                setVisible(false);
                LoginFrame.getInstance().setVisible(true);
            }
        });
        jMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Aplikaciju razvili Filip Bekić, Aleksandar Stanković i Filip Marjanović\nFakultet Tehničkih Nauka,\nSoftverske i informacione tehnologije\nNovi Sad, 2016");
            }
        });
        menuBar0.add(menu0);

        // Obrade
        JMenu menu1 = new JMenu("Obrade");
        JMenuItem jMenuItem0 = new JMenuItem("RTGS | Medjubankarski Transfer ");
        JMenuItem ukidanjeRacuna = new JMenuItem("Ukidanje racuna");
        menu1.add(jMenuItem0);
        jMenuItem0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MedjubankarskiTransferFrame cf = new MedjubankarskiTransferFrame(true);
                cf.setVisible(true);
            }
        });
        JMenuItem jMenuItem2 = new JMenuItem("Clearing | Medjubankarski Transfer");
        menu1.add(jMenuItem2);
        jMenuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MedjubankarskiTransferFrame cf = new MedjubankarskiTransferFrame(false);
                cf.setVisible(true);
            }
        });
        JMenuItem eksportMBT = new JMenuItem("Export naloga za medjubankarski transfer u XML datoteku");
        menu1.add(eksportMBT);
        JMenuItem jMenuItem3 = new JMenuItem("Generisanje naloga za prenos izvoda datom preduzecu");
        menu1.add(jMenuItem3);
        jMenuItem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GenerisanjePrenosaIzvodaFrame cf = new GenerisanjePrenosaIzvodaFrame();
                cf.setVisible(true);
            }
        });
        JMenuItem jMenuItem21 = new JMenuItem("Medjubankarski promet");
        menu1.add(jMenuItem21);
        jMenuItem21.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MBTIFrame mf = new MBTIFrame();
                mf.setVisible(true);
            }
        });
        menu1.add(ukidanjeRacuna);
        menuBar0.add(menu1);
        JMenu menu2 = new JMenu("Izveštaji");
        menu2.add(new JMenuItem("Nalog za prenos sa analitikom"));
        JMenuItem spisak = new JMenuItem("Spisak rauna sa stanjem zadate banke");
        menu2.add(spisak);
        menuBar0.add(menu2);
        JMenu menu3 = new JMenu("Admin");
        JMenuItem jMenuItemPravnoLice = new JMenuItem("Pravno Lice");
        JMenuItem jMenuItemPravnoLiceRacun = new JMenuItem("Pravno Lice: Racun");
        JMenuItem jMenuItemKursnaLista = new JMenuItem("Kursna lista");
        JMenuItem jMenuItemDrzava = new JMenuItem("Drzava");
        JMenuItem jMenuItemNaseljenoMesto = new JMenuItem("Naseljeno mesto");
        JMenuItem jMenuItemValuta = new JMenuItem("Valuta");
        JMenuItem jMenuItemKurs = new JMenuItem("Kurs u valuti");
        JMenuItem jMenuItemKodoviBanke = new JMenuItem("Kodovi banke");
        JMenuItem jMenuItemVrstaPlacanja = new JMenuItem("Vrste placanja");
        menu3.add(jMenuItemPravnoLice);
        menu3.add(jMenuItemPravnoLiceRacun);
        menu3.add(jMenuItemKursnaLista);
        menu3.add(jMenuItemDrzava);
        menu3.add(jMenuItemNaseljenoMesto);
        menu3.add(jMenuItemValuta);
        menu3.add(jMenuItemKurs);
        menu3.add(jMenuItemKodoviBanke);
        menu3.add(jMenuItemVrstaPlacanja);
        menuBar0.add(menu3);
        setJMenuBar(menuBar0);

        jMenuItemKodoviBanke.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KodoviBankeFrame pf = new KodoviBankeFrame();
                pf.setVisible(true);
            }
        });

        jMenuItemPravnoLiceRacun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RacunPravnogLicaFrame pf = new RacunPravnogLicaFrame();
                pf.setVisible(true);
            }
        });

        jMenuItemPravnoLice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PravnoLiceFrame pf = new PravnoLiceFrame();
                pf.setVisible(true);
            }
        });

        jMenuItemDrzava.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                DrzavaFrame df = new DrzavaFrame();
                df.setVisible(true);
            }
        });

        jMenuItemNaseljenoMesto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                NaseljenoMestoFrame nmf = new NaseljenoMestoFrame();
                nmf.setVisible(true);
            }
        });
        
        jMenuItemValuta.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				ValutaFrame vf = new ValutaFrame();
				vf.setVisible(true);
			}
        	
        });
        
        jMenuItemKurs.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				KursUValutiFrame kvf = new KursUValutiFrame();
				kvf.setVisible(true);
				
			}
        	
        });
        
        jMenuItemKursnaLista.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				KursnaListaFrame klf = new KursnaListaFrame();
				klf.setVisible(true);
				
			}
        	
        });
        
        jMenuItemVrstaPlacanja.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				VrstaPlacanjaFrame vpf = new VrstaPlacanjaFrame();
				vpf.setVisible(true);
			}
        	
        });
        
        eksportMBT.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				EksportMBTFrame embtf = new EksportMBTFrame();
				embtf.setVisible(true);
			}
        	
        });

        ukidanjeRacuna.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UkidanjeRacunaFrame urf = new UkidanjeRacunaFrame();
                urf.setVisible(true);
            }
        });

        spisak.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Connection conn = null;
                try {
                    conn = DriverManager.getConnection(
                            env.getProperty("spring.datasource.url"),
                            env.getProperty("spring.datasource.username"),
                            env.getProperty("spring.datasource.password")
                    );
                    JasperPrint jp = JasperFillManager.fillReport(
                            "C://Users//Alxbxbx//Documents//Poslovna informatika//Reports//PReport1.jasper",
                            null, conn);
                    JasperViewer.viewReport(jp, false);

                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void initializeHomePage() {
        jPanel.add(new JLabel("Dobrodošli, " + BankApplication.loggedUser.getName()), "wrap");
        jPanel.add(new JLabel("Uspešno ste se prijavili na sistem."), "wrap");
        jPanel.add(new JLabel("Bank 2016, Novi Sad"), "wrap");
    }

    public static MainFrame getInstance() {
        if (mainFrame == null) {
            mainFrame = new MainFrame();
        }
        return mainFrame;
    }
}
