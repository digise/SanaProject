package es.uji.ei102720gmtp.SanaProject.model;

public class Ocupa {

    private int idReserva;
    private int idZona;

    public Ocupa(){}

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getIdZona() {
        return idZona;
    }

    public void setIdZona(int idZona) {
        this.idZona = idZona;
    }

    @Override
    public String toString() {
        return "Ocupa{" +
                "idReserva='" + idReserva + '\'' +
                ", idZona='" + idZona + '\'' +
                '}';
    }
}
