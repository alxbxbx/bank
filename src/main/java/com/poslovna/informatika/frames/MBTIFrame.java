package com.poslovna.informatika.frames;

import com.poslovna.informatika.configuration.ApplicationContextProvider;
import com.poslovna.informatika.entities.AnalitikaIzvoda;
import com.poslovna.informatika.entities.MedjubankarskiTransfer;
import com.poslovna.informatika.entities.RacunPravnogLica;
import com.poslovna.informatika.entities.Valuta;
import com.poslovna.informatika.formatters.DateLabelFormatter;
import com.poslovna.informatika.service.KodBankeService;
import com.poslovna.informatika.service.MedjubankarskiTransferService;
import com.poslovna.informatika.service.ValutaService;
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
import java.awt.geom.Arc2D;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

public class MBTIFrame extends JFrame {

    private ValutaService valutaService = (ValutaService) ApplicationContextProvider.getContext().getBean("valutaService");
    private MedjubankarskiTransferService medjubankarskiTransferService = (MedjubankarskiTransferService) ApplicationContextProvider.getContext().getBean("medjubankarskiTransferService");
    private JPanel jPanel;
    private JScrollPane jScrollPane;
    private JComboBox<MedjubankarskiTransfer> mbi;
    private JDatePickerImpl datum;
    private JButton jButton;
    private JLabel jLabel;

    public MBTIFrame() {
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
        jPanel.add(new JLabel("===================== Medjubankarski Transferi ======================="), "span 3, wrap");
        jPanel.add(new JLabel("Odaberi datum:"));
        Properties p2 = new Properties();
        p2.put("text.today", "Today");
        p2.put("text.month", "Month");
        p2.put("text.year", "Year");
        UtilDateModel model2 = new UtilDateModel();
        JDatePanelImpl datePanel2 = new JDatePanelImpl(model2, p2);
        datum = new JDatePickerImpl(datePanel2, new DateLabelFormatter());
        jPanel.add(datum);
        jButton = new JButton("OK");
        jPanel.add(jButton, "wrap");
        jPanel.add(new JLabel("============================== Rezultat =============================="), "span 3, wrap");
        jLabel = new JLabel("Odaberi datum prvo...");
        listeners();
    }

    private void listeners() {
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date date = (Date) datum.getModel().getValue();
                HashMap<String, Double> hm = new HashMap<>();
                for (Valuta valuta : valutaService.findAll()) {
                    hm.put(valuta.getNaziv(), 0.0);
                }
                java.util.List<MedjubankarskiTransfer> mbs = medjubankarskiTransferService.findAll();
                for (MedjubankarskiTransfer dsrx : mbs) {
                    AnalitikaIzvoda dsr = dsrx.getAnalitikaIzvoda();
                    if (dsr.getDatumPrijema() == null) {
                        continue;
                    }
                    if ((dsr.getDatumPrijema().getDate() == date.getDate()) && (dsr.getDatumPrijema().getMonth() == date.getMonth()) && (dsr.getDatumPrijema().getYear() == date.getYear())) {
                        // Proverava da li je racun u nasoj banci
                        if (dsr.getRacunPoverioca() == null) {
                            continue;
                        }
                        if (!dsr.getRacunPoverioca().split("-")[2].equals("430")) {
                            // Final step
                            String valuta = dsr.getValuta().getNaziv();
                            hm.put(valuta, hm.get(valuta) + dsr.getIznos());
                        }
                    }
                }
                for (String key : hm.keySet()) {
                    Double value = hm.get(key);
                    jPanel.add(new JLabel("Za valutu " + key + " promet je " + value.toString()), "span 3, wrap");
                }
                jPanel.revalidate();
                jPanel.repaint();
            }
        });
    }

}
