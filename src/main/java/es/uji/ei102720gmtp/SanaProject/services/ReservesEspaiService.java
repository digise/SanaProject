package es.uji.ei102720gmtp.SanaProject.services;

import es.uji.ei102720gmtp.SanaProject.dao.EspaiPublicDao;
import es.uji.ei102720gmtp.SanaProject.dao.OcupaDao;
import es.uji.ei102720gmtp.SanaProject.dao.ReservaDao;
import es.uji.ei102720gmtp.SanaProject.dao.ZonaDao;
import es.uji.ei102720gmtp.SanaProject.model.EspaiPublic;
import es.uji.ei102720gmtp.SanaProject.model.Ocupa;
import es.uji.ei102720gmtp.SanaProject.model.Reserva;
import es.uji.ei102720gmtp.SanaProject.model.Zona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservesEspaiService implements InterfaceReservesEspaiService {

    @Autowired
    ZonaDao zonaDao;

    @Autowired
    ReservaDao reservaDao;


    @Autowired
    OcupaDao ocupaDao;

    @Override
    public List<Reserva> reservesPerEspai(int idEspai) {
        List<Reserva> res = new ArrayList<>();
        List<Zona> zonesEspai = zonaDao.getZonesFromEspai(idEspai);
        for (Zona zona : zonesEspai) {
            for (Ocupa ocupa : ocupaDao.getListaOcupaZona(zona.getId())) {
                Reserva reserva = reservaDao.getReserva(ocupa.getIdReserva());
                res.add(reserva);
            }
        }
        System.out.println(res);
        return res;
    }
}
