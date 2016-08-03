package com.poslovna.informatika.frames;

import com.poslovna.informatika.BankApplication;
import com.poslovna.informatika.configuration.ApplicationContextProvider;
import com.poslovna.informatika.service.UserService;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginFrame extends JFrame {

    private UserService userService = (UserService) ApplicationContextProvider.getContext().getBean("userService");
    public static LoginFrame loginFrame = null;
    private JTextField username;
    private JPasswordField password;
    private JPanel jPanel;

    private LoginFrame() {
        setTitle("Bank");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(new Dimension(800, 600));
        setLocationRelativeTo(null);
        jPanel = new JPanel(new MigLayout("fillx"));
        initializeLogin();
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

    private void initializeLogin() {
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
                // HardCoded for testing purpose...
                BankApplication.loggedUser = userService.login(user, pass);
                if (BankApplication.loggedUser == null) {
                    JOptionPane.showMessageDialog(null, "Korisničko ime ili lozinka nije ispravna.");
                } else {
                    setVisible(false);
                    MainFrame mainFrame = MainFrame.getInstance();
                    mainFrame.setVisible(true);
                }
            }
        });
    }

    public static LoginFrame getInstance() {
        if (loginFrame == null) {
            loginFrame = new LoginFrame();
        }
        return loginFrame;
    }

}
