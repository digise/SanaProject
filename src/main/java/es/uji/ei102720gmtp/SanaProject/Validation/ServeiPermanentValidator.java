package es.uji.ei102720gmtp.SanaProject.Validation;

import es.uji.ei102720gmtp.SanaProject.dao.ServeiPermanentDao;
import es.uji.ei102720gmtp.SanaProject.model.ServeiPermanent;
import org.springframework.validation.Errors;

public class ServeiPermanentValidator implements Validator{


    @Override
    public boolean supports(Class<?> cls) {
        return ServeiPermanent.class.equals(cls);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        ServeiPermanent serveiPermanent = (ServeiPermanent) obj;
        if (serveiPermanent.getNom().equals("")){
            errors.rejectValue("nom", "obligatori", "Cal introduir un nom");
        }

        if (serveiPermanent.getTipus().equals("")){
            errors.rejectValue("tipus", "obligatori", "Cal introduir el tipus");
        }
    }
}
