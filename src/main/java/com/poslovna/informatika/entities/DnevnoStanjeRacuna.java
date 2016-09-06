package com.poslovna.informatika.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlTransient;

@Entity
public class DnevnoStanjeRacuna implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer id;
    private Integer brojIzvoda;
    private Date datumPrometa;
    private double prethodnoStanje;
    private double prometUKorist;
    private double prometNaTeret;
    private double novoStanje;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private RacunPravnogLica racunPravnogLica;

    @OneToMany(mappedBy = "dnevnoStanjeRacuna", fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    private List<AnalitikaIzvoda> analitikeIzvoda = new ArrayList<AnalitikaIzvoda>();

    @OneToMany(mappedBy = "dnevnoStanjeRacuna", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<PrenosIzvoda> prenosiIzvoda = new ArrayList<PrenosIzvoda>();

    public DnevnoStanjeRacuna() {
    }

    public DnevnoStanjeRacuna(Integer id, Integer brojIzvoda, Date datumPrometa, double prethodnoStanje,
                              double prometUKorist, double prometNaTeret, double novoStanje, RacunPravnogLica racunPravnogLica,
                              List<AnalitikaIzvoda> analitikeIzvoda, List<PrenosIzvoda> prenosiIzvoda) {
        super();
        this.id = id;
        this.brojIzvoda = brojIzvoda;
        this.datumPrometa = datumPrometa;
        this.prethodnoStanje = prethodnoStanje;
        this.prometUKorist = prometUKorist;
        this.prometNaTeret = prometNaTeret;
        this.novoStanje = novoStanje;
        this.racunPravnogLica = racunPravnogLica;
        this.analitikeIzvoda = analitikeIzvoda;
        this.prenosiIzvoda = prenosiIzvoda;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBrojIzvoda() {
        return brojIzvoda;
    }

    public void setBrojIzvoda(Integer brojIzvoda) {
        this.brojIzvoda = brojIzvoda;
    }

    public Date getDatumPrometa() {
        return datumPrometa;
    }

    public void setDatumPrometa(Date datumPrometa) {
        this.datumPrometa = datumPrometa;
    }

    public double getPrethodnoStanje() {
        return prethodnoStanje;
    }

    public void setPrethodnoStanje(double prethodnoStanje) {
        this.prethodnoStanje = prethodnoStanje;
    }

    public double getPrometUKorist() {
        return prometUKorist;
    }

    public void setPrometUKorist(double prometUKorist) {
        this.prometUKorist = prometUKorist;
    }

    public double getPrometNaTeret() {
        return prometNaTeret;
    }

    public void setPrometNaTeret(double prometNaTeret) {
        this.prometNaTeret = prometNaTeret;
    }

    public double getNovoStanje() {
        return novoStanje;
    }

    public void setNovoStanje(double novoStanje) {
        this.novoStanje = novoStanje;
    }

    public RacunPravnogLica getRacunPravnogLica() {
        return racunPravnogLica;
    }

    public void setRacunPravnogLica(RacunPravnogLica racunPravnogLica) {
        this.racunPravnogLica = racunPravnogLica;
    }

    @XmlTransient
    public List<AnalitikaIzvoda> getAnalitikeIzvoda() {
        return analitikeIzvoda;
    }

    public void setAnalitikeIzvoda(List<AnalitikaIzvoda> analitikeIzvoda) {
        this.analitikeIzvoda = analitikeIzvoda;
    }
    
    @XmlTransient
    public List<PrenosIzvoda> getPrenosiIzvoda() {
        return prenosiIzvoda;
    }

    public void setPrenosiIzvoda(List<PrenosIzvoda> prenosiIzvoda) {
        this.prenosiIzvoda = prenosiIzvoda;
    }

}
