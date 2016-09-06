package com.poslovna.informatika.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

@Entity
public class AnalitikaIzvoda implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer id;
    private Integer brojStavke;
    private String nalogodavac;
    private String svrhaPlacanja;
    private String primalac;
    private Date datumPrijema;
    private Date datumValute;
    private String racunDuznika;
    private Integer modelZaduzenja;
    private String pozivNaBrojZaduzenja;
    private String racunPoverioca;
    private Integer modelOdobrenja;
    private String pozivNaBrojOdobrenja;
    private boolean hitno;
    private double iznos;
    private String smer;
    private Integer tipGreske;
    private String status;
    
    @OneToOne(mappedBy="analitikaIzvoda")
    private MedjubankarskiTransfer medjubankarskiTransfer;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private NaseljenoMesto naseljenoMesto;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private VrstaPlacanja vrstaPlacanja;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Valuta valuta;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private DnevnoStanjeRacuna dnevnoStanjeRacuna;

    @OneToMany(mappedBy = "analitikaIzvoda", fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    private List<AnalitikaPreseka> analitikePreseka = new ArrayList<AnalitikaPreseka>();

    public AnalitikaIzvoda() {
    }

    public AnalitikaIzvoda(Integer id, Integer brojStavke, String nalogodavac, String svrhaPlacanja, String primalac,
                           Date datumPrijema, Date datumValute, String racunDuznika, Integer modelZaduzenja,
                           String pozivNaBrojZaduzenja, String racunPoverioca, Integer modelOdobrenja, String pozivNaBrojOdobrenja,
                           boolean hitno, double iznos, String smer, Integer tipGreske, String status, NaseljenoMesto naseljenoMesto,
                           VrstaPlacanja vrstaPlacanja, Valuta valuta, DnevnoStanjeRacuna dnevnoStanjeRacuna,
                           List<AnalitikaPreseka> analitikePreseka, MedjubankarskiTransfer medjubankarskiTransfer) {
        super();
        this.id = id;
        this.brojStavke = brojStavke;
        this.nalogodavac = nalogodavac;
        this.svrhaPlacanja = svrhaPlacanja;
        this.primalac = primalac;
        this.datumPrijema = datumPrijema;
        this.datumValute = datumValute;
        this.racunDuznika = racunDuznika;
        this.modelZaduzenja = modelZaduzenja;
        this.pozivNaBrojZaduzenja = pozivNaBrojZaduzenja;
        this.racunPoverioca = racunPoverioca;
        this.modelOdobrenja = modelOdobrenja;
        this.pozivNaBrojOdobrenja = pozivNaBrojOdobrenja;
        this.hitno = hitno;
        this.iznos = iznos;
        this.smer = smer;
        this.tipGreske = tipGreske;
        this.status = status;
        this.naseljenoMesto = naseljenoMesto;
        this.vrstaPlacanja = vrstaPlacanja;
        this.valuta = valuta;
        this.dnevnoStanjeRacuna = dnevnoStanjeRacuna;
        this.analitikePreseka = analitikePreseka;
        this.medjubankarskiTransfer = medjubankarskiTransfer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBrojStavke() {
        return brojStavke;
    }

    public void setBrojStavke(Integer brojStavke) {
        this.brojStavke = brojStavke;
    }

    public String getNalogodavac() {
        return nalogodavac;
    }

    public void setNalogodavac(String nalogodavac) {
        this.nalogodavac = nalogodavac;
    }

    public String getSvrhaPlacanja() {
        return svrhaPlacanja;
    }

    public void setSvrhaPlacanja(String svrhaPlacanja) {
        this.svrhaPlacanja = svrhaPlacanja;
    }

    public String getPrimalac() {
        return primalac;
    }

    public void setPrimalac(String primalac) {
        this.primalac = primalac;
    }

    public Date getDatumPrijema() {
        return datumPrijema;
    }

    public void setDatumPrijema(Date datumPrijema) {
        this.datumPrijema = datumPrijema;
    }

    public Date getDatumValute() {
        return datumValute;
    }

    public void setDatumValute(Date datumValute) {
        this.datumValute = datumValute;
    }

    public String getRacunDuznika() {
        return racunDuznika;
    }

    public void setRacunDuznika(String racunDuznika) {
        this.racunDuznika = racunDuznika;
    }

    public Integer getModelZaduzenja() {
        return modelZaduzenja;
    }

    public void setModelZaduzenja(Integer modelZaduzenja) {
        this.modelZaduzenja = modelZaduzenja;
    }

    public String getPozivNaBrojZaduzenja() {
        return pozivNaBrojZaduzenja;
    }

    public void setPozivNaBrojZaduzenja(String pozivNaBrojZaduzenja) {
        this.pozivNaBrojZaduzenja = pozivNaBrojZaduzenja;
    }

    public String getRacunPoverioca() {
        return racunPoverioca;
    }

    public void setRacunPoverioca(String racunPoverioca) {
        this.racunPoverioca = racunPoverioca;
    }

    public Integer getModelOdobrenja() {
        return modelOdobrenja;
    }

    public void setModelOdobrenja(Integer modelOdobrenja) {
        this.modelOdobrenja = modelOdobrenja;
    }

    public String getPozivNaBrojOdobrenja() {
        return pozivNaBrojOdobrenja;
    }

    public void setPozivNaBrojOdobrenja(String pozivNaBrojOdobrenja) {
        this.pozivNaBrojOdobrenja = pozivNaBrojOdobrenja;
    }

    public boolean isHitno() {
        return hitno;
    }

    public void setHitno(boolean hitno) {
        this.hitno = hitno;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public String getSmer() {
        return smer;
    }

    public void setSmer(String smer) {
        this.smer = smer;
    }

    public Integer getTipGreske() {
        return tipGreske;
    }

    public void setTipGreske(Integer tipGreske) {
        this.tipGreske = tipGreske;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public NaseljenoMesto getNaseljenoMesto() {
        return naseljenoMesto;
    }

    public void setNaseljenoMesto(NaseljenoMesto naseljenoMesto) {
        this.naseljenoMesto = naseljenoMesto;
    }

    public VrstaPlacanja getVrstaPlacanja() {
        return vrstaPlacanja;
    }

    public void setVrstaPlacanja(VrstaPlacanja vrstaPlacanja) {
        this.vrstaPlacanja = vrstaPlacanja;
    }

    public Valuta getValuta() {
        return valuta;
    }

    public void setValuta(Valuta valuta) {
        this.valuta = valuta;
    }

    @XmlTransient
    public DnevnoStanjeRacuna getDnevnoStanjeRacuna() {
        return dnevnoStanjeRacuna;
    }

    public void setDnevnoStanjeRacuna(DnevnoStanjeRacuna dnevnoStanjeRacuna) {
        this.dnevnoStanjeRacuna = dnevnoStanjeRacuna;
    }

    public List<AnalitikaPreseka> getAnalitikePreseka() {
        return analitikePreseka;
    }

    public void setAnalitikePreseka(List<AnalitikaPreseka> analitikePreseka) {
        this.analitikePreseka = analitikePreseka;
    }
    
    @XmlTransient
    public MedjubankarskiTransfer getMedjubankarskiTransfer() {
        return medjubankarskiTransfer;
    }

    public void setMedjubankarskiTransfer(MedjubankarskiTransfer medjubankarskiTransfer) {
        this.medjubankarskiTransfer = medjubankarskiTransfer;
    }
}
