package es.uji.ei102720gmtp.SanaProject.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class Ocupa {

    private int idReserva;
    private int idZona;
    private int idFranja;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataReserva;

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

    public int getIdFranja() {
        return this.idFranja;
    }

    public LocalDate getDataReserva() {
        return dataReserva;
    }

    public void setIdFranja(int idFranja) {
        this.idFranja = idFranja;
    }

    public void setDataReserva(LocalDate dataReserva) {
        this.dataReserva = dataReserva;
    }

    @Override
    public String toString() {
        return "Ocupa{" +
                "idReserva=" + idReserva +
                ", idZona=" + idZona +
                ", id_franja=" + idFranja +
                ", data_reserva=" + dataReserva +
                '}';
    }
}
