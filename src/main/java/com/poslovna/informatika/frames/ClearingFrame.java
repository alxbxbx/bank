package com.poslovna.informatika.frames;

import com.poslovna.informatika.configuration.ApplicationContextProvider;
import com.poslovna.informatika.entities.*;
import com.poslovna.informatika.formatters.DateLabelFormatter;
import com.poslovna.informatika.service.*;
import net.miginfocom.swing.MigLayout;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Properties;

public class ClearingFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private RacunPravnogLicaService rplService = (RacunPravnogLicaService) ApplicationContextProvider.getContext().getBean("racunPravnogLicaService");
    private PravnoLiceService plService = (PravnoLiceService) ApplicationContextProvider.getContext().getBean("pravnoLiceService");
    private KodBankeService kbService = (KodBankeService) ApplicationContextProvider.getContext().getBean("kodBankeService");
    private AnalitikaIzvodaService analitikaIzvodaService = (AnalitikaIzvodaService) ApplicationContextProvider.getContext().getBean("analitikaIzvodaService");
    private MedjubankarskiTransferService medjubankarskiTransferService = (MedjubankarskiTransferService) ApplicationContextProvider.getContext().getBean("medjubankarskiTransferService");
    private JPanel jPanel;
    private JScrollPane jScrollPane;
    private JButton proveriRacun, posalji;
    private JComboBox swiftKodBanke;
    private JTextField obracunskiRacunBankeDuznika, duznik, racunDuznika, racunDuznika_0, racunDuznika_1, racunDuznika_2,
            swiftKodBankePoverioca, svrhaPlacanja, primalac, obracunskiRacunBankePoverioca, modelZaduzenja,
            pozivNaBrojZaduzenja, racunPoverioca, modelOdobrenja, pozivNaBrojOdobrenja, iznos, sifraValute;
    private JDatePickerImpl datumNaloga, datumValute;

    public ClearingFrame() {
        setTitle("RTGS / Kliring Forma");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(new Dimension(800, 600));
        setLocationRelativeTo(null);
        jPanel = new JPanel(new MigLayout("fillx"));
        init();
        listeners();
        jScrollPane = new JScrollPane(jPanel);
        add(jScrollPane);
    }

    private void init() {
        jPanel.add(new JLabel("Molimo Vas da popunite formu..."), "wrap");
        jPanel.add(new JLabel(""), "wrap");

        // Racun duznika
        jPanel.add(new JLabel("Primer: 123-123456-123"), "wrap");
        racunDuznika_0 = new JTextField(null, 3);
        racunDuznika_1 = new JTextField(null, 6);
        racunDuznika_2 = new JTextField(null, 3);
        jPanel.add(racunDuznika_0);
        jPanel.add(racunDuznika_1);
        jPanel.add(racunDuznika_2);
        proveriRacun = new JButton("Proveri");
        jPanel.add(proveriRacun, "wrap");

        // PREFILLED: Swift kod
        jPanel.add(new JLabel("SWIFT kod banke:"), "wrap");
        swiftKodBanke = new JComboBox<KodBanke>();
        jPanel.add(swiftKodBanke, "wrap");

        // PREFILLED: Obracunski racun banke duznika
        jPanel.add(new JLabel("Obracunski racun banke duznika:"), "wrap");
        obracunskiRacunBankeDuznika = new JTextField("", 25);
        obracunskiRacunBankeDuznika.setEnabled(false);
        jPanel.add(obracunskiRacunBankeDuznika, "wrap");

        // PREFILLED: Duznik
        jPanel.add(new JLabel("Duznik:"), "wrap");
        duznik = new JTextField("", 25);
        duznik.setEnabled(false);
        jPanel.add(duznik, "wrap");

        // PREFILLED: Racun duznika
        jPanel.add(new JLabel("Racun duznika:"), "wrap");
        racunDuznika = new JTextField("", 25);
        racunDuznika.setEnabled(false);
        jPanel.add(racunDuznika, "wrap");

        // Swift kod banke poverioca
        jPanel.add(new JLabel("SWIFT kod banke poverioca:"), "wrap");
        swiftKodBankePoverioca = new JTextField("", 25);
        jPanel.add(swiftKodBankePoverioca, "wrap");

        // Obracunski racun banke poverioca
        jPanel.add(new JLabel("Obracunski racun banke poverioca:"), "wrap");
        obracunskiRacunBankePoverioca = new JTextField("", 25);
        jPanel.add(obracunskiRacunBankePoverioca, "wrap");

        // Svrha placanja
        jPanel.add(new JLabel("Svrha placanja:"), "wrap");
        svrhaPlacanja = new JTextField("", 25);
        jPanel.add(svrhaPlacanja, "wrap");

        // Primalac
        jPanel.add(new JLabel("Primalac:"), "wrap");
        primalac = new JTextField("", 25);
        jPanel.add(primalac, "wrap");

        // Datum naloga
        jPanel.add(new JLabel("Datum naloga:"), "wrap");
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datumNaloga = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        jPanel.add(datumNaloga, "wrap");

        // Datum valute
        jPanel.add(new JLabel("Datum valute:"), "wrap");
        Properties p2 = new Properties();
        p2.put("text.today", "Today");
        p2.put("text.month", "Month");
        p2.put("text.year", "Year");
        UtilDateModel model2 = new UtilDateModel();
        JDatePanelImpl datePanel2 = new JDatePanelImpl(model2, p2);
        datumValute = new JDatePickerImpl(datePanel2, new DateLabelFormatter());
        jPanel.add(datumValute, "wrap");

        // Model zaduzenja
        jPanel.add(new JLabel("Model zaduzenja:"), "wrap");
        modelZaduzenja = new JTextField("", 2);
        jPanel.add(modelZaduzenja, "wrap");

        // Poziv na broj zaduzenja
        jPanel.add(new JLabel("Poziv na broj zaduzenja:"), "wrap");
        pozivNaBrojZaduzenja = new JTextField("", 20);
        jPanel.add(pozivNaBrojZaduzenja, "wrap");

        // Racun poverioca
        jPanel.add(new JLabel("Racun poverioca:"), "wrap");
        racunPoverioca = new JTextField("", 18);
        jPanel.add(racunPoverioca, "wrap");

        // Model odobrenja
        jPanel.add(new JLabel("Model odobrenja:"), "wrap");
        modelOdobrenja = new JTextField("", 2);
        jPanel.add(modelOdobrenja, "wrap");

        // Poziv na broj odobrenja
        jPanel.add(new JLabel("Poziv na broj odobrenja:"), "wrap");
        pozivNaBrojOdobrenja = new JTextField("", 20);
        jPanel.add(pozivNaBrojOdobrenja, "wrap");

        // Iznos
        jPanel.add(new JLabel("Iznos:"), "wrap");
        iznos = new JTextField("", 15);
        jPanel.add(iznos, "wrap");

        // Sifra valute
        jPanel.add(new JLabel("Sifra valute:"), "wrap");
        sifraValute = new JTextField("", 3);
        jPanel.add(sifraValute);

        jPanel.add(new JLabel(""), "wrap");
        posalji = new JButton("Posalji");
        jPanel.add(posalji);
    }

    private void listeners() {
        posalji.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AnalitikaIzvoda analitikaIzvoda = new AnalitikaIzvoda();
                analitikaIzvodaService.save(analitikaIzvoda);

                MedjubankarskiTransfer medjubankarskiTransfer = new MedjubankarskiTransfer();
                medjubankarskiTransfer.setTip("clearing");
                medjubankarskiTransfer.setAnalitikaIzvoda(analitikaIzvoda);

                medjubankarskiTransferService.save(medjubankarskiTransfer);
            }
        });
        proveriRacun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RacunPravnogLica rpl = rplService.findByBrojRacuna(racunDuznika_0.getText() + "-" + racunDuznika_1.getText() + "-" + racunDuznika_2.getText());
                for (KodBanke kb : kbService.findAll()) {
                    swiftKodBanke.addItem(kb);
                }
                obracunskiRacunBankeDuznika.setText("rply.getSMTH()");
                duznik.setText(rpl.getPravnoLice().getNaziv().toString());
                racunDuznika.setText(rpl.getBrojRacuna().toString());
            }
        });
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

}
