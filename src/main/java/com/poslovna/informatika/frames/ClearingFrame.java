package com.poslovna.informatika.frames;

import com.poslovna.informatika.configuration.ApplicationContextProvider;
import com.poslovna.informatika.entities.*;
import com.poslovna.informatika.formatters.DateLabelFormatter;
import com.poslovna.informatika.service.*;
import net.miginfocom.swing.MigLayout;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.Properties;

public class ClearingFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private RacunPravnogLicaService rplService = (RacunPravnogLicaService) ApplicationContextProvider.getContext().getBean("racunPravnogLicaService");
    private PravnoLiceService plService = (PravnoLiceService) ApplicationContextProvider.getContext().getBean("pravnoLiceService");
    private KodBankeService kbService = (KodBankeService) ApplicationContextProvider.getContext().getBean("kodBankeService");
    private AnalitikaIzvodaService analitikaIzvodaService = (AnalitikaIzvodaService) ApplicationContextProvider.getContext().getBean("analitikaIzvodaService");
    private DnevnoStanjeRacunaService dnevnoStanjeRacunaService = (DnevnoStanjeRacunaService) ApplicationContextProvider.getContext().getBean("dnevnoStanjeRacunaService");
    private MedjubankarskiTransferService medjubankarskiTransferService = (MedjubankarskiTransferService) ApplicationContextProvider.getContext().getBean("medjubankarskiTransferService");
    private JPanel jPanel;
    private JScrollPane jScrollPane;
    private JButton proveriRacun, posalji;
    private JComboBox swiftKodBanke;
    private JTextField obracunskiRacunBankeDuznika, duznik, racunDuznika, racunDuznika_0, racunDuznika_1, racunDuznika_2,
            swiftKodBankePoverioca, svrhaPlacanja, primalac, obracunskiRacunBankePoverioca, modelZaduzenja,
            pozivNaBrojZaduzenja, racunPoverioca, modelOdobrenja, pozivNaBrojOdobrenja, iznos, sifraValute, brojStavke,
            nalogodavac, smer, status;
    private JDatePickerImpl datumNaloga, datumValute;
    private JCheckBox hitno;
    private RacunPravnogLica racunPravnogLica;

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
        jPanel.add(new JLabel("Molimo Vas da popunite formu, sva polja su neophodna za izvrsenje transakcije."), "span 4, wrap");
        jPanel.add(new JLabel(""), "wrap");

        jPanel.add(new JLabel("==========================================================================================================="), "span 4, wrap");
        // Racun duznika
        jPanel.add(new JLabel("Unesite racun pravnog lica (npr. 123 123456 123)"), "span 4, wrap");
        racunDuznika_0 = new JTextField(null, 15);
        racunDuznika_1 = new JTextField(null, 15);
        racunDuznika_2 = new JTextField(null, 15);
        jPanel.add(racunDuznika_0);
        jPanel.add(racunDuznika_1);
        jPanel.add(racunDuznika_2);
        proveriRacun = new JButton("Proveri");
        jPanel.add(proveriRacun, "wrap");

        jPanel.add(new JLabel("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"), "span 4, wrap");

        jPanel.add(new JLabel("Racun duznika"));
        jPanel.add(new JLabel("Obracunski racun banke duznika"));
        jPanel.add(new JLabel("Duznik"));
        jPanel.add(new JLabel("SWIFT kod banke"), "wrap");

        // PREFILLED: Obracunski racun banke duznika
        obracunskiRacunBankeDuznika = new JTextField("", 15);
        obracunskiRacunBankeDuznika.setEnabled(false);
        jPanel.add(obracunskiRacunBankeDuznika);

        // PREFILLED: Duznik
        duznik = new JTextField("", 15);
        duznik.setEnabled(false);
        jPanel.add(duznik);

        // PREFILLED: Racun duznika
        racunDuznika = new JTextField("", 15);
        racunDuznika.setEnabled(false);
        jPanel.add(racunDuznika);

        // PREFILLED TO PICK: Swift kod
        swiftKodBanke = new JComboBox<KodBanke>();
        jPanel.add(swiftKodBanke, "wrap");

        jPanel.add(new JLabel("==========================================================================================================="), "span 4, wrap");

        // Swift kod banke poverioca
        jPanel.add(new JLabel("SWIFT kod banke poverioca:"), "wrap");
        swiftKodBankePoverioca = new JTextField("", 15);
        jPanel.add(swiftKodBankePoverioca, "wrap");

        // Obracunski racun banke poverioca
        jPanel.add(new JLabel("Obracunski racun banke poverioca:"), "wrap");
        obracunskiRacunBankePoverioca = new JTextField("", 15);
        jPanel.add(obracunskiRacunBankePoverioca, "wrap");

        // Svrha placanja
        jPanel.add(new JLabel("Svrha placanja:"), "wrap");
        svrhaPlacanja = new JTextField("", 15);
        jPanel.add(svrhaPlacanja, "wrap");

        // Primalac
        jPanel.add(new JLabel("Primalac:"), "wrap");
        primalac = new JTextField("", 15);
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
        pozivNaBrojZaduzenja = new JTextField("", 15);
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
        pozivNaBrojOdobrenja = new JTextField("", 15);
        jPanel.add(pozivNaBrojOdobrenja, "wrap");

        // Iznos
        jPanel.add(new JLabel("Iznos:"), "wrap");
        iznos = new JTextField("", 15);
        jPanel.add(iznos, "wrap");

        // Sifra valute
        jPanel.add(new JLabel("Sifra valute:"), "wrap");
        sifraValute = new JTextField("", 3);
        jPanel.add(sifraValute, "wrap");

        // Broj stavke
        jPanel.add(new JLabel("Broj stavke:"), "wrap");
        brojStavke = new JTextField("", 15);
        jPanel.add(brojStavke, "wrap");

        // Hitno
        hitno = new JCheckBox("Hitno");
        jPanel.add(hitno, "wrap");

        // Nalogodavac
        jPanel.add(new JLabel("Nalogodavac"), "wrap");
        nalogodavac = new JTextField("", 15);
        jPanel.add(nalogodavac, "wrap");

        // Smer
        jPanel.add(new JLabel("Smer"), "wrap");
        smer = new JTextField("", 15);
        jPanel.add(smer, "wrap");

        // Status
        jPanel.add(new JLabel("Status"), "wrap");
        status = new JTextField("", 15);
        jPanel.add(status, "wrap");

        // Posalji
        jPanel.add(new JLabel(""), "wrap");
        posalji = new JButton("Posalji");
        jPanel.add(posalji);
    }

    private void listeners() {
        posalji.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Proveri da li je racun pravnog lica odabran
                if (racunPravnogLica == null) {
                    JOptionPane.showMessageDialog(null, "Racun pravnog lica nije odabran.");
                    return;
                }

                // Dnevno stanje racuna
                // Uzimamo poslednje, uvek ce postojati bar jedno dnevno stanje jer se ono kreira pri inicijalizaciji
                // samog racuna, nema potrebe za proveravanjem da li je null ili ne.
                DnevnoStanjeRacuna poslednjeDnevnoStanjeRacuna = dnevnoStanjeRacunaService.findTop1ByRacunPravnogLicaIdOrderByDatumPrometaDesc(7);
                double novoStanje = poslednjeDnevnoStanjeRacuna.getNovoStanje() - Double.parseDouble(iznos.getText());
                if (novoStanje < 0) {
                    JOptionPane.showMessageDialog(null, "Nema dovoljno novca na racunu.");
                    return;
                }
                DnevnoStanjeRacuna dnevnoStanjeRacuna = new DnevnoStanjeRacuna();
                dnevnoStanjeRacuna.setBrojIzvoda(0); // wtf
                dnevnoStanjeRacuna.setDatumPrometa(new Date());
                dnevnoStanjeRacuna.setNovoStanje(novoStanje);
                dnevnoStanjeRacuna.setPrethodnoStanje(poslednjeDnevnoStanjeRacuna.getNovoStanje());
                dnevnoStanjeRacuna.setPrometNaTeret(Double.parseDouble(iznos.getText()));
                dnevnoStanjeRacuna.setPrometUKorist(0);
                dnevnoStanjeRacuna.setRacunPravnogLica(racunPravnogLica);
                dnevnoStanjeRacunaService.save(dnevnoStanjeRacuna);

                // Analitika izvoda
                AnalitikaIzvoda analitikaIzvoda = new AnalitikaIzvoda();
                analitikaIzvoda.setBrojStavke(Integer.parseInt(brojStavke.getText()));
                Date date1 = (Date) datumNaloga.getModel().getValue();
                analitikaIzvoda.setDatumPrijema(date1);
                Date date2 = (Date) datumValute.getModel().getValue();
                analitikaIzvoda.setDatumValute(date2);
                analitikaIzvoda.setHitno(hitno.isSelected());
                analitikaIzvoda.setIznos(Integer.parseInt(iznos.getText()));
                analitikaIzvoda.setModelOdobrenja(Integer.parseInt(modelOdobrenja.getText()));
                analitikaIzvoda.setModelZaduzenja(Integer.parseInt(modelZaduzenja.getText()));
                analitikaIzvoda.setNalogodavac(nalogodavac.getText());
                analitikaIzvoda.setPozivNaBrojOdobrenja(pozivNaBrojOdobrenja.getText());
                analitikaIzvoda.setPozivNaBrojZaduzenja(pozivNaBrojZaduzenja.getText());
                analitikaIzvoda.setPrimalac(primalac.getText());
                analitikaIzvoda.setRacunDuznika(racunDuznika.getText());
                analitikaIzvoda.setRacunPoverioca(racunPoverioca.getText());
                analitikaIzvoda.setSmer(smer.getText());
                analitikaIzvoda.setStatus(status.getText());
                analitikaIzvoda.setSvrhaPlacanja(svrhaPlacanja.getText());
                analitikaIzvoda.setTipGreske(0); //
                /*
                analitikaIzvoda.setDnevnoStanjeRacuna(?);
                analitikaIzvoda.setNaseljenoMesto(?);
                analitikaIzvoda.setValuta(?);
                analitikaIzvoda.setVrstaPlacanja(?);
                analitikaIzvodaService.save(analitikaIzvoda);
                */

                // Medjubankarski Transfer
                MedjubankarskiTransfer medjubankarskiTransfer = new MedjubankarskiTransfer();
                medjubankarskiTransfer.setTip("clearing");
                medjubankarskiTransfer.setAnalitikaIzvoda(analitikaIzvoda);
                medjubankarskiTransferService.save(medjubankarskiTransfer);
            }
        });
        proveriRacun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                racunPravnogLica = rplService.findByBrojRacuna(racunDuznika_0.getText() + "-" + racunDuznika_1.getText() + "-" + racunDuznika_2.getText());
                for (KodBanke kb : kbService.findAll()) {
                    swiftKodBanke.addItem(kb);
                }
                obracunskiRacunBankeDuznika.setText("rply.getSMTH()");
                duznik.setText(racunPravnogLica.getPravnoLice().getNaziv().toString());
                racunDuznika.setText(racunPravnogLica.getBrojRacuna().toString());
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
