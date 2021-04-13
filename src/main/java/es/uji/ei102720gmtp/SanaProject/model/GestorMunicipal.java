package es.uji.ei102720gmtp.SanaProject.model;

public class GestorMunicipal {

    private String nif;
    private String id_municipi;
    private String nom;
    private String cognoms;
    private String email;
    private String telefon;
    private String contrasenya;

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getId_municipi() {
        return id_municipi;
    }

    public void setId_municipi(String id_municipi) {
        this.id_municipi = id_municipi;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognoms() {
        return cognoms;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    @Override
    public String toString() {
        return "GestorMunicipal{" +
                "nif='" + nif + '\'' +
                ", id_municipi='" + id_municipi + '\'' +
                ", nom='" + nom + '\'' +
                ", cognoms='" + cognoms + '\'' +
                ", email='" + email + '\'' +
                ", telefon='" + telefon + '\'' +
                ", contrasenya='" + contrasenya + '\'' +
                '}';
    }
}
