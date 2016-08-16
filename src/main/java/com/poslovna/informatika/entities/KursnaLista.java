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

@Entity
public class KursnaLista implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer id;

    private Date datum;
    private Integer brojKursneListe;
    private Date primenjujeSeOd;

    @OneToMany(mappedBy = "kursnaLista", fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    private List<KursUValuti> kurseviUValuti = new ArrayList<KursUValuti>();

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private PravnoLice pravnoLice;
    
    @Override
    public String toString(){
    	String dan = String.valueOf(this.primenjujeSeOd.getDate());
    	String mesec = String.valueOf(this.primenjujeSeOd.getMonth() + 1);
    	String godina = String.valueOf(this.primenjujeSeOd.getYear() + 1900);
    	return (dan + "-" + mesec + "-" + godina);
    }

    public KursnaLista() {
    }

    public KursnaLista(Integer id, Date datum, Integer brojKursneListe, Date primenjujeSeOd,
                       List<KursUValuti> kurseviUValuti, PravnoLice pravnoLice) {
        super();
        this.id = id;
        this.datum = datum;
        this.brojKursneListe = brojKursneListe;
        this.primenjujeSeOd = primenjujeSeOd;
        this.kurseviUValuti = kurseviUValuti;
        this.pravnoLice = pravnoLice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Integer getBrojKursneListe() {
        return brojKursneListe;
    }

    public void setBrojKursneListe(Integer brojKursneListe) {
        this.brojKursneListe = brojKursneListe;
    }

    public Date getPrimenjujeSeOd() {
        return primenjujeSeOd;
    }

    public void setPrimenjujeSeOd(Date primenjujeSeOd) {
        this.primenjujeSeOd = primenjujeSeOd;
    }

    public List<KursUValuti> getKurseviUValuti() {
        return kurseviUValuti;
    }

    public void setKurseviUValuti(List<KursUValuti> kurseviUValuti) {
        this.kurseviUValuti = kurseviUValuti;
    }

    public PravnoLice getPravnoLice() {
        return pravnoLice;
    }

    public void setPravnoLice(PravnoLice pravnoLice) {
        this.pravnoLice = pravnoLice;
    }

}
