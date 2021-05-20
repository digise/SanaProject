package es.uji.ei102720gmtp.SanaProject.model;

import es.uji.ei102720gmtp.SanaProject.model.enums.EstatReserva;

import java.time.LocalDate;

public class Reserva {

    private int id;
    private String codiQr;
    private int nombrePersones;
    private LocalDate dataCreacio;
    private EstatReserva estat;
    private String nifCiutada;
    private int idFranja;

    public Reserva(){}

    public int getId() {
        return id;
    }

    public String getCodiQr() {
        return codiQr;
    }

    public int getNombrePersones() {
        return nombrePersones;
    }

    public LocalDate getDataCreacio() {
        return dataCreacio;
    }

    public EstatReserva getEstat() {
        return estat;
    }

    public String getNifCiutada() {
        return nifCiutada;
    }

    public int getIdFranja() {
        return idFranja;
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

    public void setDataCreacio(LocalDate dataCreacio) {
        this.dataCreacio = dataCreacio;
    }

    public void setEstat(EstatReserva estat) {
        this.estat = estat;
    }

    public void setNifCiutada(String nifCiutada) {
        this.nifCiutada = nifCiutada;
    }

    public void setIdFranja(int id_franja) {
        this.idFranja = id_franja;
    }


    @Override
    public String toString() {
        return "Reserva{" +
                "id='" + id + '\'' +
                ", codiQr='" + codiQr + '\'' +
                ", nombrePersones='" + nombrePersones + '\'' +
                ", dataCreacio='" + dataCreacio + '\'' +
                ", estat=" + estat +
                ", nif_ciutada='" + nifCiutada + '\'' +
                ", id_franja='" + idFranja + '\'' +
                '}';
    }
}
