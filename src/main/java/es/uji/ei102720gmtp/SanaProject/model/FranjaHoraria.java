package es.uji.ei102720gmtp.SanaProject.model;

import java.time.LocalDate;

public class FranjaHoraria {
    private String id;
    private String idEspai;
    private String descripcio;
    private LocalDate horaInici;
    private LocalDate horaFinal;
    private LocalDate dataInici;
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

    public LocalDate getHoraInici() {
        return horaInici;
    }

    public void setHoraInici(LocalDate horaInici) {
        this.horaInici = horaInici;
    }

    public LocalDate getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(LocalDate horaFinal) {
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