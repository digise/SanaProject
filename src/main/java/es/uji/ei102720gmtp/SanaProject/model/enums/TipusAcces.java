package es.uji.ei102720gmtp.SanaProject.model.enums;

public enum TipusAcces {
    RESTRINGIT("Restringit"),
    OBERT("Obert"),
    TANCAT("Tancat");

    private final String acces;

    TipusAcces(String acces) {
        this.acces = acces;
    }

    @Override
    public String toString(){
        return acces;
    }
}
