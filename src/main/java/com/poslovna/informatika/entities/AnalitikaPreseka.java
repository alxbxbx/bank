package com.poslovna.informatika.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlTransient;

@Entity
public class AnalitikaPreseka implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer id;
    private Integer redniBrojPromene;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private RacunPravnogLica racunPravnogLica;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private AnalitikaIzvoda analitikaIzvoda;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private PrenosIzvoda prenosIzvoda;

    public AnalitikaPreseka() {
    }

    public AnalitikaPreseka(Integer id, Integer redniBrojPromene, RacunPravnogLica racunPravnogLica,
                            AnalitikaIzvoda analitikaIzvoda, PrenosIzvoda prenosIzvoda) {
        super();
        this.id = id;
        this.redniBrojPromene = redniBrojPromene;
        this.racunPravnogLica = racunPravnogLica;
        this.analitikaIzvoda = analitikaIzvoda;
        this.prenosIzvoda = prenosIzvoda;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRedniBrojPromene() {
        return redniBrojPromene;
    }

    public void setRedniBrojPromene(Integer redniBrojPromene) {
        this.redniBrojPromene = redniBrojPromene;
    }

    public RacunPravnogLica getRacunPravnogLica() {
        return racunPravnogLica;
    }

    public void setRacunPravnogLica(RacunPravnogLica racunPravnogLica) {
        this.racunPravnogLica = racunPravnogLica;
    }

    public AnalitikaIzvoda getAnalitikaIzvoda() {
        return analitikaIzvoda;
    }

    public void setAnalitikaIzvoda(AnalitikaIzvoda analitikaIzvoda) {
        this.analitikaIzvoda = analitikaIzvoda;
    }
    
    @XmlTransient
    public PrenosIzvoda getPrenosIzvoda() {
        return prenosIzvoda;
    }

    public void setPrenosIzvoda(PrenosIzvoda prenosIzvoda) {
        this.prenosIzvoda = prenosIzvoda;
    }

}
