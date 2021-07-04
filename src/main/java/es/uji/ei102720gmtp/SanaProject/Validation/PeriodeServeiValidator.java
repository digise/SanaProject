package es.uji.ei102720gmtp.SanaProject.Validation;

import es.uji.ei102720gmtp.SanaProject.model.PeriodeServeiEspai;
import es.uji.ei102720gmtp.SanaProject.model.ServeiEstacional;
import org.springframework.validation.Errors;

public class PeriodeServeiValidator implements Validator{

    @Override
    public boolean supports(Class<?> cls) {
        return PeriodeServeiEspai.class.equals(cls);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        PeriodeServeiEspai periodeServeiEspai = (PeriodeServeiEspai) obj;

        if (!periodeServeiEspai.getDataFinal().isAfter(periodeServeiEspai.getDataInici())){
            errors.rejectValue("dataFinal", "incorrecta", "Cal que la data final siga posterior a la inicial");
        }
    }
}
