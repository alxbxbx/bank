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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "PrenozIzvoda")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class PrenosIzvoda implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer id;

    private Date datumNaloga;
    private Integer brojPreseka;
    private Integer brojPromenaUKorist;
    private double ukupnoUKorist;
    private Integer brojPromenaNaTeret;
    private double ukupnoNaTeret;
    private Integer brojPogresnihStavkiUKorist;
    private Integer brojPogresnihStavkiNaTeret;
    private String statusNaloga;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private PravnoLice pravnoLice;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private RacunPravnogLica racunPravnogLica;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private DnevnoStanjeRacuna dnevnoStanjeRacuna;

    @OneToMany(mappedBy = "prenosIzvoda", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<AnalitikaPreseka> analitikePreseka = new ArrayList<AnalitikaPreseka>();

    public PrenosIzvoda() {
    }

    public PrenosIzvoda(Integer id, Date datumNaloga, Integer brojPreseka, Integer brojPromenaUKorist,
                        double ukupnoUKorist, Integer brojPromenaNaTeret, double ukupnoNaTeret, Integer brojPogresnihStavkiUKorist,
                        Integer brojPogresnihStavkiNaTeret, String statusNaloga, PravnoLice pravnoLice,
                        RacunPravnogLica racunPravnogLica, DnevnoStanjeRacuna dnevnoStanjeRacuna,
                        List<AnalitikaPreseka> analitikePreseka) {
        super();
        this.id = id;
        this.datumNaloga = datumNaloga;
        this.brojPreseka = brojPreseka;
        this.brojPromenaUKorist = brojPromenaUKorist;
        this.ukupnoUKorist = ukupnoUKorist;
        this.brojPromenaNaTeret = brojPromenaNaTeret;
        this.ukupnoNaTeret = ukupnoNaTeret;
        this.brojPogresnihStavkiUKorist = brojPogresnihStavkiUKorist;
        this.brojPogresnihStavkiNaTeret = brojPogresnihStavkiNaTeret;
        this.statusNaloga = statusNaloga;
        this.pravnoLice = pravnoLice;
        this.racunPravnogLica = racunPravnogLica;
        this.dnevnoStanjeRacuna = dnevnoStanjeRacuna;
        this.analitikePreseka = analitikePreseka;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDatumNaloga() {
        return datumNaloga;
    }

    public void setDatumNaloga(Date datumNaloga) {
        this.datumNaloga = datumNaloga;
    }

    public Integer getBrojPreseka() {
        return brojPreseka;
    }

    public void setBrojPreseka(Integer brojPreseka) {
        this.brojPreseka = brojPreseka;
    }

    public Integer getBrojPromenaUKorist() {
        return brojPromenaUKorist;
    }

    public void setBrojPromenaUKorist(Integer brojPromenaUKorist) {
        this.brojPromenaUKorist = brojPromenaUKorist;
    }

    public double getUkupnoUKorist() {
        return ukupnoUKorist;
    }

    public void setUkupnoUKorist(double ukupnoUKorist) {
        this.ukupnoUKorist = ukupnoUKorist;
    }

    public Integer getBrojPromenaNaTeret() {
        return brojPromenaNaTeret;
    }

    public void setBrojPromenaNaTeret(Integer brojPromenaNaTeret) {
        this.brojPromenaNaTeret = brojPromenaNaTeret;
    }

    public double getUkupnoNaTeret() {
        return ukupnoNaTeret;
    }

    public void setUkupnoNaTeret(double ukupnoNaTeret) {
        this.ukupnoNaTeret = ukupnoNaTeret;
    }

    public Integer getBrojPogresnihStavkiUKorist() {
        return brojPogresnihStavkiUKorist;
    }

    public void setBrojPogresnihStavkiUKorist(Integer brojPogresnihStavkiUKorist) {
        this.brojPogresnihStavkiUKorist = brojPogresnihStavkiUKorist;
    }

    public Integer getBrojPogresnihStavkiNaTeret() {
        return brojPogresnihStavkiNaTeret;
    }

    public void setBrojPogresnihStavkiNaTeret(Integer brojPogresnihStavkiNaTeret) {
        this.brojPogresnihStavkiNaTeret = brojPogresnihStavkiNaTeret;
    }

    public String getStatusNaloga() {
        return statusNaloga;
    }

    public void setStatusNaloga(String statusNaloga) {
        this.statusNaloga = statusNaloga;
    }
    
    @XmlTransient
    public PravnoLice getPravnoLice() {
        return pravnoLice;
    }

    public void setPravnoLice(PravnoLice pravnoLice) {
        this.pravnoLice = pravnoLice;
    }
    
    @XmlTransient
    public RacunPravnogLica getRacunPravnogLica() {
        return racunPravnogLica;
    }

    public void setRacunPravnogLica(RacunPravnogLica racunPravnogLica) {
        this.racunPravnogLica = racunPravnogLica;
    }
    
    @XmlTransient
    public DnevnoStanjeRacuna getDnevnoStanjeRacuna() {
        return dnevnoStanjeRacuna;
    }

    public void setDnevnoStanjeRacuna(DnevnoStanjeRacuna dnevnoStanjeRacuna) {
        this.dnevnoStanjeRacuna = dnevnoStanjeRacuna;
    }
    
    @XmlTransient
    public List<AnalitikaPreseka> getAnalitikePreseka() {
        return analitikePreseka;
    }

    public void setAnalitikePreseka(List<AnalitikaPreseka> analitikePreseka) {
        this.analitikePreseka = analitikePreseka;
    }

}
