package es.uji.ei102720gmtp.SanaProject.model.enums;

public enum TipusTerreny {
    SORRA("Sorra"),
    PEDRA("Pedra"),
    ROCAM("Rocam");

    private String descripcion;

    TipusTerreny(String descripcion) {
        this.descripcion = descripcion;
    }


    public String getDescripcion() {
        return descripcion;
    }
}
