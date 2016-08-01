package com.poslovna.informatika.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Ukidanje implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer id;
    private Date datumUkidanja;
    private String sredstvaSePrenoseNaRacun;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private RacunPravnogLica racunPravnogLica;

    public Ukidanje() {
    }

    public Ukidanje(Integer id, Date datumUkidanja, String sredstvaSePrenoseNaRacun,
                    RacunPravnogLica racunPravnogLica) {
        super();
        this.id = id;
        this.datumUkidanja = datumUkidanja;
        this.sredstvaSePrenoseNaRacun = sredstvaSePrenoseNaRacun;
        this.racunPravnogLica = racunPravnogLica;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDatumUkidanja() {
        return datumUkidanja;
    }

    public void setDatumUkidanja(Date datumUkidanja) {
        this.datumUkidanja = datumUkidanja;
    }

    public String getSredstvaSePrenoseNaRacun() {
        return sredstvaSePrenoseNaRacun;
    }

    public void setSredstvaSePrenoseNaRacun(String sredstvaSePrenoseNaRacun) {
        this.sredstvaSePrenoseNaRacun = sredstvaSePrenoseNaRacun;
    }

    public RacunPravnogLica getRacunPravnogLica() {
        return racunPravnogLica;
    }

    public void setRacunPravnogLica(RacunPravnogLica racunPravnogLica) {
        this.racunPravnogLica = racunPravnogLica;
    }

}
