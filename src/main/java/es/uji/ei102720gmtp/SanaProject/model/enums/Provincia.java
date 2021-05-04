package es.uji.ei102720gmtp.SanaProject.model.enums;

public enum Provincia {
    CASTELLO("Castello"),
    VALENCIA("Valencia"),
    ALACANT("Alacant");

    private final String provincia;

    Provincia(String provincia) {
        this.provincia = provincia;
    }

    public String getNombreProvincia() {
        return provincia;
    }
}
