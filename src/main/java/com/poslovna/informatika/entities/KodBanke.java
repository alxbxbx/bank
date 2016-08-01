package com.poslovna.informatika.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class KodBanke implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer id;

    private Integer sifraBanke;
    private String SWIFTKod;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private PravnoLice pravnoLice;

    public KodBanke() {
    }

    public KodBanke(Integer id, Integer sifraBanke, String sWIFTKod, PravnoLice pravnoLice) {
        super();
        this.id = id;
        this.sifraBanke = sifraBanke;
        SWIFTKod = sWIFTKod;
        this.pravnoLice = pravnoLice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSifraBanke() {
        return sifraBanke;
    }

    public void setSifraBanke(Integer sifraBanke) {
        this.sifraBanke = sifraBanke;
    }

    public String getSWIFTKod() {
        return SWIFTKod;
    }

    public void setSWIFTKod(String sWIFTKod) {
        SWIFTKod = sWIFTKod;
    }

    public PravnoLice getPravnoLice() {
        return pravnoLice;
    }

    public void setPravnoLice(PravnoLice pravnoLice) {
        this.pravnoLice = pravnoLice;
    }

}
