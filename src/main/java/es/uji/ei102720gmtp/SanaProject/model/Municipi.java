package es.uji.ei102720gmtp.SanaProject.model;

import es.uji.ei102720gmtp.SanaProject.model.enums.Provincia;

public class Municipi {

    private int id;
    private String nom;
    private Provincia provincia;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return "Municipi{" +
                "idMunicipi='" + id + '\'' +
                ", nom='" + nom + '\'' +
                ", provincia='" + provincia + '\'' +
                '}';
    }
}
