package com.poslovna.informatika.frames;

import com.poslovna.informatika.BankApplication;
import com.poslovna.informatika.configuration.ApplicationContextProvider;
import com.poslovna.informatika.entities.User;
import com.poslovna.informatika.service.UserService;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private static MainFrame mainFrame;

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
                if (JOptionPane.showConfirmDialog(null, "Da li ste sigurni da želite da zatvorite aplikaciju?", "Potrvrda", JOptionPane.YES_NO_OPTION)
                        == JOptionPane.YES_OPTION) {
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
                JOptionPane.showMessageDialog(null, "Aplikaciju razvili Filip Bekić, Aleksandar Stanković i Filip Marjanović\nFakultet Tehničkih Nauka,\nSoftverske i informacione tehnologije\nNovi Sad, 2016");
            }
        });
        menuBar0.add(menu0);

        // Obrade
        JMenu menu1 = new JMenu("Obrade");
        JMenuItem jMenuItem0 = new JMenuItem("RTGS medjubankarski transfer ");
        menu1.add(jMenuItem0);
        jMenuItem0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClearingFrame cf = new ClearingFrame();
                cf.setVisible(true);
            }
        });
        menu1.add(new JMenuItem("Clearing medjubankarski transfer (/)"));
        menu1.add(new JMenuItem("Export nalog za medjubankarski transfer u XML datoteku"));
        menu1.add(new JMenuItem("Generisanje naloga za prenos izvoda datom preduzecu"));
        menu1.add(new JMenuItem("Export datog izvoda u XML datotetku"));
        menu1.add(new JMenuItem("Import stavki placanja iz XML datoteke"));
        menu1.add(new JMenuItem("Ukidanje racuna"));
        menuBar0.add(menu1);
        JMenu menu2 = new JMenu("Izveštaji");
        menu2.add(new JMenuItem("Nalog za prenos sa analitikom"));
        menu2.add(new JMenuItem("Spisak rauna sa stanjem zadate banke"));
        menuBar0.add(menu2);
        setJMenuBar(menuBar0);
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
