package es.uji.ei102720gmtp.SanaProject.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class PeriodeServeiEspai {

    private int idEspai;
    private String nomServei;
    private LocalTime horaInici;
    private LocalTime horaFinal;
    private LocalDate dataInici;
    private LocalDate dataFinal;

    public int getIdEspai() {
        return idEspai;
    }

    public void setIdEspai(int idEspai) {
        this.idEspai = idEspai;
    }

    public String getNomServei() {
        return nomServei;
    }

    public void setNomServei(String nomServei) {
        this.nomServei = nomServei;
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
        return "PeriodeServeiEspai{" +
                "idEspai='" + idEspai + '\'' +
                ", nomServei='" + nomServei + '\'' +
                ", horaInici=" + horaInici +
                ", horaFinal=" + horaFinal +
                ", dataInici=" + dataInici +
                ", dataFinal=" + dataFinal +
                '}';
    }
}
