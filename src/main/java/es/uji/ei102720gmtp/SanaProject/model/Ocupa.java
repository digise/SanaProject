package es.uji.ei102720gmtp.SanaProject.model;

public class Ocupa {

    private String idReserva;
    private String idZona;

    public Ocupa(){}

    public String getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(String idReserva) {
        this.idReserva = idReserva;
    }

    public String getIdZona() {
        return idZona;
    }

    public void setIdZona(String idZona) {
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
