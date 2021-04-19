package es.uji.ei102720gmtp.SanaProject.model;

import es.uji.ei102720gmtp.SanaProject.model.enums.TipusServei;

public class ServeiPermanent {

    private String nom;
    private TipusServei tipus;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public TipusServei getTipus() {
        return tipus;
    }

    public void setTipus(TipusServei tipus) {
        this.tipus = tipus;
    }

    @Override
    public String toString() {
        return "ServeiPermanent{" +
                "nom='" + nom + '\'' +
                ", tipus='" + tipus + '\'' +
                '}';
    }
}
