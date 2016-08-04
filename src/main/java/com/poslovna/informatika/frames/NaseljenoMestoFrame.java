package com.poslovna.informatika.frames;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.poslovna.informatika.configuration.ApplicationContextProvider;
import com.poslovna.informatika.entities.Drzava;
import com.poslovna.informatika.entities.NaseljenoMesto;
import com.poslovna.informatika.service.DrzavaService;
import com.poslovna.informatika.service.NaseljenoMestoService;

import net.miginfocom.swing.MigLayout;

public class NaseljenoMestoFrame extends JFrame {
    private static final long serialVersionUID = -5561865287839844621L;
    private DrzavaService drzavaService = (DrzavaService) ApplicationContextProvider.getContext().getBean("drzavaService");
    private NaseljenoMestoService nmService = (NaseljenoMestoService) ApplicationContextProvider.getContext().getBean("naseljenoMestoService");
    private JPanel jPanel;
    private JTextField sifraMesta;
    private JTextField nazivMesta;
    private JTextField pttOznaka;
    private JButton dodajMesto;
    private JButton obrisiMesto;
    private JComboBox<Drzava> drzave;
    private JComboBox<NaseljenoMesto> mesta;
    private List<NaseljenoMesto> naseljenaMesta;

    public NaseljenoMestoFrame() {
        setTitle("Naseljeno mesto");
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
        naseljenaMesta = nmService.findAll();

        List<Drzava> listaDrzava = drzavaService.findAll();
        drzave = new JComboBox<Drzava>();
        for (Drzava drzava : listaDrzava) {
            drzave.addItem(drzava);
        }
        mesta = new JComboBox<NaseljenoMesto>();
        for (NaseljenoMesto mesto : naseljenaMesta) {
            mesta.addItem(mesto);
        }

        sifraMesta = new JTextField(null, 3);
        nazivMesta = new JTextField(null, 9);
        pttOznaka = new JTextField(null, 6);
        dodajMesto = new JButton("Dodaj mesto");
        obrisiMesto = new JButton("Obrisi mesto");

        jPanel.add(new JLabel("Sifra naseljenog mesta:"));
        jPanel.add(sifraMesta);
        jPanel.add(new JLabel("Naziv naseljenog mesta:"));
        jPanel.add(nazivMesta, "wrap");
        jPanel.add(new JLabel("PTT oznaka:"));
        jPanel.add(pttOznaka);
        jPanel.add(drzave);
        jPanel.add(dodajMesto, "wrap");

        jPanel.add(new JLabel("Lista naseljenih mesta: "), "wrap");
        jPanel.add(new JLabel(""), "wrap");
        jPanel.add(new JLabel("Sifra mesta"));
        jPanel.add(new JLabel("Naziv mesta"));
        jPanel.add(new JLabel("PTT oznaka"), "wrap");

        for (NaseljenoMesto nm : naseljenaMesta) {
            JTextField sifra = new JTextField(nm.getSifraMesta(), 3);
            JTextField naziv = new JTextField(nm.getNaziv(), 9);
            JTextField ptt = new JTextField(nm.getPTTOznaka(), 6);
            sifra.setEditable(false);
            naziv.setEditable(false);
            ptt.setEditable(false);
            jPanel.add(sifra);
            jPanel.add(naziv);
            jPanel.add(ptt, "wrap");
        }

        jPanel.add(new JLabel(""), "wrap");
        jPanel.add(new JLabel(""), "wrap");
        jPanel.add(mesta);
        jPanel.add(obrisiMesto, "wrap");

        dodajMesto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!sifraMesta.getText().equals("") && !nazivMesta.getText().equals("") && !pttOznaka.getText().equals("")) {
                    NaseljenoMesto naseljenoMesto = new NaseljenoMesto();
                    naseljenoMesto.setSifraMesta(sifraMesta.getText());
                    naseljenoMesto.setNaziv(nazivMesta.getText());
                    naseljenoMesto.setPTTOznaka(pttOznaka.getText());

                    //Drzava drzava = drzavaService.findByNazivDrzave(drzave.getSelectedItem().toString());
                    Drzava drzava = (Drzava) drzave.getSelectedItem();
                    naseljenoMesto.setDrzava(drzava);
                    drzava.getNaseljenaMesta().add(naseljenoMesto);

                    nmService.save(naseljenoMesto);
                    drzavaService.save(drzava);

                    jPanel.removeAll();
                    jPanel.revalidate();
                    jPanel.repaint();

                    init();
                }
            }

        });

        obrisiMesto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                NaseljenoMesto nm = (NaseljenoMesto) mesta.getSelectedItem();
                nm.getDrzava().getNaseljenaMesta().remove(nm);
                drzavaService.save(nm.getDrzava());
                nmService.remove(nm.getId());

                jPanel.removeAll();
                jPanel.revalidate();
                jPanel.repaint();

                init();
            }

        });
    }

}
