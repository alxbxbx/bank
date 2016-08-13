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

    private AnalitikaIzvoda analitikaIzvoda;

    public MedjubankarskiTransfer() {

    }

    public MedjubankarskiTransfer(Integer id, String tip, AnalitikaIzvoda analitikaIzvoda) {
        super();
        this.id = id;
        this.tip = tip;
        this.analitikaIzvoda = analitikaIzvoda;
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

    @OneToOne(mappedBy = "medjubankerskiTransfer")
    public AnalitikaIzvoda getAnalitikaIzvoda() {
        return this.analitikaIzvoda;
    }

    public void setAnalitikaIzvoda(AnalitikaIzvoda analitikaIzvoda) {
        this.analitikaIzvoda = analitikaIzvoda;
    }

}
