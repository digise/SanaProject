package es.uji.ei102720gmtp.SanaProject.services;

import es.uji.ei102720gmtp.SanaProject.dao.FranjaHorariaDao;
import es.uji.ei102720gmtp.SanaProject.dao.OcupaDao;
import es.uji.ei102720gmtp.SanaProject.dao.ReservaDao;
import es.uji.ei102720gmtp.SanaProject.dao.ZonaDao;
import es.uji.ei102720gmtp.SanaProject.model.Ocupa;
import es.uji.ei102720gmtp.SanaProject.model.ReservaTablas;
import es.uji.ei102720gmtp.SanaProject.model.Zona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservesService implements InterfaceReservesService{

    @Autowired
    ZonaDao zonaDao;

    @Autowired
    ReservaDao reservaDao;

    @Autowired
    OcupaDao ocupaDao;

    @Autowired
    FranjaHorariaDao franjaHorariaDao;

    @Override
    public List<ReservaTablas> reservesPerEspai(int idEspai) {
        List<ReservaTablas> res = new ArrayList<>();
        List<Zona> zonesEspai = zonaDao.getZonesFromEspai(idEspai);
        for (Zona zona : zonesEspai) {
            for (Ocupa ocupa : ocupaDao.getListaOcupaZona(zona.getId())) {
                int idZona = zona.getId();
                LocalTime horaInici = franjaHorariaDao.getFranjaHoraria(ocupa.getIdFranja()).getHoraInici();
                LocalTime horaFinal = franjaHorariaDao.getFranjaHoraria(ocupa.getIdFranja()).getHoraFinal();
                LocalDate data = ocupa.getDataReserva();
                ReservaTablas reserva = new ReservaTablas(reservaDao.getReserva(ocupa.getIdReserva()), idEspai, idZona, horaInici, horaFinal, data);
                res.add(reserva);
            }
        }
        System.out.println(res);
        return res;
    }
}
