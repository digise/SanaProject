package es.uji.ei102720gmtp.SanaProject.model;

import es.uji.ei102720gmtp.SanaProject.model.enums.TipusAcces;
import es.uji.ei102720gmtp.SanaProject.model.enums.TipusTerreny;

public class EspaiPublic
{
    private int id;
    private int idMunicipi;
    private String nom;
    private String tipus;
    private TipusTerreny tipusTerreny;
    private TipusAcces tipusAcces;
    private String localitzacio;
    private Integer longitud;
    private Integer amplaria;
    private String imagen;
    private String descripcio;


    public EspaiPublic(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMunicipi() {
        return idMunicipi;
    }

    public void setIdMunicipi(int idMunicipi) {
        this.idMunicipi = idMunicipi;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public TipusTerreny getTipusTerreny() {
        return tipusTerreny;
    }

    public void setTipusTerreny(TipusTerreny tipusTerreny) {
        this.tipusTerreny = tipusTerreny;
    }

    public TipusAcces getTipusAcces() {
        return tipusAcces;
    }

    public void setTipusAcces(TipusAcces tipusAcces) {
        this.tipusAcces = tipusAcces;
    }

    public String getLocalitzacio() {
        return localitzacio;
    }

    public void setLocalitzacio(String localitzacio) {
        this.localitzacio = localitzacio;
    }

    public Integer getLongitud() {
        return longitud;
    }

    public void setLongitud(Integer longitud) {
        this.longitud = longitud;
    }

    public Integer getAmplaria() {
        return amplaria;
    }

    public void setAmplaria(Integer amplaria) {
        this.amplaria = amplaria;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    @Override
    public String toString() {
        return "EspaiPublic{" +
                "id=" + id +
                ", idMunicipi=" + idMunicipi +
                ", nom='" + nom + '\'' +
                ", tipus='" + tipus + '\'' +
                ", tipusTerreny=" + tipusTerreny +
                ", tipusAcces=" + tipusAcces +
                ", localitzacio='" + localitzacio + '\'' +
                ", longitud=" + longitud +
                ", amplaria=" + amplaria +
                ", imagen='" + imagen + '\'' +
                ", descripcio='" + descripcio + '\'' +
                '}';
    }
}
