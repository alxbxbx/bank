package com.poslovna.informatika.frames;

import com.poslovna.informatika.configuration.ApplicationContextProvider;
import com.poslovna.informatika.entities.DnevnoStanjeRacuna;
import com.poslovna.informatika.entities.PravnoLice;
import com.poslovna.informatika.entities.RacunPravnogLica;
import com.poslovna.informatika.entities.Valuta;
import com.poslovna.informatika.service.PravnoLiceService;
import com.poslovna.informatika.service.RacunPravnogLicaService;
import com.poslovna.informatika.service.ValutaService;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

public class RacunPravnogLicaFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private PravnoLiceService pravnoLiceService = (PravnoLiceService) ApplicationContextProvider.getContext().getBean("pravnoLiceService");
    private ValutaService valutaService = (ValutaService) ApplicationContextProvider.getContext().getBean("valutaService");
    private RacunPravnogLicaService racunPravnogLicaService = (RacunPravnogLicaService) ApplicationContextProvider.getContext().getBean("racunPravnogLicaService");
    private JPanel jPanel;
    private JTextField uplata;
    private JScrollPane jScrollPane;
    private JComboBox<PravnoLice> pravnaLica;
    private JComboBox<Valuta> valute;
    private JButton sacuvaj;

    public RacunPravnogLicaFrame() {
        setTitle("");
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

        jPanel.add(new JLabel("Kreiranje racuna pravnih lica."), "wrap");
        jPanel.add(new JLabel(""), "wrap");

        // Pravna Lica
        jPanel.add(new JLabel("Pravno lice"), "wrap");
        pravnaLica = new JComboBox<>();
        for (PravnoLice pravnoLice : pravnoLiceService.findAll()) {
            pravnaLica.addItem(pravnoLice);
        }
        jPanel.add(pravnaLica, "wrap");

        // Valute
        jPanel.add(new JLabel("Valuta"), "wrap");
        valute = new JComboBox<>();
        for (Valuta valuta : valutaService.findAll()) {
            valute.addItem(valuta);
        }
        jPanel.add(valute, "wrap");

        // Inicijalna uplata
        jPanel.add(new JLabel("Inicijalna uplata"), "wrap");
        uplata = new JTextField(null, 20);
        jPanel.add(uplata, "wrap");

        sacuvaj = new JButton("Sacuvaj");
        jPanel.add(sacuvaj, "wrap");

        listeners();
    }

    private void listeners() {
        sacuvaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RacunPravnogLica racunPravnogLica = new RacunPravnogLica();
                Valuta v1 = (Valuta) valute.getSelectedItem();
                racunPravnogLica.setValuta(v1);
                racunPravnogLica.setDatumOtvaranja(new Date());
                racunPravnogLica.setVazeci(true);
                PravnoLice p1 = (PravnoLice) pravnaLica.getSelectedItem();
                racunPravnogLica.setPravnoLice(p1);
                racunPravnogLicaService.save(racunPravnogLica);
                racunPravnogLica.setBrojRacuna(racunPravnogLica.getId().toString());
                racunPravnogLicaService.save(racunPravnogLica);

                DnevnoStanjeRacuna dnevnoStanjeRacuna = new DnevnoStanjeRacuna();
                dnevnoStanjeRacuna.setDatumPrometa(new Date());
                // dnevnoStanjeRacuna.setBrojIzvoda();
                dnevnoStanjeRacuna.setRacunPravnogLica(racunPravnogLica);
                dnevnoStanjeRacuna.setPrethodnoStanje(0);
                dnevnoStanjeRacuna.setPrometUKorist(Double.parseDouble(uplata.getText()));
                dnevnoStanjeRacuna.setPrometNaTeret(0);
                dnevnoStanjeRacuna.setNovoStanje(0);
                java.util.List<DnevnoStanjeRacuna> dnevnoStanjeRacunaList = new java.util.ArrayList<>();
                dnevnoStanjeRacunaList.add(dnevnoStanjeRacuna);
            }
        });
    }

}
