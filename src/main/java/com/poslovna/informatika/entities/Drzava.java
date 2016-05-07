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
public class Drzava implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2625670240225920326L;

	@Id
	@GeneratedValue
	private Integer id;

	private String sifraDrzave;
	
	private String nazivDrzave;
	
	@OneToMany(mappedBy = "drzava", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<Valuta> valute = new ArrayList<Valuta>();
	
	@OneToMany(mappedBy = "drzava", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<NaseljenoMesto> naseljenaMesta = new ArrayList<NaseljenoMesto>();
	
	public Drzava(){}
	public Drzava(Integer id, String sifraDrzave, String nazivDrzave, List<Valuta> valute,
			List<NaseljenoMesto> naseljenaMesta) {
		super();
		this.id = id;
		this.sifraDrzave = sifraDrzave;
		this.nazivDrzave = nazivDrzave;
		this.valute = valute;
		this.naseljenaMesta = naseljenaMesta;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSifraDrzave() {
		return sifraDrzave;
	}
	public void setSifraDrzave(String sifraDrzave) {
		this.sifraDrzave = sifraDrzave;
	}
	public String getNazivDrzave() {
		return nazivDrzave;
	}
	public void setNazivDrzave(String nazivDrzave) {
		this.nazivDrzave = nazivDrzave;
	}
	public List<Valuta> getValute() {
		return valute;
	}
	public void setValute(List<Valuta> valute) {
		this.valute = valute;
	}
	public List<NaseljenoMesto> getNaseljenaMesta() {
		return naseljenaMesta;
	}
	public void setNaseljenaMesta(List<NaseljenoMesto> naseljenaMesta) {
		this.naseljenaMesta = naseljenaMesta;
	}
	
	
	
}
