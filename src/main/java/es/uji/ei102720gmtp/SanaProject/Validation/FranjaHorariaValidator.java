package es.uji.ei102720gmtp.SanaProject.Validation;

import es.uji.ei102720gmtp.SanaProject.model.FranjaHoraria;
import org.springframework.validation.Errors;

public class FranjaHorariaValidator implements Validator{

    @Override
    public boolean supports(Class<?> cls) {
        return FranjaHoraria.class.equals(cls);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        FranjaHoraria franjaHoraria = (FranjaHoraria) obj;
        if (franjaHoraria.getHoraFinal().isBefore(franjaHoraria.getHoraInici())){
            errors.rejectValue("horaInici", "obligatori", "L'hora d'inici te que ser abans a l'hora de fi");
        }
        if (franjaHoraria.getHoraInici().isAfter(franjaHoraria.getHoraFinal())){
            errors.rejectValue("horaFinal", "obligatori", "L'hora final ha de ser després a l'hora d'inici");
        }

    }
}
