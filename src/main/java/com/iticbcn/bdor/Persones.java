package com.iticbcn.bdor;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Persones implements Serializable {

    private int numPers;
    private String nomPers;
    private String cognomsPers;
    private Adresa domiciliPers;
    private LocalDateTime dataNaix;
    private String[] telefons;

    public Persones() {}

    public Persones(String nomPers, String cognomsPers, Adresa domiciliPers, LocalDateTime dataNaix,
            String[] telefons) {
        this.nomPers = nomPers;
        this.cognomsPers = cognomsPers;
        this.domiciliPers = domiciliPers;
        this.dataNaix = dataNaix;
        this.telefons = telefons;
    }

    public int getNumPers() {
        return numPers;
    }

    public void setNumPers(int numPers) {
        this.numPers = numPers;
    }

    public String getNomPers() {
        return nomPers;
    }

    public void setNomPers(String nomPers) {
        this.nomPers = nomPers;
    }

    public String getCognomsPers() {
        return cognomsPers;
    }

    public void setCognomsPers(String cognomsPers) {
        this.cognomsPers = cognomsPers;
    }

    public Adresa getDomiciliPers() {
        return domiciliPers;
    }

    public void setDomiciliPers(Adresa domiciliPers) {
        this.domiciliPers = domiciliPers;
    }

    public LocalDateTime getDataNaix() {
        return dataNaix;
    }

    public void setDataNaix(LocalDateTime dataNaix) {
        this.dataNaix = dataNaix;
    }

    public String[] getTelefons() {
        return telefons;
    }

    public void setTelefons(String[] telefons) {
        this.telefons = telefons;
    }

    

    



}
