package es.uji.ei102720gmtp.SanaProject.model;


public class Controlador
{
    private String nif;
    private String nom;
    private String cognom;
    private String especialitat;
    private String email;
    private String telefon;

    public Controlador(){}


    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognom() {
        return cognom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public String getEspecialitat() {
        return especialitat;
    }

    public void setEspecialitat(String especialitat) {
        this.especialitat = especialitat;
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


    @Override
    public String toString() {
        return "Controlador{" +
                "nif='" + nif + '\'' +
                ", nom='" + nom + '\'' +
                ", cognom='" + cognom + '\'' +
                ", especialitat='" + especialitat + '\'' +
                ", email='" + email + '\'' +
                ", telefon='" + telefon + '\'' +
                '}';
    }
}

