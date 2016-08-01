package com.poslovna.informatika.frames;

import com.poslovna.informatika.configuration.ApplicationContextProvider;
import com.poslovna.informatika.entities.KodBanke;
import com.poslovna.informatika.entities.RacunPravnogLica;
import com.poslovna.informatika.service.RacunPravnogLicaService;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ClearingFrame extends JFrame {

    private RacunPravnogLicaService rplService = (RacunPravnogLicaService) ApplicationContextProvider.getContext().getBean("racunPravnogLicaService");
    private JPanel jPanel;
    private JTextField racunDuznika_0;
    private JTextField racunDuznika_1;
    private JTextField racunDuznika_2;
    private JButton proveriRacun;
    private JComboBox swiftKodBanke;
    private JTextField obracunskiRacunBankeDuznika;
    private JTextField duznik;
    private JTextField racunDuznika;

    public ClearingFrame() {
        setTitle("RTGS / Kliring Forma");
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
        jPanel.add(new JLabel("Molimo Vas da popunite formu..."), "wrap");
        jPanel.add(new JLabel(""), "wrap");
        jPanel.add(new JLabel("Primer: 123-123456-123"), "wrap");
        racunDuznika_0 = new JTextField(null, 3);
        racunDuznika_1 = new JTextField(null, 6);
        racunDuznika_2 = new JTextField(null, 3);
        jPanel.add(racunDuznika_0);
        jPanel.add(racunDuznika_1);
        jPanel.add(racunDuznika_2);
        proveriRacun = new JButton("Proveri");
        proveriRacun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RacunPravnogLica rpl = rplService.findByBrojRacuna(racunDuznika_0.getText() + "-" + racunDuznika_1.getText() + "-" + racunDuznika_2.getText());

                jPanel.add(new JLabel("SWIFT kod banke:"), "wrap");
                String[] swifts = new String[rpl.getPravnoLice().getKodoviBanke().size()];
                int i = 0;
                for (KodBanke kodBanke : rpl.getPravnoLice().getKodoviBanke()) {
                    swifts[i] = kodBanke.getSWIFTKod().toString();
                    i++;
                }
                swiftKodBanke = new JComboBox(swifts);
                jPanel.add(swiftKodBanke, "wrap");

                jPanel.add(new JLabel("Obracunski racun banke duznika:"), "wrap");
                obracunskiRacunBankeDuznika = new JTextField("x", 25);
                obracunskiRacunBankeDuznika.setEnabled(false);
                jPanel.add(obracunskiRacunBankeDuznika, "wrap");

                jPanel.add(new JLabel("Duznik:"), "wrap");
                duznik = new JTextField(rpl.getPravnoLice().getNaziv(), 25);
                duznik.setEnabled(false);
                jPanel.add(duznik, "wrap");

                jPanel.add(new JLabel("Racun duznika:"), "wrap");
                racunDuznika = new JTextField(rpl.getBrojRacuna(), 25);
                racunDuznika.setEnabled(false);
                jPanel.add(racunDuznika, "wrap");
            }
        });
        jPanel.add(proveriRacun, "wrap");

    }

}
