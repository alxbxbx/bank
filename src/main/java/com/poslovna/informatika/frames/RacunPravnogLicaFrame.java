package com.poslovna.informatika.frames;

import com.poslovna.informatika.configuration.ApplicationContextProvider;
import com.poslovna.informatika.entities.DnevnoStanjeRacuna;
import com.poslovna.informatika.entities.PravnoLice;
import com.poslovna.informatika.entities.RacunPravnogLica;
import com.poslovna.informatika.entities.Valuta;
import com.poslovna.informatika.service.DnevnoStanjeRacunaService;
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
    private DnevnoStanjeRacunaService dnevnoStanjeRacunaService = (DnevnoStanjeRacunaService) ApplicationContextProvider.getContext().getBean("dnevnoStanjeRacunaService");
    private PravnoLiceService pravnoLiceService = (PravnoLiceService) ApplicationContextProvider.getContext().getBean("pravnoLiceService");
    private ValutaService valutaService = (ValutaService) ApplicationContextProvider.getContext().getBean("valutaService");
    private RacunPravnogLicaService racunPravnogLicaService = (RacunPravnogLicaService) ApplicationContextProvider.getContext().getBean("racunPravnogLicaService");
    private JPanel jPanel;
    private JTextField uplata, brojRacunaToDelete;
    private JScrollPane jScrollPane;
    private JComboBox<PravnoLice> pravnaLica;
    private JComboBox<Valuta> valute;
    private JButton sacuvaj, obrisi;

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

        jPanel.add(new JLabel("========================= Kreiranje racuna pravnih lica ========================= "), "wrap");
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

        jPanel.add(new JLabel("============================ Pregled racuna pravnih lica ============================ "), "wrap");
        jPanel.add(new JLabel("BROJ RACUNA | PRAVNO LICE | VALUTA"), "wrap");
        java.util.List<RacunPravnogLica> racuniPravnihLica = racunPravnogLicaService.findByVazeci(true);
        for (RacunPravnogLica r : racuniPravnihLica) {
            jPanel.add(new JLabel(r.getBrojRacuna() + " | " + r.getPravnoLice().getNaziv() + " | " + r.getValuta().getNaziv()), "wrap");
        }
        brojRacunaToDelete = new JTextField(null, 15);
        obrisi = new JButton("Obrisi");
        jPanel.add(brojRacunaToDelete);
        jPanel.add(obrisi);

        listeners();
    }

    private void listeners() {
        sacuvaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (uplata.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Inicijalna uplata je obavezna.");
                } else {
                    RacunPravnogLica racunPravnogLica = new RacunPravnogLica();
                    Valuta v1 = (Valuta) valute.getSelectedItem();
                    racunPravnogLica.setValuta(v1);
                    racunPravnogLica.setDatumOtvaranja(new Date());
                    racunPravnogLica.setVazeci(true);
                    PravnoLice p1 = (PravnoLice) pravnaLica.getSelectedItem();
                    racunPravnogLica.setPravnoLice(p1);
                    racunPravnogLicaService.save(racunPravnogLica);
                    racunPravnogLica.setBrojRacuna("1-" + racunPravnogLica.getId().toString() + "-430");
                    racunPravnogLicaService.save(racunPravnogLica);

                    DnevnoStanjeRacuna dnevnoStanjeRacuna = new DnevnoStanjeRacuna();
                    dnevnoStanjeRacuna.setDatumPrometa(new Date());
                    dnevnoStanjeRacuna.setBrojIzvoda(0);
                    dnevnoStanjeRacuna.setRacunPravnogLica(racunPravnogLica);
                    dnevnoStanjeRacuna.setPrethodnoStanje(0);
                    dnevnoStanjeRacuna.setPrometUKorist(Double.parseDouble(uplata.getText()));
                    dnevnoStanjeRacuna.setPrometNaTeret(0);
                    dnevnoStanjeRacuna.setNovoStanje(Double.parseDouble(uplata.getText()));
                    dnevnoStanjeRacuna.setRacunPravnogLica(racunPravnogLica);
                    dnevnoStanjeRacunaService.save(dnevnoStanjeRacuna);

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
                racunPravnogLicaService.remove(Integer.parseInt(brojRacunaToDelete.getText()));

                jPanel.removeAll();
                jPanel.revalidate();
                jPanel.repaint();
                init();
            }
        });
    }

}
