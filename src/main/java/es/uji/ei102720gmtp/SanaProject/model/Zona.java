package es.uji.ei102720gmtp.SanaProject.model;

public class Zona {
    private String id;
    private String idEspai;
    private String nom;
    private int capacitat;
    private String coordenades;

    public Zona(){}

    public String getId() {
        return id;
    }

    public String getIdEspai() {
        return idEspai;
    }

    public String getNom() {
        return nom;
    }

    public int getCapacitat() {
        return capacitat;
    }

    public String getCoordenades() {
        return coordenades;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIdEspai(String idEspai) {
        this.idEspai = idEspai;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCapacitat(int capacitat) {
        this.capacitat = capacitat;
    }

    public void setCoordenades(String coordenades) {
        this.coordenades = coordenades;
    }

    @Override
    public String toString() {
        return "Zona{" +
                "id='" + id + '\'' +
                ", idEspai='" + idEspai + '\'' +
                ", nom='" + nom + '\'' +
                ", capacitat=" + capacitat +
                ", coordenades='" + coordenades + '\'' +
                '}';
    }
}
