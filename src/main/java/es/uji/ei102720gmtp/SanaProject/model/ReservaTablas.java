package es.uji.ei102720gmtp.SanaProject.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservaTablas extends Reserva{

    private int idEspai;
    private int idZona;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime horaInici;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime horaFinal;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataReserva;
    private Reserva reserva;

    public ReservaTablas(Reserva reserva, int idEspai, int idZona, LocalTime horaInici, LocalTime horaFinal, LocalDate dataReserva){
        this.reserva = reserva;
        this.idEspai = idEspai;
        this.idZona = idZona;
        this.horaInici = horaInici;
        this.horaFinal = horaFinal;
        this.dataReserva = dataReserva;
    }

    public int getIdEspai() {
        return idEspai;
    }

    public void setIdEspai(int idEspai) {
        this.idEspai = idEspai;
    }

    public int getIdZona() {
        return idZona;
    }

    public void setIdZona(int idZona) {
        this.idZona = idZona;
    }

    public LocalTime getHoraInici() {
        return horaInici;
    }

    public void setHoraInici(LocalTime horaInici) {
        this.horaInici = horaInici;
    }

    public LocalTime getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(LocalTime horaFinal) {
        this.horaFinal = horaFinal;
    }

    public LocalDate getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(LocalDate dataReserva) {
        this.dataReserva = dataReserva;
    }
}
