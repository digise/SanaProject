package es.uji.ei102720gmtp.SanaProject.model;

import es.uji.ei102720gmtp.SanaProject.model.enums.Provincia;

public class Municipi {

    private String idMunicipi;
    private String nom;
    private Provincia provincia;

    public String getIdMunicipi() {
        return idMunicipi;
    }

    public void setIdMunicipi(String idMunicipi) {
        this.idMunicipi = idMunicipi;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getProvincia() {
        return provincia.name();
    }

    public void setProvincia(String provincia) {
        this.provincia = Provincia.valueOf(provincia);
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
