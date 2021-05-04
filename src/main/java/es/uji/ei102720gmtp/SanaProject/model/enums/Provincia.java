package es.uji.ei102720gmtp.SanaProject.model.enums;

import java.util.Locale;

public enum Provincia {
    VALENCIA("Valencia"),
    CASTELLO("Castello"),
    ALACANT("Alacant");

    private final String provincia;

    Provincia(String provincia) {
        this.provincia = provincia;
    }

    @Override
    public String toString(){
        return provincia;
    }
}
