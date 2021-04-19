package es.uji.ei102720gmtp.SanaProject.model;

public class EspaiPublic
{
    private String id;
    private String idMunicipi;
    private String nom;
    private String tipus;
    private String terreny;
    private String tipusAcces;
    private String localitzacio;
    private Integer longitud;
    private Integer amplaria;
    private String imagen;
    private String comentaris;
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

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public String getTerreny() {
        return terreny;
    }

    public void setTerreny(String terreny) {
        this.terreny = terreny;
    }

    public String getTipusAcces() {
        return tipusAcces;
    }

    public void setTipusAcces(String tipusAcces) {
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

    public String getComentaris() {
        return comentaris;
    }

    public void setComentaris(String comentaris) {
        this.comentaris = comentaris;
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
                ", comentaris='" + comentaris + '\'' +
                ", descripciio='" + descripcio + '\'' +
                '}';
    }
}
