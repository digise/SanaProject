package es.uji.ei102720gmtp.SanaProject.model;

public class Zona {
    private int id;
    private int idEspai;
    private int capacitat;
    private String coordenades;

    public Zona(){}

    public int getId() {
        return id;
    }

    public int getIdEspai() {
        return idEspai;
    }

    public int getCapacitat() {
        return capacitat;
    }

    public String getCoordenades() {
        return coordenades;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdEspai(int idEspai) {
        this.idEspai = idEspai;
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
                ", capacitat=" + capacitat +
                ", coordenades='" + coordenades + '\'' +
                '}';
    }
}
