package es.uji.ei102720gmtp.SanaProject.model.enums;

public enum EstatReserva {
    PENDENTUS("Pendent d'ús"),
    ENUS("En ús"),
    FIUS("Fi ús"),
    CANCELADAPERCIUTADA("Cancelada pel ciutada"),
    CANCELADAPERCONTROLADOR("Cancelada pel controlador"),
    CANCELADAPERGESTORMUNICIPAL("Cancelada pel gestor municipal");

    private String descripcion;

    EstatReserva(String descripcion){
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
