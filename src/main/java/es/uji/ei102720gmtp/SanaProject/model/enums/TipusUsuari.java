package es.uji.ei102720gmtp.SanaProject.model.enums;

public enum TipusUsuari {

    CIUTADA("Ciutada"),
    GESTORMUNICIPAL("Gestor Municipal");


    private final String tipusCiutada;

    TipusUsuari(String tipusCiutada){
        this.tipusCiutada = tipusCiutada;
    }

    @Override
    public String toString() {
        return tipusCiutada;
    }
}
