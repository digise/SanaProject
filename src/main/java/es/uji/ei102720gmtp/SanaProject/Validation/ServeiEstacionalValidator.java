package es.uji.ei102720gmtp.SanaProject.Validation;

import es.uji.ei102720gmtp.SanaProject.model.ServeiEstacional;
import org.springframework.validation.Errors;

public class ServeiEstacionalValidator implements Validator{

    @Override
    public boolean supports(Class<?> cls) {
        return ServeiEstacional.class.equals(cls);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        ServeiEstacional serveiEstacional = (ServeiEstacional) obj;
        if (serveiEstacional.getNom().equals("")){
            errors.rejectValue("nom", "obligatori", "Cal introduir un nom");
        }

        if (serveiEstacional.getTipus().equals("")){
            errors.rejectValue("tipus", "obligatori", "Cal introduir el tipus");
        }
    }
}
