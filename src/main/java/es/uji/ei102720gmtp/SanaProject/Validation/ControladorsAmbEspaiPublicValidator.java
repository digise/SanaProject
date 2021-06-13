package es.uji.ei102720gmtp.SanaProject.Validation;

import es.uji.ei102720gmtp.SanaProject.model.ControladorAmbEspaiPublic;
import org.springframework.validation.Errors;

public class ControladorsAmbEspaiPublicValidator implements Validator{

    @Override
    public boolean supports(Class<?> cls) {
        return ControladorAmbEspaiPublic.class.equals(cls);
        // o, si volguérem tractar també les subclasses:
        // return Nadador.class.isAssignableFrom(cls);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        ControladorAmbEspaiPublic controladorAmbEspaiPublic = (ControladorAmbEspaiPublic) obj;
        if (controladorAmbEspaiPublic.getControlador().getNom().trim().equals("")){

            errors.rejectValue("nom", "obligatori", "Cal introduir un valor");
        }
        if (controladorAmbEspaiPublic.getControlador().getCognoms().trim().equals("")){

            errors.rejectValue("cognoms", "obligatori", "Cal introduir un valor");
        }
        if (controladorAmbEspaiPublic.getControlador().getEmail().trim().equals("")){

            errors.rejectValue("email", "obligatori", "Cal introduir un valor");
        }
        if (controladorAmbEspaiPublic.getControlador().getTelefon().trim().equals("")){

            errors.rejectValue("telefon", "obligatori", "Cal introduir un valor");
        }
        //Afegeix ací la validació per nif <= 9
         if(controladorAmbEspaiPublic.getControlador().getNif().length() != 9)
            errors.rejectValue("nif", "no permitit" , "EL nif ha de tindre 9 caràcters");
        // Afegeix ací la validació per a carc telefon < 9
        if (Integer.parseInt(controladorAmbEspaiPublic.getControlador().getTelefon()) != 9)
            errors.rejectValue("telefon", "noPermitit" , "El número de telèfon deu tindre 9 dígits");

    }
}
