package com.iticbcn.bdor;

import java.io.Serializable;

public class Adresa implements Serializable{

    private String Carrer;
    private int Nombre;
    private String Ciutat;
    private String Cp;
    private String Provincia;

    public Adresa() {}

    public Adresa(String carrer, int nombre, String ciutat, String cp, String provincia) {
        Carrer = carrer;
        Nombre = nombre;
        Ciutat = ciutat;
        Cp = cp;
        Provincia = provincia;
    }

    public String getCarrer() {
        return Carrer;
    }

    public void setCarrer(String carrer) {
        Carrer = carrer;
    }

    public int getNombre() {
        return Nombre;
    }

    public void setNombre(int nombre) {
        Nombre = nombre;
    }

    public String getCiutat() {
        return Ciutat;
    }

    public void setCiutat(String ciutat) {
        Ciutat = ciutat;
    }

    public String getCp() {
        return Cp;
    }

    public void setCp(String cp) {
        Cp = cp;
    }

    public String getProvincia() {
        return Provincia;
    }

    public void setProvincia(String provincia) {
        Provincia = provincia;
    }

}
