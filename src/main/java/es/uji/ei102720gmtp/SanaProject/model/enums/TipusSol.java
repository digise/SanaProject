package es.uji.ei102720gmtp.SanaProject.model.enums;

public enum TipusSol {
    SORRA("sorra"),
    PEDRA("pedra"),
    ROCAM("rocam");

    private final String tipusSol;

    TipusSol(String tipusSol) {
        this.tipusSol = tipusSol;
    }

    public String getTipusSol() {
        return tipusSol;
    }
}
