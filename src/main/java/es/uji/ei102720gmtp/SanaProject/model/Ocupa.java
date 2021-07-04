package es.uji.ei102720gmtp.SanaProject.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

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
                "idReserva=" + idReserva +
                ", idZona=" + idZona +
                '}';
    }
}
