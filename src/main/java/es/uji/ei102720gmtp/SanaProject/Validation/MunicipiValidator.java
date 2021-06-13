package es.uji.ei102720gmtp.SanaProject.Validation;

import es.uji.ei102720gmtp.SanaProject.model.Municipi;
import org.springframework.validation.Errors;

import java.util.Arrays;
import java.util.List;

public class MunicipiValidator implements Validator{

    @Override
    public boolean supports(Class<?> cls){
        return Municipi.class.equals(cls);
    }

    @Override
    public void validate (Object obj, Errors errors){
        Municipi municipi = (Municipi) obj;
        if (municipi.getNom().trim().equals(""))
            errors.rejectValue("nom", "obligatori", "Cal introduir un valor");
    }
}
