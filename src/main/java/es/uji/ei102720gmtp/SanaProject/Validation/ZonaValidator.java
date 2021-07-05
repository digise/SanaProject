package es.uji.ei102720gmtp.SanaProject.Validation;

import es.uji.ei102720gmtp.SanaProject.model.Zona;
import org.springframework.validation.Errors;

public class ZonaValidator implements Validator{

    @Override
    public boolean supports(Class<?> cls) {
        return Zona.class.equals(cls);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Zona zona = (Zona) obj;
        if (zona.getCapacitat() < 1){
            errors.rejectValue("capacitat", "obligatori", "Cal introduir un valor major que 0");
        }

        if (zona.getCoordenades().equals("")){
            errors.rejectValue("coordenades", "obligatori", "Cal introduir un valor");
        }
    }
}
