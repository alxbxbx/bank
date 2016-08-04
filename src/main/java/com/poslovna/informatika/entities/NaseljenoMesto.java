package com.poslovna.informatika.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class NaseljenoMesto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer id;

    private String sifraMesta;
    private String naziv;
    private String PTTOznaka;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Drzava drzava;

    @OneToMany(mappedBy = "naseljenoMesto", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<AnalitikaIzvoda> analitikeIzvoda = new ArrayList<AnalitikaIzvoda>();
    
    @Override
    public String toString(){
		return this.naziv;
    	
    }
    public NaseljenoMesto() {
    }

    public NaseljenoMesto(Integer id, String sifraMesta, String naziv, String pTTOznaka, Drzava drzava,
			List<AnalitikaIzvoda> analitikeIzvoda) {
		super();
		this.id = id;
		this.sifraMesta = sifraMesta;
		this.naziv = naziv;
		PTTOznaka = pTTOznaka;
		this.drzava = drzava;
		this.analitikeIzvoda = analitikeIzvoda;
	}



	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    

    public String getSifraMesta() {
		return sifraMesta;
	}

	public void setSifraMesta(String sifraMesta) {
		this.sifraMesta = sifraMesta;
	}

	public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getPTTOznaka() {
        return PTTOznaka;
    }

    public void setPTTOznaka(String pTTOznaka) {
        PTTOznaka = pTTOznaka;
    }

    public Drzava getDrzava() {
        return drzava;
    }

    public void setDrzava(Drzava drzava) {
        this.drzava = drzava;
    }

    public List<AnalitikaIzvoda> getAnalitikeIzvoda() {
        return analitikeIzvoda;
    }

    public void setAnalitikeIzvoda(List<AnalitikaIzvoda> analitikeIzvoda) {
        this.analitikeIzvoda = analitikeIzvoda;
    }

}
