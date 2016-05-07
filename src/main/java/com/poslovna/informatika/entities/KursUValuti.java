package com.poslovna.informatika.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class KursUValuti implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	
	private Integer redniBroj;
	private double kupovni;
	private double srednji;
	private double prodajni;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Valuta valuta;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private KursnaLista kursnaLista;
	
	public KursUValuti(){}
	public KursUValuti(Integer id, Integer redniBroj, double kupovni, double srednji, double prodajni, Valuta valuta,
			KursnaLista kursnaLista) {
		super();
		this.id = id;
		this.redniBroj = redniBroj;
		this.kupovni = kupovni;
		this.srednji = srednji;
		this.prodajni = prodajni;
		this.valuta = valuta;
		this.kursnaLista = kursnaLista;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRedniBroj() {
		return redniBroj;
	}
	public void setRedniBroj(Integer redniBroj) {
		this.redniBroj = redniBroj;
	}
	public double getKupovni() {
		return kupovni;
	}
	public void setKupovni(double kupovni) {
		this.kupovni = kupovni;
	}
	public double getSrednji() {
		return srednji;
	}
	public void setSrednji(double srednji) {
		this.srednji = srednji;
	}
	public double getProdajni() {
		return prodajni;
	}
	public void setProdajni(double prodajni) {
		this.prodajni = prodajni;
	}
	public Valuta getValuta() {
		return valuta;
	}
	public void setValuta(Valuta valuta) {
		this.valuta = valuta;
	}
	public KursnaLista getKursnaLista() {
		return kursnaLista;
	}
	public void setKursnaLista(KursnaLista kursnaLista) {
		this.kursnaLista = kursnaLista;
	}
		
	
}
