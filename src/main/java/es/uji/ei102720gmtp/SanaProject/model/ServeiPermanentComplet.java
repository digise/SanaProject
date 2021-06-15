package es.uji.ei102720gmtp.SanaProject.model;

import java.time.LocalDate;

public class ServeiPermanentComplet {

    private String nom;
    private String tipus;
    private LocalDate dataApertura;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public LocalDate getDataApertura() {
        return dataApertura;
    }

    public void setDataApertura(LocalDate dataApertura) {
        this.dataApertura = dataApertura;
    }

    @Override
    public String toString() {
        return "ServeiPermanentComplet{" +
                "nom='" + nom + '\'' +
                ", tipus='" + tipus + '\'' +
                ", dataApertura=" + dataApertura +
                '}';
    }
}
