package com.poslovna.informatika.frames;

import com.poslovna.informatika.configuration.ApplicationContextProvider;
import com.poslovna.informatika.entities.PravnoLice;
import com.poslovna.informatika.service.PravnoLiceService;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PravnoLiceFrame  extends JFrame {

    private static final long serialVersionUID = 1L;
    private PravnoLiceService pravnoLiceService = (PravnoLiceService) ApplicationContextProvider.getContext().getBean("pravnoLiceService");
    private JPanel jPanel;
    private JScrollPane jScrollPane;
    private JTextField pib, naziv, adresa, email, web, telefon, fax, pibToDelete;
    private JButton sacuvaj, obrisi;
    private JCheckBox banka;

    public PravnoLiceFrame() {
        setTitle("Pravna Lica");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(new Dimension(800, 600));
        setLocationRelativeTo(null);
        jPanel = new JPanel(new MigLayout("fillx"));
        init();
        jScrollPane = new JScrollPane(jPanel);
        add(jScrollPane);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                if (JOptionPane.showConfirmDialog(null, "Da li ste sigurni da želite da zatvorite aplikaciju?",
                        "Potrvrda", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });
    }

    private void init() {

        jPanel.add(new JTextArea("============================== DODAJ NOVO PRAVNO LICE U BAZU ================================="), "wrap");

        // Naziv
        jPanel.add(new JLabel("PIB"), "wrap");
        pib = new JTextField(null, 20);
        jPanel.add(pib, "wrap");

        // Naziv
        jPanel.add(new JLabel("Naziv"), "wrap");
        naziv = new JTextField(null, 20);
        jPanel.add(naziv, "wrap");

        // Adresa
        jPanel.add(new JLabel("Adresa"), "wrap");
        adresa = new JTextField(null, 20);
        jPanel.add(adresa, "wrap");

        // EMail
        jPanel.add(new JLabel("E-Mail"), "wrap");
        email = new JTextField(null, 20);
        jPanel.add(email, "wrap");

        // Web
        jPanel.add(new JLabel("Web"), "wrap");
        web = new JTextField(null, 20);
        jPanel.add(web, "wrap");

        // Telefon
        jPanel.add(new JLabel("Telefon"), "wrap");
        telefon = new JTextField(null, 20);
        jPanel.add(telefon, "wrap");

        // Fax
        jPanel.add(new JLabel("Fax"), "wrap");
        fax = new JTextField(null, 20);
        jPanel.add(fax, "wrap");

        // Banka
        banka = new JCheckBox("Banka");
        jPanel.add(banka, "wrap");

        // Sacuvaj
        sacuvaj = new JButton("Sacuvaj");
        jPanel.add(sacuvaj, "wrap");
        jPanel.add(new JLabel(""), "wrap");

        jPanel.add(new JTextArea("============================== PRAVNA LICA U BAZI PODATAKA ================================="), "wrap");
        java.util.List<PravnoLice> pravnaLica = pravnoLiceService.findAll();
        for (PravnoLice p : pravnaLica) {
            jPanel.add(new Label(p.getPib() + ", " + p.getNaziv()), "wrap");
        }
        jPanel.add(new JLabel("Unesi PIB i obrisi pravno lice: "), "wrap");
        pibToDelete = new JTextField("Unesi pib...", 20);
        obrisi = new JButton("Obrisi");
        jPanel.add(pibToDelete);
        jPanel.add(obrisi);

        listeners();
    }

    private void listeners() {
        sacuvaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pib.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Unesi PIB.");
                } else if (naziv.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Unesi naziv.");
                } else if (adresa.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Unesi adresa.");
                } else if (email.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Unesi email.");
                } else if (web.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Unesi web.");
                } else if (telefon.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Unesi telefon.");
                } else if (fax.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Unesi fax.");
                } else {
                    PravnoLice pravnoLice = new PravnoLice();
                    pravnoLice.setPib(pib.getText());
                    pravnoLice.setNaziv(naziv.getText());
                    pravnoLice.setAdresa(adresa.getText());
                    pravnoLice.seteMail(email.getText());
                    pravnoLice.setWeb(web.getText());
                    pravnoLice.setTelefon(telefon.getText());
                    pravnoLice.setFax(fax.getText());
                    pravnoLice.setBanka(banka.isSelected());
                    pravnoLiceService.save(pravnoLice);
                    jPanel.removeAll();
                    jPanel.revalidate();
                    jPanel.repaint();
                    init();
                }
            }
        });
        obrisi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pib = pibToDelete.getText();
                PravnoLice pl = pravnoLiceService.findByPib(pib);
                pravnoLiceService.remove(pl.getId());
                jPanel.removeAll();
                jPanel.revalidate();
                jPanel.repaint();
                init();
            }
        });
    }

}
