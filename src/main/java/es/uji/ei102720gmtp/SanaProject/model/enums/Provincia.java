package es.uji.ei102720gmtp.SanaProject.model.enums;


public enum Provincia {
    VALENCIA("Valencia"),
    CASTELLO("Castello"),
    ALACANT("Alacant");

    private  String descripcion;

    Provincia(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
