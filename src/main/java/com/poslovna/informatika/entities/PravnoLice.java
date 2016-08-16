package com.poslovna.informatika.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class PravnoLice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer id;

    private String pib;
    private String naziv;
    private String adresa;
    private String eMail;
    private String web;
    private String telefon;
    private String fax;
    private boolean banka;

    @OneToMany(mappedBy = "pravnoLice", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<KodBanke> kodoviBanke = new ArrayList<KodBanke>();

    @OneToMany(mappedBy = "pravnoLice", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<KursnaLista> kursneListe = new ArrayList<KursnaLista>();

    @OneToMany(mappedBy = "pravnoLice", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<RacunPravnogLica> racuniPravnihLica = new ArrayList<RacunPravnogLica>();

    @OneToMany(mappedBy = "pravnoLice", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<PrenosIzvoda> prenosiIzvoda = new ArrayList<PrenosIzvoda>();

    public PravnoLice() {
    }

    public PravnoLice(Integer id, String pib, String naziv, String adresa, String eMail, String web, String telefon,
                      String fax, boolean banka, List<KodBanke> kodoviBanke, List<KursnaLista> kursneListe,
                      List<RacunPravnogLica> racuniPravnihLica, List<PrenosIzvoda> prenosiIzvoda) {
        super();
        this.id = id;
        this.pib = pib;
        this.naziv = naziv;
        this.adresa = adresa;
        this.eMail = eMail;
        this.web = web;
        this.telefon = telefon;
        this.fax = fax;
        this.banka = banka;
        this.kodoviBanke = kodoviBanke;
        this.kursneListe = kursneListe;
        this.racuniPravnihLica = racuniPravnihLica;
        this.prenosiIzvoda = prenosiIzvoda;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPib() {
        return pib;
    }

    public void setPib(String pib) {
        this.pib = pib;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public boolean isBanka() {
        return banka;
    }

    public void setBanka(boolean banka) {
        this.banka = banka;
    }

    public List<KodBanke> getKodoviBanke() {
        return kodoviBanke;
    }

    public void setKodoviBanke(List<KodBanke> kodoviBanke) {
        this.kodoviBanke = kodoviBanke;
    }

    public List<KursnaLista> getKursneListe() {
        return kursneListe;
    }

    public void setKursneListe(List<KursnaLista> kursneListe) {
        this.kursneListe = kursneListe;
    }

    public List<RacunPravnogLica> getRacuniPravnihLica() {
        return racuniPravnihLica;
    }

    public void setRacuniPravnihLica(List<RacunPravnogLica> racuniPravnihLica) {
        this.racuniPravnihLica = racuniPravnihLica;
    }

    public List<PrenosIzvoda> getPrenosiIzvoda() {
        return prenosiIzvoda;
    }

    public void setPrenosiIzvoda(List<PrenosIzvoda> prenosiIzvoda) {
        this.prenosiIzvoda = prenosiIzvoda;
    }

    public String toString() {
        return this.naziv;
    }

}
