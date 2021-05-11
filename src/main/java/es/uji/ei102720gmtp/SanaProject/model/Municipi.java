package es.uji.ei102720gmtp.SanaProject.model;

import es.uji.ei102720gmtp.SanaProject.model.enums.Provincia;

public class Municipi {

    private int idMunicipi;
    private String nom;
    private Provincia provincia;

    public int getIdMunicipi() {
        return idMunicipi;
    }

    public void setIdMunicipi(int idMunicipi) {
        this.idMunicipi = idMunicipi;
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
                "idMunicipi='" + idMunicipi + '\'' +
                ", nom='" + nom + '\'' +
                ", provincia='" + provincia + '\'' +
                '}';
    }
}
