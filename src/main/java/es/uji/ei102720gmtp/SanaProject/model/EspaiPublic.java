package es.uji.ei102720gmtp.SanaProject.model;

import es.uji.ei102720gmtp.SanaProject.model.enums.TipusAcces;
import es.uji.ei102720gmtp.SanaProject.model.enums.TipusEspai;
import es.uji.ei102720gmtp.SanaProject.model.enums.TipusSol;

public class EspaiPublic
{
    private String id;
    private String idMunicipi;
    private String nom;
    private TipusEspai tipus;
    private TipusSol terreny;
    private TipusAcces tipusAcces;
    private String localitzacio;
    private Integer longitud;
    private Integer amplaria;
    private String imagen;
    private String descripcio;

    public EspaiPublic(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdMunicipi() {
        return idMunicipi;
    }

    public void setIdMunicipi(String idMunicipi) {
        this.idMunicipi = idMunicipi;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public TipusEspai getTipus() {
        return tipus;
    }

    public void setTipus(TipusEspai tipus) {
        this.tipus = tipus;
    }

    public TipusSol getTerreny() {
        return terreny;
    }

    public void setTerreny(TipusSol ing terreny) {
        this.terreny = terreny;
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
                "id='" + id + '\'' +
                ", idMunicipi='" + idMunicipi + '\'' +
                ", nom='" + nom + '\'' +
                ", tipus='" + tipus + '\'' +
                ", terreny=" + terreny +
                ", tipusAcces='" + tipusAcces + '\'' +
                ", localitzacio='" + localitzacio + '\'' +
                ", longitud='" + longitud + '\'' +
                ", amplaria='" + amplaria + '\'' +
                ", imagen='" + imagen + '\'' +
                ", descripciio='" + descripcio + '\'' +
                '}';
    }
}
