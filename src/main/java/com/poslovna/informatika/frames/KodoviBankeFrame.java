package com.poslovna.informatika.frames;

import com.poslovna.informatika.configuration.ApplicationContextProvider;
import com.poslovna.informatika.entities.Drzava;
import com.poslovna.informatika.entities.KodBanke;
import com.poslovna.informatika.service.DrzavaService;
import com.poslovna.informatika.service.KodBankeService;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class KodoviBankeFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private KodBankeService kodBankeService = (KodBankeService) ApplicationContextProvider.getContext().getBean("kodBankeService");
    private JPanel jPanel;
    private JTextField sifraBanke, swiftKod;
    private JButton sacuvaj, obrisi;
    private JComboBox<KodBanke> kodBankeJComboBox;

    public KodoviBankeFrame() {
        setTitle("Kodovi Banke");
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

    private void init() {

        jPanel.add(new JLabel("===================== Dodavanje kodova banke ======================="), "wrap");

        jPanel.add(new JLabel("Sifra banke"), "wrap");
        sifraBanke = new JTextField(null, 20);
        jPanel.add(sifraBanke, "wrap");

        jPanel.add(new JLabel("SWIFT kod"), "wrap");
        swiftKod = new JTextField(null, 20);
        jPanel.add(swiftKod, "wrap");

        sacuvaj = new JButton("Sacuvaj");
        jPanel.add(sacuvaj, "wrap");

        jPanel.add(new JLabel("===================== Prikaz kodova iz baze ======================="), "wrap");

        jPanel.add(new JLabel("ID | SIFRA BANKE | SWIFT KOD"), "wrap");
        kodBankeJComboBox = null;
        kodBankeJComboBox = new JComboBox<>();
        for (KodBanke kodBanke : kodBankeService.findAll()) {
            kodBankeJComboBox.addItem(kodBanke);
            jPanel.add(new JLabel(kodBanke.getId() + " | " + kodBanke.getSifraBanke() + " | " + kodBanke.getSWIFTKod()), "wrap");
        }

        obrisi = new JButton("Obrisi");
        jPanel.add(kodBankeJComboBox);
        jPanel.add(obrisi);

        listeners();
    }

    private void listeners() {
        sacuvaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KodBanke kodBanke = new KodBanke();
                kodBanke.setSifraBanke(Integer.parseInt(sifraBanke.getText()));
                kodBanke.setSWIFTKod(swiftKod.getText());
                kodBankeService.save(kodBanke);
                jPanel.removeAll();
                jPanel.revalidate();
                jPanel.repaint();
                init();
            }
        });
        obrisi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KodBanke kodBanke = (KodBanke) kodBankeJComboBox.getSelectedItem();
                kodBankeService.remove(kodBanke.getId());
                jPanel.removeAll();
                jPanel.revalidate();
                jPanel.repaint();
                init();
            }
        });
    }

}
