package es.uji.ei102720gmtp.SanaProject.Validation;

import es.uji.ei102720gmtp.SanaProject.dao.ZonaDao;
import es.uji.ei102720gmtp.SanaProject.model.ElegirZonaBean;
import es.uji.ei102720gmtp.SanaProject.model.ReservaDadesCompletes;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ElegirZonaBeanValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ElegirZonaBean.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ElegirZonaBean elegirZonaBean = (ElegirZonaBean) target;
        if (!elegirZonaBean.getDiaElegit().isAfter(LocalDate.now().plus(2, ChronoUnit.DAYS))){
            errors.rejectValue("diaElegit", "Dia no valid", "Cal que el dia siga minim 2 dies posterior a hui");
        }
    }
}
