package es.uji.ei102720gmtp.SanaProject.model.enums;

public enum TipusSol {
    SORRA("Sorra"),
    PEDRA("Pedra"),
    ROCAM("Rocam");

    private final String terreny;

    TipusSol(String terreny) {
        this.terreny = terreny;
    }

    @Override
    public String toString(){
        return terreny;
    }
}
