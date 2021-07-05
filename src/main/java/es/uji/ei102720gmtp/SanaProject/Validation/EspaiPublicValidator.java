package es.uji.ei102720gmtp.SanaProject.Validation;

import es.uji.ei102720gmtp.SanaProject.model.EspaiPublic;
import org.springframework.validation.Errors;

public class EspaiPublicValidator implements Validator {

    @Override
    public boolean supports(Class<?> cls){
        return EspaiPublic.class.equals(cls);
    }

    @Override
    public void validate(Object obj, Errors errors){
        EspaiPublic espaiPublic = (EspaiPublic) obj;
        if (espaiPublic.getIdMunicipi() == 0)
            errors.rejectValue("idMunicipi", "obligatori", "Cal introduir un id de municipi");
        if (espaiPublic.getNom().trim().equals(""))
            errors.rejectValue("nom", "obligatori", "Cal introduir un nom per al espai");
        if (espaiPublic.getTipus().trim().equals(""))
            errors.rejectValue("tipus", "obligatori", "Cal introduir el tipus de espai");
        if (espaiPublic.getLocalitzacio().trim().equals(""))
            errors.rejectValue("localitzacio", "obligatori", "Cal introduir la localització");
        if (espaiPublic.getLongitud() == null)
            errors.rejectValue("longitud", "obligatori", "Cal introduir la longitud");
        else
            if (espaiPublic.getLongitud() < 1000)
                errors.rejectValue("longitud", "massa baix", "La longitud deu ser major de 1000 metres");
        if (espaiPublic.getAmplaria() == null)
            errors.rejectValue("amplaria", "obligatori", "Cal introduir la amplaria");
        else
            if (espaiPublic.getAmplaria() < 2000)
                errors.rejectValue("amplaria", "massa baix", "La amplaria deu ser major de 2000 metres");
        if (espaiPublic.getImagen().trim().equals(""))
            errors.rejectValue("imagen", "obligatori", "Cal introduir la url de la image");
        if (espaiPublic.getDescripcio().trim().equals(""))
            errors.rejectValue("descripcio", "obligatori", "Cal introduir una descripció");
    }
}
