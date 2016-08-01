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
public class VrstaPlacanja implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer id;

    private String oznakaVrste;
    private String nazivVrstePlacanja;

    @OneToMany(mappedBy = "vrstaPlacanja", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<AnalitikaIzvoda> analitikeIzvoda = new ArrayList<AnalitikaIzvoda>();

    public VrstaPlacanja() {
    }

    public VrstaPlacanja(Integer id, String oznakaVrste, String nazivVrstePlacanja,
                         List<AnalitikaIzvoda> analitikeIzvoda) {
        super();
        this.id = id;
        this.oznakaVrste = oznakaVrste;
        this.nazivVrstePlacanja = nazivVrstePlacanja;
        this.analitikeIzvoda = analitikeIzvoda;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOznakaVrste() {
        return oznakaVrste;
    }

    public void setOznakaVrste(String oznakaVrste) {
        this.oznakaVrste = oznakaVrste;
    }

    public String getNazivVrstePlacanja() {
        return nazivVrstePlacanja;
    }

    public void setNazivVrstePlacanja(String nazivVrstePlacanja) {
        this.nazivVrstePlacanja = nazivVrstePlacanja;
    }

    public List<AnalitikaIzvoda> getAnalitikeIzvoda() {
        return analitikeIzvoda;
    }

    public void setAnalitikeIzvoda(List<AnalitikaIzvoda> analitikeIzvoda) {
        this.analitikeIzvoda = analitikeIzvoda;
    }

}
