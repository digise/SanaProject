package es.uji.ei102720gmtp.SanaProject.Validation;

import es.uji.ei102720gmtp.SanaProject.dao.ZonaDao;
import es.uji.ei102720gmtp.SanaProject.model.ReservaDadesCompletes;
import es.uji.ei102720gmtp.SanaProject.model.Zona;
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
        ZonaDao zonaDao = reserva.getZonaDao();
        int capacitat = 0;
        for (int zonaId: reserva.getZones()){
            capacitat += zonaDao.getZona(zonaId).getCapacitat();
        }
        if (reserva.getZones().size() == 0)
            errors.rejectValue("zones", "obligatori",
                    "Cal seleccionar al menys 1 zona");
        else {
            if (reserva.getNombrePersones() <= 0)
                errors.rejectValue("nombrePersones", "Massa baix", "Cal que el nombre siga al menys 1");
            if (reserva.getNombrePersones() > capacitat)
                errors.rejectValue("nombrePersones", "Massa alt",
                        "Cal que el nombre siga menor o igual a la capacitat de totes les zones seleccionades");

            if (reserva.getZones().size() > reserva.getNombrePersones())
                errors.rejectValue("nombrePersones", "Massa baix",
                        "Cal que el nombre de persones siga major o igual al nombre de zones");
        }
    }
}
