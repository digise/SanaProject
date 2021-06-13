package es.uji.ei102720gmtp.SanaProject.model;

import es.uji.ei102720gmtp.SanaProject.dao.ZonaDao;
import es.uji.ei102720gmtp.SanaProject.model.enums.EstatReserva;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class ReservaDadesCompletes {

    private int idEspai;
    private int idReserva;
    private String codiQR;
    private int nombrePersones;
    private EstatReserva estat;
    private String nifCiutada;
    private int idZona;
    private int idFranja;
    private ZonaDao zonaDao;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataReserva;

    public int getIdEspai() {
        return idEspai;
    }

    public void setIdEspai(int idEspai) {
        this.idEspai = idEspai;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public String getCodiQR() {
        return codiQR;
    }

    public void setCodiQR(String codiQR) {
        this.codiQR = codiQR;
    }

    public int getNombrePersones() {
        return nombrePersones;
    }

    public void setNombrePersones(int nombrePersones) {
        this.nombrePersones = nombrePersones;
    }

    public EstatReserva getEstat() {
        return estat;
    }

    public void setEstat(EstatReserva estat) {
        this.estat = estat;
    }

    public String getNifCiutada() {
        return nifCiutada;
    }

    public void setNifCiutada(String nifCiutada) {
        this.nifCiutada = nifCiutada;
    }

    public int getIdZona() {
        return idZona;
    }

    public void setIdZona(int idZona) {
        this.idZona = idZona;
    }

    public int getIdFranja() {
        return idFranja;
    }

    public void setIdFranja(int idFranja) {
        this.idFranja = idFranja;
    }

    public LocalDate getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(LocalDate dataReserva) {
        this.dataReserva = dataReserva;
    }

    @Override
    public String toString() {
        return "NovaReserva{" +
                "idEspai=" + idEspai +
                ", codiQR='" + codiQR + '\'' +
                ", nombrePersones=" + nombrePersones +
                ", estat=" + estat +
                ", nifCiutada='" + nifCiutada + '\'' +
                ", idZona=" + idZona +
                ", idFranja=" + idFranja +
                ", dataReserva=" + dataReserva +
                '}';
    }

    public ZonaDao getZonaDao() {
        return zonaDao;
    }

    public void setZonaDao(ZonaDao zonaDao) {
        this.zonaDao = zonaDao;
    }
}
