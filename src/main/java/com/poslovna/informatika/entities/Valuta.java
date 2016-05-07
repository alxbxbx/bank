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
public class Valuta implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	
	private String sifraValute;
	private String naziv;
	private  boolean domicilna;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Drzava drzava;
	
	@OneToMany(mappedBy = "valuta", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<KursUValuti> kurseviUValuti = new ArrayList<KursUValuti>();
	
	@OneToMany(mappedBy = "valuta", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<RacunPravnogLica> racuniPravnihLica = new ArrayList<RacunPravnogLica>();
	
	public Valuta(){}
	public Valuta(Integer id, String sifraValute, String naziv, boolean domicilna, Drzava drzava,
			List<KursUValuti> kurseviUValuti, List<RacunPravnogLica> racuniPravnihLica) {
		super();
		this.id = id;
		this.sifraValute = sifraValute;
		this.naziv = naziv;
		this.domicilna = domicilna;
		this.drzava = drzava;
		this.kurseviUValuti = kurseviUValuti;
		this.racuniPravnihLica = racuniPravnihLica;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSifraValute() {
		return sifraValute;
	}
	public void setSifraValute(String sifraValute) {
		this.sifraValute = sifraValute;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public boolean isDomicilna() {
		return domicilna;
	}
	public void setDomicilna(boolean domicilna) {
		this.domicilna = domicilna;
	}
	public Drzava getDrzava() {
		return drzava;
	}
	public void setDrzava(Drzava drzava) {
		this.drzava = drzava;
	}
	public List<KursUValuti> getKurseviUValuti() {
		return kurseviUValuti;
	}
	public void setKurseviUValuti(List<KursUValuti> kurseviUValuti) {
		this.kurseviUValuti = kurseviUValuti;
	}
	public List<RacunPravnogLica> getRacuniPravnihLica() {
		return racuniPravnihLica;
	}
	public void setRacuniPravnihLica(List<RacunPravnogLica> racuniPravnihLica) {
		this.racuniPravnihLica = racuniPravnihLica;
	}
 	
	
	
}
