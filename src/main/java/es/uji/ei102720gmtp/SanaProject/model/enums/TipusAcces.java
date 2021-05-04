package es.uji.ei102720gmtp.SanaProject.model.enums;

public enum TipusAcces {
    RESTRINGIT("Restringit"),
    OBERT("Obert"),
    TANCAT("Tancat");

    private final String access;

    TipusAcces(String terreny) {
        this.access = terreny;
    }

    @Override
    public String toString(){
        return access;
    }
}
