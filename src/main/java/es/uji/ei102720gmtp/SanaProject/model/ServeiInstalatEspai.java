package es.uji.ei102720gmtp.SanaProject.model;

import java.time.LocalDate;

public class ServeiInstalatEspai {

    private String nomServei;
    private int idEspai;
    private LocalDate dataApertura;

    public String getNomServei() {
        return nomServei;
    }

    public void setNomServei(String nomServei) {
        this.nomServei = nomServei;
    }

    public int getIdEspai() {
        return idEspai;
    }

    public void setIdEspai(int idEspai) {
        this.idEspai = idEspai;
    }

    public LocalDate getDataApertura() {
        return dataApertura;
    }

    public void setDataApertura(LocalDate dataApertura) {
        this.dataApertura = dataApertura;
    }

    @Override
    public String toString() {
        return "ServeiInstalatEspai{" +
                "nomServei='" + nomServei + '\'' +
                ", idEspai='" + idEspai + '\'' +
                ", dataApertura=" + dataApertura +
                '}';
    }
}
