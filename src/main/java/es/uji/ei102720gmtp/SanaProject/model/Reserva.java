package es.uji.ei102720gmtp.SanaProject.model;

import es.uji.ei102720gmtp.SanaProject.dao.ZonaDao;
import es.uji.ei102720gmtp.SanaProject.model.enums.EstatReserva;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


public class Reserva {

    private int id;
    private String codiQr;
    private int nombrePersones;
    private EstatReserva estat;
    private String nifCiutada;

    public Reserva(){
    }

    public int getId() {
        return id;
    }

    public String getCodiQr() {
        return codiQr;
    }

    public int getNombrePersones() {
        return nombrePersones;
    }


    public EstatReserva getEstat() {
        return estat;
    }

    public String getNifCiutada() {
        return nifCiutada;
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setCodiQr(String codiQr) {
        this.codiQr = codiQr;
    }

    public void setNombrePersones(int nombrePersones) {
        this.nombrePersones = nombrePersones;
    }

    public void setEstat(EstatReserva estat) {
        this.estat = estat;
    }

    public void setNifCiutada(String nifCiutada) {
        this.nifCiutada = nifCiutada;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", codiQr='" + codiQr + '\'' +
                ", nombrePersones=" + nombrePersones +
                ", estat=" + estat +
                ", nifCiutada='" + nifCiutada + '\'' +
                '}';
    }

}
