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

@Entity
public class RacunPravnogLica implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer id;

    private String brojRacuna;
    private Date datumOtvaranja;
    private boolean vazeci;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Valuta valuta;

    @OneToMany(mappedBy = "racunPravnogLica", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<Ukidanje> ukidanja = new ArrayList<Ukidanje>();

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private PravnoLice pravnoLice;

    @OneToMany(mappedBy = "racunPravnogLica", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<DnevnoStanjeRacuna> dnevnaStanjaRacuna = new ArrayList<DnevnoStanjeRacuna>();

    @OneToMany(mappedBy = "racunPravnogLica", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<AnalitikaPreseka> analitikePreseka = new ArrayList<AnalitikaPreseka>();

    @OneToMany(mappedBy = "racunPravnogLica", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<PrenosIzvoda> prenosiIzvoda = new ArrayList<PrenosIzvoda>();

    public RacunPravnogLica() {
    }

    public RacunPravnogLica(Integer id, String brojRacuna, Date datumOtvaranja, boolean vazeci, Valuta valuta,
                            List<Ukidanje> ukidanja, PravnoLice pravnoLice, List<DnevnoStanjeRacuna> dnevnaStanjaRacuna,
                            List<AnalitikaPreseka> analitikePreseka, List<PrenosIzvoda> prenosiIzvoda) {
        super();
        this.id = id;
        this.brojRacuna = brojRacuna;
        this.datumOtvaranja = datumOtvaranja;
        this.vazeci = vazeci;
        this.valuta = valuta;
        this.ukidanja = ukidanja;
        this.pravnoLice = pravnoLice;
        this.dnevnaStanjaRacuna = dnevnaStanjaRacuna;
        this.analitikePreseka = analitikePreseka;
        this.prenosiIzvoda = prenosiIzvoda;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrojRacuna() {
        return brojRacuna;
    }

    public void setBrojRacuna(String brojRacuna) {
        this.brojRacuna = brojRacuna;
    }

    public Date getDatumOtvaranja() {
        return datumOtvaranja;
    }

    public void setDatumOtvaranja(Date datumOtvaranja) {
        this.datumOtvaranja = datumOtvaranja;
    }

    public boolean isVazeci() {
        return vazeci;
    }

    public void setVazeci(boolean vazeci) {
        this.vazeci = vazeci;
    }

    public Valuta getValuta() {
        return valuta;
    }

    public void setValuta(Valuta valuta) {
        this.valuta = valuta;
    }

    public List<Ukidanje> getUkidanja() {
        return ukidanja;
    }

    public void setUkidanja(List<Ukidanje> ukidanja) {
        this.ukidanja = ukidanja;
    }

    public PravnoLice getPravnoLice() {
        return pravnoLice;
    }

    public void setPravnoLice(PravnoLice pravnoLice) {
        this.pravnoLice = pravnoLice;
    }

    public List<DnevnoStanjeRacuna> getDnevnaStanjaRacuna() {
        return dnevnaStanjaRacuna;
    }

    public void setDnevnaStanjaRacuna(List<DnevnoStanjeRacuna> dnevnaStanjaRacuna) {
        this.dnevnaStanjaRacuna = dnevnaStanjaRacuna;
    }

    public List<AnalitikaPreseka> getAnalitikePreseka() {
        return analitikePreseka;
    }

    public void setAnalitikePreseka(List<AnalitikaPreseka> analitikePreseka) {
        this.analitikePreseka = analitikePreseka;
    }

    public List<PrenosIzvoda> getPrenosiIzvoda() {
        return prenosiIzvoda;
    }

    public void setPrenosiIzvoda(List<PrenosIzvoda> prenosiIzvoda) {
        this.prenosiIzvoda = prenosiIzvoda;
    }

    public String toString() {
        return brojRacuna;
    }

}
