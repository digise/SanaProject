package es.uji.ei102720gmtp.SanaProject.Validation;

import es.uji.ei102720gmtp.SanaProject.dao.ZonaDao;
import es.uji.ei102720gmtp.SanaProject.model.ReservaDadesCompletes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ReservaValidator implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        return ReservaDadesCompletes.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ReservaDadesCompletes reserva = (ReservaDadesCompletes) target;
        if (reserva.getNombrePersones() <= 0)
            errors.rejectValue("nombrePersones", "Massa baix", "Cal que el nombre siga al menys 1");
        ZonaDao zonaDao = reserva.getZonaDao();
        int capacitat = zonaDao.getZona(reserva.getIdZona()).getCapacitat();
        if (reserva.getNombrePersones() > capacitat )
            errors.rejectValue("nombrePersones", "Massa alt", "Cal que el nombre siga menor o igual a la capacitat");
    }
}
