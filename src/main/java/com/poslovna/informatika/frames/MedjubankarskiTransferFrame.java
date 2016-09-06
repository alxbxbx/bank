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
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

public class MedjubankarskiTransferFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private VrstaPlacanjaService vrstaPlacanjaService = (VrstaPlacanjaService) ApplicationContextProvider.getContext().getBean("vrstaPlacanjaService");
    private NaseljenoMestoService naseljenoMestoService = (NaseljenoMestoService) ApplicationContextProvider.getContext().getBean("naseljenoMestoService");
    private DrzavaService drzavaService = (DrzavaService) ApplicationContextProvider.getContext().getBean("drzavaService");
    private ValutaService valutaService = (ValutaService) ApplicationContextProvider.getContext().getBean("valutaService");
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
    private JComboBox<Valuta> valute;
    private JComboBox<Drzava> drzave;
    private JComboBox<NaseljenoMesto> mesta;
    private JComboBox<VrstaPlacanja> vrstaPlacanja;
    private JTextField duznik, racunDuznika, racunDuznika_0, racunDuznika_1, racunDuznika_2,
            swiftKodBankePoverioca, svrhaPlacanja, primalac, modelZaduzenja,
            pozivNaBrojZaduzenja, racunPoverioca, modelOdobrenja, pozivNaBrojOdobrenja, iznos;
    private JDatePickerImpl datumNaloga, datumValute;
    private JCheckBox hitno;
    private RacunPravnogLica racunPravnogLica;
    private boolean isRTGS = false;

    public MedjubankarskiTransferFrame(boolean isRTGS) {
        if (isRTGS) {
            this.isRTGS = true;
        }
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

        jPanel.add(new JLabel("=== TIP PORUKE U PLATNOM PROMETU =========================================================================="), "span 3, wrap");

        if (isRTGS) {
            jPanel.add(new JLabel("MT 103 | Nalog za bezgotovinsko plaćanje (RTGS)"), "span 3, wrap");
        } else {
            jPanel.add(new JLabel("MT 102 | Plaćanja u kliringu"), "span 3, wrap");
        }

        jPanel.add(new JLabel("=== RACUN DUZNIKA ========================================================================================="), "span 3, wrap");

        jPanel.add(new JLabel("Unesite racun duznika (npr. 123-123456789-12)"), "span 3, wrap");
        racunDuznika_0 = new JTextField(null, 3);
        racunDuznika_1 = new JTextField(null, 15);
        racunDuznika_2 = new JTextField(null, 3);
        proveriRacun = new JButton("Proveri");
        jPanel.add(racunDuznika_0);
        jPanel.add(racunDuznika_1);
        jPanel.add(racunDuznika_2, "wrap");
        jPanel.add(proveriRacun, "wrap");

        jPanel.add(new JLabel("=== DUZNIK ================================================================================================"), "span 3, wrap");

        jPanel.add(new JLabel("Naziv"));
        duznik = new JTextField("", 15);
        duznik.setEnabled(false);
        jPanel.add(duznik, "wrap");

        jPanel.add(new JLabel("Racun"));
        racunDuznika = new JTextField("", 15);
        racunDuznika.setEnabled(false);
        jPanel.add(racunDuznika, "wrap");

        jPanel.add(new JLabel("SWIFT"));
        swiftKodBanke = new JComboBox<KodBanke>();
        jPanel.add(swiftKodBanke, "wrap");

        jPanel.add(new JLabel("Model"));
        jPanel.add(new JLabel("Poziv na broj"), "wrap");
        modelZaduzenja = new JTextField("", 2);
        pozivNaBrojZaduzenja = new JTextField("", 15);
        jPanel.add(modelZaduzenja);
        jPanel.add(pozivNaBrojZaduzenja, "wrap");

        jPanel.add(new JLabel("=== POVERILAC ============================================================================================="), "span 3, wrap");


        jPanel.add(new JLabel("Naziv"));
        primalac = new JTextField("", 15);
        jPanel.add(primalac, "wrap");

        jPanel.add(new JLabel("Racun"));
        racunPoverioca = new JTextField("", 15);
        jPanel.add(racunPoverioca, "wrap");

        jPanel.add(new JLabel("SWIFT"));
        swiftKodBankePoverioca = new JTextField("", 15);
        jPanel.add(swiftKodBankePoverioca, "wrap");

        jPanel.add(new JLabel("Model:"));
        jPanel.add(new JLabel("Poziv na broj:"), "wrap");
        modelOdobrenja = new JTextField("", 2);
        pozivNaBrojOdobrenja = new JTextField("", 15);
        jPanel.add(modelOdobrenja);
        jPanel.add(pozivNaBrojOdobrenja, "wrap");

        jPanel.add(new JLabel("=== LOKACIJA =============================================================================================="), "span 3, wrap");

        jPanel.add(new JLabel("Drzava"));
        jPanel.add(new JLabel("Naseljeno mesto"), "wrap");
        drzave = new JComboBox<Drzava>();
        for (Drzava d : drzavaService.findAll()) {
            drzave.addItem(d);
        }
        mesta = new JComboBox<NaseljenoMesto>();
        jPanel.add(drzave);
        jPanel.add(mesta, "wrap");

        jPanel.add(new JLabel("=== DETALJI TRANSFERA ====================================================================================="), "span 3, wrap");

        jPanel.add(new JLabel("Datum naloga"));
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datumNaloga = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        jPanel.add(datumNaloga, "wrap");

        jPanel.add(new JLabel("Svrha uplate"));
        svrhaPlacanja = new JTextField("", 15);
        jPanel.add(svrhaPlacanja, "wrap");

        jPanel.add(new JLabel("Datum i sifra valute"));
        Properties p2 = new Properties();
        p2.put("text.today", "Today");
        p2.put("text.month", "Month");
        p2.put("text.year", "Year");
        UtilDateModel model2 = new UtilDateModel();
        JDatePanelImpl datePanel2 = new JDatePanelImpl(model2, p2);
        datumValute = new JDatePickerImpl(datePanel2, new DateLabelFormatter());
        jPanel.add(datumValute);
        valute = new JComboBox<Valuta>();
        for (Valuta v : valutaService.findAll()) {
            valute.addItem(v);
        }
        jPanel.add(valute, "wrap");

        jPanel.add(new JLabel("Iznos (hitno)"));
        iznos = new JTextField("", 15);
        jPanel.add(iznos);
        hitno = new JCheckBox("Hitno");
        jPanel.add(hitno, "wrap");

        jPanel.add(new JLabel("Vrsta placanja"));
        vrstaPlacanja = new JComboBox<VrstaPlacanja>();
        for (VrstaPlacanja vp : vrstaPlacanjaService.findAll()) {
            vrstaPlacanja.addItem(vp);
        }
        jPanel.add(vrstaPlacanja, "wrap");

        posalji = new JButton("Posalji");
        jPanel.add(posalji);
    }

    private void listeners() {
        posalji.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Dnevno stanje racuna
                // Uzimamo poslednje, uvek ce postojati bar jedno dnevno stanje jer se ono kreira pri inicijalizaciji
                // samog racuna, nema potrebe za proveravanjem da li je null ili ne.
                DnevnoStanjeRacuna poslednjeDnevnoStanjeRacuna = dnevnoStanjeRacunaService.findTop1ByRacunPravnogLicaIdOrderByDatumPrometaDesc(7);
                double novoStanje = poslednjeDnevnoStanjeRacuna.getNovoStanje() - Double.parseDouble(iznos.getText());
                DnevnoStanjeRacuna dnevnoStanjeRacuna = null;
                if (novoStanje >= 0) {
                    dnevnoStanjeRacuna = new DnevnoStanjeRacuna();
                    dnevnoStanjeRacuna.setBrojIzvoda(0); // wtf
                    dnevnoStanjeRacuna.setDatumPrometa(new Date());
                    dnevnoStanjeRacuna.setNovoStanje(novoStanje);
                    dnevnoStanjeRacuna.setPrethodnoStanje(poslednjeDnevnoStanjeRacuna.getNovoStanje());
                    dnevnoStanjeRacuna.setPrometNaTeret(Double.parseDouble(iznos.getText()));
                    dnevnoStanjeRacuna.setPrometUKorist(0);
                    dnevnoStanjeRacuna.setRacunPravnogLica(racunPravnogLica);
                    dnevnoStanjeRacunaService.save(dnevnoStanjeRacuna);
                }

                // Analitika izvoda
                AnalitikaIzvoda analitikaIzvoda = new AnalitikaIzvoda();
                Date date1 = (Date) datumNaloga.getModel().getValue();
                analitikaIzvoda.setDatumPrijema(date1);
                Date date2 = (Date) datumValute.getModel().getValue();
                analitikaIzvoda.setDatumValute(date2);
                analitikaIzvoda.setHitno(hitno.isSelected());
                analitikaIzvoda.setIznos(Integer.parseInt(iznos.getText()));
                analitikaIzvoda.setModelOdobrenja(Integer.parseInt(modelOdobrenja.getText()));
                analitikaIzvoda.setModelZaduzenja(Integer.parseInt(modelZaduzenja.getText()));
                analitikaIzvoda.setNalogodavac(duznik.getText());
                analitikaIzvoda.setPozivNaBrojOdobrenja(pozivNaBrojOdobrenja.getText());
                analitikaIzvoda.setPozivNaBrojZaduzenja(pozivNaBrojZaduzenja.getText());
                analitikaIzvoda.setPrimalac(primalac.getText());
                analitikaIzvoda.setRacunDuznika(racunDuznika.getText());
                analitikaIzvoda.setRacunPoverioca(racunPoverioca.getText());
                analitikaIzvoda.setSvrhaPlacanja(svrhaPlacanja.getText());
                if (novoStanje < 0) {
                    analitikaIzvoda.setTipGreske(2);
                } else {

                    analitikaIzvoda.setTipGreske(1);
                }
                analitikaIzvoda.setSmer("");
                analitikaIzvoda.setStatus("");
                analitikaIzvoda.setBrojStavke(0);
                analitikaIzvoda.setDnevnoStanjeRacuna(dnevnoStanjeRacuna);
                NaseljenoMesto naseljenoMesto = (NaseljenoMesto) mesta.getSelectedItem();
                analitikaIzvoda.setNaseljenoMesto(naseljenoMesto);
                Valuta valuta = (Valuta) valute.getSelectedItem();
                analitikaIzvoda.setValuta(valuta);
                VrstaPlacanja vp = (VrstaPlacanja) vrstaPlacanja.getSelectedItem();
                analitikaIzvoda.setVrstaPlacanja(vp);
                analitikaIzvodaService.save(analitikaIzvoda);

                // Medjubankarski Transfer
                MedjubankarskiTransfer medjubankarskiTransfer = new MedjubankarskiTransfer();
                if (isRTGS) {
                    medjubankarskiTransfer.setTip("MT 103");
                } else {
                    medjubankarskiTransfer.setTip("MT 102");
                }
                medjubankarskiTransfer.setAnalitikaIzvoda(analitikaIzvoda);
                KodBanke kb = (KodBanke) swiftKodBanke.getSelectedItem();
                medjubankarskiTransfer.setSwiftDuznika(kb.getSWIFTKod().toString());
                medjubankarskiTransfer.setSwiftPoverioca(swiftKodBankePoverioca.getText());
                medjubankarskiTransferService.save(medjubankarskiTransfer);
                JOptionPane.showMessageDialog(null, "Medjubankarski transfer obavljen.");
                dispose();
            }
        });
        drzave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Drzava d = (Drzava) drzave.getSelectedItem();
                if (d != null) {
                    mesta.removeAllItems();
                    for (NaseljenoMesto n : naseljenoMestoService.findByDrzavaId(d.getId())) {
                        mesta.addItem(n);
                    }
                }
            }
        });
        proveriRacun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                racunPravnogLica = rplService.findByBrojRacuna(racunDuznika_0.getText() + "-" + racunDuznika_1.getText() + "-" + racunDuznika_2.getText());
                swiftKodBanke.removeAllItems();
                for (KodBanke kb : kbService.findAll()) {
                    swiftKodBanke.addItem(kb);
                }
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
