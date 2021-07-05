package es.uji.ei102720gmtp.SanaProject.model;

import es.uji.ei102720gmtp.SanaProject.dao.ZonaDao;
import es.uji.ei102720gmtp.SanaProject.model.enums.EstatReserva;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public class ReservaDadesCompletes {

    private String idEspai;
    private String idReserva;
    private String codiQR;
    private int nombrePersones;
    private EstatReserva estat;
    private String nifCiutada;
    private List<Integer> zones;
    private String idFranja;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataReserva;

    public String getIdEspai() {
        return idEspai;
    }

    public void setIdEspai(String idEspai) {
        this.idEspai = idEspai;
    }

    public String getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(String idReserva) {
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

    public List<Integer> getZones() {
        return zones;
    }

    public void setZones(List<Integer> zones) {
        this.zones = zones;
    }

    public String getIdFranja() {
        return idFranja;
    }

    public void setIdFranja(String idFranja) {
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
        return "ReservaDadesCompletes{" +
                "idEspai='" + idEspai + '\'' +
                ", idReserva='" + idReserva + '\'' +
                ", codiQR='" + codiQR + '\'' +
                ", nombrePersones=" + nombrePersones +
                ", estat=" + estat +
                ", nifCiutada='" + nifCiutada + '\'' +
                ", zones=" + zones +
                ", idFranja='" + idFranja + '\'' +
                ", dataReserva=" + dataReserva +
                ", zonaDao=" + zonaDao +
                '}';
    }

    private ZonaDao zonaDao;

    public ZonaDao getZonaDao() {
        return zonaDao;
    }

    public void setZonaDao(ZonaDao zonaDao) {
        this.zonaDao = zonaDao;
    }
}
