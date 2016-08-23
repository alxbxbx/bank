package com.poslovna.informatika.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class MedjubankarskiTransfer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer id;

    private String tip;

    private String swiftPoverioca;

    private String swiftDuznika;

    @OneToOne
    @JoinColumn(name = "analitika_izvoda_id")
    private AnalitikaIzvoda analitikaIzvoda;

    public MedjubankarskiTransfer() {

    }

    public MedjubankarskiTransfer(Integer id, String tip, AnalitikaIzvoda analitikaIzvoda, String swiftDuznika, String swiftPoverioca) {
        super();
        this.id = id;
        this.tip = tip;
        this.analitikaIzvoda = analitikaIzvoda;
        this.swiftDuznika = swiftDuznika;
        this.swiftPoverioca = swiftPoverioca;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public AnalitikaIzvoda getAnalitikaIzvoda() {
        return this.analitikaIzvoda;
    }

    public void setAnalitikaIzvoda(AnalitikaIzvoda analitikaIzvoda) {
        this.analitikaIzvoda = analitikaIzvoda;
    }

    public String getSwiftPoverioca() {
        return swiftPoverioca;
    }

    public void setSwiftPoverioca(String swiftPoverioca) {
        this.swiftPoverioca = swiftPoverioca;
    }

    public String getSwiftDuznika() {
        return swiftDuznika;
    }

    public void setSwiftDuznika(String swiftDuznika) {
        this.swiftDuznika = swiftDuznika;
    }
}
