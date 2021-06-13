package es.uji.ei102720gmtp.SanaProject.model.enums;

public enum TipusAcces {
    RESTRINGIT("Restringit"),
    OBERT("Obert"),
    TANCAT("Tancat");

    private String descripcion;

    TipusAcces(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
