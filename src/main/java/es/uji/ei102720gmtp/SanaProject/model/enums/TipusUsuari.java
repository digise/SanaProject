package es.uji.ei102720gmtp.SanaProject.model.enums;

public enum TipusUsuari {

    CIUTADA("Ciutada"),
    GESTORMUNICIPAL("Gestor Municipal"),
    RESPONSABLE("Responsable de medi ambient"),
    CONTROLADOR("Controlador");


    private String descripcion;

    TipusUsuari(String descripcion){
        this.descripcion = descripcion;
    }


}
