package es.uji.ei102720gmtp.SanaProject.model;

import es.uji.ei102720gmtp.SanaProject.model.enums.TipusUsuari;

public class UserDetails {
    String nif;
    String password;
    TipusUsuari tipusUsuari;


    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TipusUsuari getTipusUsuari() {
        return tipusUsuari;
    }

    public void setTipusUsuari(TipusUsuari tipusUsuari) {
        this.tipusUsuari = tipusUsuari;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "nif='" + nif + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
