package es.uji.ei102720gmtp.SanaProject.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public class FranjaHoraria {
    private String id;
    private String idEspai;
    private String descripcio;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime horaInici;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime horaFinal;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataInici;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataFinal;

    public FranjaHoraria() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdEspai() {
        return idEspai;
    }

    public void setIdEspai(String idEspai) {
        this.idEspai = idEspai;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
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

    public LocalDate getDataInici() {
        return dataInici;
    }

    public void setDataInici(LocalDate dataInici) {
        this.dataInici = dataInici;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    @Override
    public String toString() {
        return "FranjaHoraria{" +
                "id='" + id + '\'' +
                ", idEspai='" + idEspai + '\'' +
                ", descripcio='" + descripcio + '\'' +
                ", horaInici='" + horaInici + '\'' +
                ", horaFinal=" + horaFinal +
                ", dataInici='" + dataInici + '\'' +
                ", dataFinal='" + dataFinal + '\'' +
                '}';
    }
}