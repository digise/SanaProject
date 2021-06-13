package es.uji.ei102720gmtp.SanaProject.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class ElegirZonaBean {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate diaElegit;
    private int idEspai;

    public ElegirZonaBean(int idEspai, LocalDate diaElegit) {
        this.idEspai = idEspai;
        this.diaElegit = diaElegit;
    }

    public LocalDate getDiaElegit() {
        return diaElegit;
    }

    public void setDiaElegit(LocalDate diaElegit) {
        this.diaElegit = diaElegit;
    }

    public int getIdEspai() {
        return idEspai;
    }

    public void setIdEspai(int idEspai) {
        this.idEspai = idEspai;
    }

    @Override
    public String toString() {
        return "ElegirZonaBean{" +
                "diaElegit=" + diaElegit +
                ", idEspai=" + idEspai +
                '}';
    }
}
