package com.poslovna.informatika.frames;

import com.poslovna.informatika.entities.User;
import com.poslovna.informatika.service.UserService;
import net.miginfocom.swing.MigLayout;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class MainFrame extends JFrame {

    private JTextField username;
    private JPasswordField password;

    private static final long serialVersionUID = 1L;
    private static MainFrame mainFrame;
    private static User loggedUser = null;
    private static boolean isLoggedIn = false;
    private JPanel jPanel;

    private MainFrame() {
        setTitle("Bank");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(new Dimension(800, 600));
        setLocationRelativeTo(null);
        jPanel = new JPanel(new MigLayout("fillx"));
        initializeMainMenu();
        initializeLogin();
        add(jPanel);
        setVisible(true);
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
        JMenu menu0 = new JMenu("Bank");
        JMenuItem jMenuItem = new JMenuItem("O aplikaciji");
        menu0.add(jMenuItem);
        jMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Aplikaciju razvili Filip Bekić, Aleksandar Stanković i Filip Marjanović\nFakultet Tehničkih Nauka,\nSoftverske i informacione tehnologije\nNovi Sad, 2016");
            }
        });
        menuBar0.add(menu0);
        JMenu menu1 = new JMenu("Obrade");
        menu1.add(new JMenuItem("Generisanje naloga za medjubankarski transfer (RTGS/Clearing)"));
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

    private void initializeLogin() {
        if (isLoggedIn) {
            jPanel.add(new JLabel("ULOGOVAN!"));
        } else {
            jPanel.add(new JLabel("Ukoliko niste ulogovani, nećete moći da pristupite svim opcijama koje program nudi."), "wrap");
            jPanel.add(new JLabel("Molimo Vas da se prijavite na sistem."), "wrap");
            jPanel.add(new JLabel("Korisničko ime:"), "wrap");
            username = new JTextField(null, 20);
            jPanel.add(username, "wrap");
            jPanel.add(new JLabel("Lozinka:"), "wrap");
            password = new JPasswordField(null, 20);
            jPanel.add(password, "wrap");
            JButton loginButton = new JButton("Prijavi se");
            jPanel.add(loginButton);
            loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String user = username.getText();
                    String pass = password.getText();
                    //loggedUser = userSer
                    if (loggedUser == null) {
                        JOptionPane.showMessageDialog(null, "Korisničko ime ili lozinka nije ispravna.");
                    } else {
                        // Success
                    }
                }
            });
        }

    }

    public static MainFrame getInstance() {
        if (mainFrame == null) {
            mainFrame = new MainFrame();
        }
        return mainFrame;
    }
}
