package com.poslovna.informatika.frames;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private static MainFrame mainFrame;

    public MainFrame() {
        setTitle("Illuminate Bank");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        initializeMainMenu();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                if (JOptionPane.showConfirmDialog(null, "Da li ste sigurni da želite da zatvorite aplikaciju?", "Potvrda", JOptionPane.YES_NO_OPTION)
                        == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }

            }
        });
    }

    private void initializeMainMenu() {

        JMenuBar menuBar0 = new JMenuBar();

        // Illuminate Bank
        JMenu menu0 = new JMenu("Illuminate Bank");
        menu0.add(new JMenuItem("About Project"));
        menu0.add(new JMenuItem("Exit"));

        // Processes
        JMenu menu1 = new JMenu("Processes");
        menu1.add(new JMenuItem("Generisanje naloga za medjubankarski transfer (RTGS/Clearing)"));
        menu1.add(new JMenuItem("Export nalog za medjubankarski transfer u XML datoteku"));
        menu1.add(new JMenuItem("Generisanje nalog aza prenos izvoda datom preduzecu"));
        menu1.add(new JMenuItem("Export datog izvoda u XML datotetku"));
        menu1.add(new JMenuItem("Import stavki placanja iz XML datoteke"));
        menu1.add(new JMenuItem("Ukidanje racuna"));

        // Reports
        JMenu menu2 = new JMenu("Processes");
        menu2.add(new JMenuItem("Nalog za prenos sa analitikom"));
        menu2.add(new JMenuItem("Spisak rauna sa stanjem zadate banke"));

        menuBar0.add(menu0);
        menuBar0.add(menu1);
        menuBar0.add(menu2);

        setJMenuBar(menuBar0);

    }

    public static MainFrame getInstance() {
        if (mainFrame == null) {
            mainFrame = new MainFrame();
        }
        return mainFrame;
    }

}
