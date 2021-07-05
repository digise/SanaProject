package es.uji.ei102720gmtp.SanaProject.model;

import java.time.LocalDate;

public class Email implements Comparable<Email>{

    private int id;
    private LocalDate dataEnviament;
    private String emisor;
    private String receptor;
    private String asunto;
    private String mensaje;
    private String nifCiutada;

    public Email(){}

    public Email(LocalDate dataEnviament, String emisor, String receptor, String asunto, String mensaje, String nifCiutada) {
        this.dataEnviament = dataEnviament;
        this.emisor = emisor;
        this.receptor = receptor;
        this.asunto = asunto;
        this.mensaje = mensaje;
        this.nifCiutada = nifCiutada;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDataEnviament() {
        return dataEnviament;
    }

    public void setDataEnviament(LocalDate dataEnviament) {
        this.dataEnviament = dataEnviament;
    }

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getNifCiutada() {
        return nifCiutada;
    }

    public void setNifCiutada(String nifCiutada) {
        this.nifCiutada = nifCiutada;
    }

    @Override
    public int compareTo(Email altreEmail) {
        return altreEmail.getDataEnviament().compareTo(this.getDataEnviament());
    }

    @Override
    public String toString() {
        return "Correu{" +
                "dataEnviament=" + dataEnviament +
                ", emisor='" + emisor + '\'' +
                ", receptor='" + receptor + '\'' +
                ", asunto='" + asunto + '\'' +
                ", mensaje='" + mensaje + '\'' +
                '}';
    }
}
