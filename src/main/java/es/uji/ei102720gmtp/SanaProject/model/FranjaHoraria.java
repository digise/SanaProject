package es.uji.ei102720gmtp.SanaProject.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public class FranjaHoraria {
    private int id;
    private int idEspai;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime horaInici;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime horaFinal;


    public FranjaHoraria() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEspai() {
        return idEspai;
    }

    public void setIdEspai(int idEspai) {
        this.idEspai = idEspai;
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

    @Override
    public String toString() {
        return "FranjaHoraria{" +
                "id=" + id +
                ", idEspai=" + idEspai +
                ", horaInici=" + horaInici +
                ", horaFinal=" + horaFinal +
                '}';
    }
}