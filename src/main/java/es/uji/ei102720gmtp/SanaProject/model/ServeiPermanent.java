package es.uji.ei102720gmtp.SanaProject.model;

public class ServeiPermanent {

    private String nom;
    private String tipus;

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

    @Override
    public String toString() {
        return "ServeiPermanent{" +
                "nom='" + nom + '\'' +
                ", tipus='" + tipus + '\'' +
                '}';
    }
}
