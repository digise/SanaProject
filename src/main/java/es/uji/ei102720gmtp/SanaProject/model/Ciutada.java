package es.uji.ei102720gmtp.SanaProject.model;

public class Ciutada {

    private String nif;
    private String nom;
    private String cognoms;
    private String telefon;
    private String email;
    private String domicili;
    private String localitat;
    private String pais;
    private String contrasenya;

    public Ciutada(){}

    public String getNif() {
        return nif;
    }

    public String getNom() {
        return nom;
    }

    public String getCognoms() {
        return cognoms;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getEmail() {
        return email;
    }

    public String getDomicili() {
        return domicili;
    }

    public String getLocalitat() {
        return localitat;
    }

    public String getPais() {
        return pais;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDomicili(String domicili) {
        this.domicili = domicili;
    }

    public void setLocalitat(String localitat) {
        this.localitat = localitat;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    @Override
    public String toString() {
        return "Ciutada{" +
                "nif='" + nif + '\'' +
                ", nom='" + nom + '\'' +
                ", cognoms='" + cognoms + '\'' +
                ", telefon='" + telefon + '\'' +
                ", email='" + email + '\'' +
                ", domicili='" + domicili + '\'' +
                ", localitat='" + localitat + '\'' +
                ", pais='" + pais + '\'' +
                ", pin='" + contrasenya + '\'' +
                '}';
    }
}
