package com.poslovna.informatika.frames;

import com.poslovna.informatika.configuration.ApplicationContextProvider;
import com.poslovna.informatika.entities.PravnoLice;
import com.poslovna.informatika.entities.RacunPravnogLica;
import com.poslovna.informatika.service.PravnoLiceService;
import com.poslovna.informatika.service.RacunPravnogLicaService;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GenerisanjePrenosaIzvodaFrame extends JFrame {

    private PravnoLiceService pravnoLiceService = (PravnoLiceService) ApplicationContextProvider.getContext().getBean("pravnoLiceService");
    private RacunPravnogLicaService racunPravnogLicaService = (RacunPravnogLicaService) ApplicationContextProvider.getContext().getBean("racunPravnogLicaService");
    private JComboBox<PravnoLice> pravnoLiceJComboBox;
    private JComboBox<RacunPravnogLica> racunPravnogLicaJComboBox;
    private JPanel jPanel;
    private JScrollPane jScrollPane;

    public GenerisanjePrenosaIzvodaFrame() {
        setTitle("Generisanje prenosa izvoda");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(new Dimension(800, 600));
        setLocationRelativeTo(null);
        jPanel = new JPanel(new MigLayout("fillx"));
        init();
        listeners();
        jScrollPane = new JScrollPane(jPanel);
        add(jScrollPane);
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
        jPanel.add(new JLabel("=== DETALJI ==============================================================================================="), "span 3, wrap");

        jPanel.add(new JLabel("Pravno lice"));
        pravnoLiceJComboBox = new JComboBox<PravnoLice>();
        for (PravnoLice pl : pravnoLiceService.findAll()) {
            pravnoLiceJComboBox.addItem(pl);
        }
        jPanel.add(pravnoLiceJComboBox, "wrap");

        jPanel.add(new JLabel("Racun pravnog lica"));
        racunPravnogLicaJComboBox = new JComboBox<RacunPravnogLica>();
        jPanel.add(racunPravnogLicaJComboBox, "wrap");

    }

    private void listeners() {
        pravnoLiceJComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PravnoLice pl = (PravnoLice) pravnoLiceJComboBox.getSelectedItem();
                racunPravnogLicaJComboBox.removeAllItems();
                for (RacunPravnogLica rpl : racunPravnogLicaService.findByPravnoLiceId(pl.getId())) {
                    racunPravnogLicaJComboBox.addItem(rpl);
                }
            }
        });
    }

}
