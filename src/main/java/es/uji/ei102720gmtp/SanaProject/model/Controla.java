package es.uji.ei102720gmtp.SanaProject.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class Controla {
    private String nifControlador;
    private int idEspai;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataInici;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataFinal;

    public Controla() {
    }

    public String getNifControlador() {
        return nifControlador;
    }

    public void setNifControlador(String nifControlador) {
        this.nifControlador = nifControlador;
    }

    public int getIdEspai() {
        return idEspai;
    }

    public void setIdEspai(int idEspai) {
        this.idEspai = idEspai;
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
        return "Controla{" +
                "nifControlador='" + nifControlador + '\'' +
                ", idEspai='" + idEspai + '\'' +
                "dataInici='" + dataInici + '\'' +
                ", dataFinal='" + dataFinal + '\'' +
                '}';
    }
}

