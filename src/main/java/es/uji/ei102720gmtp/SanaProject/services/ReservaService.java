package es.uji.ei102720gmtp.SanaProject.services;

import es.uji.ei102720gmtp.SanaProject.dao.FranjaHorariaDao;
import es.uji.ei102720gmtp.SanaProject.dao.ReservaDao;
import es.uji.ei102720gmtp.SanaProject.model.FranjaHoraria;
import es.uji.ei102720gmtp.SanaProject.model.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservaService {

    @Autowired
    FranjaHorariaDao franjaHorariaDao;

    @Autowired
    ReservaDao reservaDao;


    public List<FranjaHoraria> getFrangesHorariesDisponibles(int idEspai, int idZona, LocalDate data) {
        List<FranjaHoraria> frangesHoraries = franjaHorariaDao.getFranjasEspai(idEspai);

        return null;
    }
}
