package es.uji.ei102720gmtp.SanaProject.Validation;

import es.uji.ei102720gmtp.SanaProject.model.GestorMunicipal;
import org.springframework.validation.Errors;


public class GestorMunicipalValidator implements Validator {

    @Override
    public boolean supports(Class<?> cls){
        return GestorMunicipal.class.equals(cls);
    }

    @Override
    public void validate (Object obj, Errors errors){
        GestorMunicipal gestorMunicipal = (GestorMunicipal) obj;
        if (gestorMunicipal.getNom().trim().equals(""))
            errors.rejectValue("nom", "obligatori", "Cal introduir un valor");
        if (gestorMunicipal.getCognoms().trim().equals(""))
            errors.rejectValue("cognoms", "obligatori", "Cal introduir un valor");
        if (gestorMunicipal.getEmail().trim().equals(""))
            errors.rejectValue("email", "obligatori", "Cal introduir un valor");
        if (gestorMunicipal.getTelefon() == null || gestorMunicipal.getTelefon().length() != 9)
            errors.rejectValue("telefon", "no permitit", "El número de telèfon deu tindre 9 digits");
        if (gestorMunicipal.getContrasenya().trim().equals(""))
            errors.rejectValue("contrasenya", "obligatori", "Cal introduir un valor");
        if (gestorMunicipal.getNif().trim().length() != 9)
            errors.rejectValue("nif", "no permitit", "El nif ha de tindre 9 caràcteres");
    }
}
