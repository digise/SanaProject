package es.uji.ei102720gmtp.SanaProject.model;

public class Municipi {

    private String idMunicipi;
    private String nom;
    private String provincia;

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
        return provincia;
    }

    public void setProvincia(String provincia) {
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
