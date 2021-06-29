package es.uji.ei102720gmtp.SanaProject.services;

import es.uji.ei102720gmtp.SanaProject.dao.*;
import es.uji.ei102720gmtp.SanaProject.model.Ocupa;
import es.uji.ei102720gmtp.SanaProject.model.Reserva;
import es.uji.ei102720gmtp.SanaProject.model.ReservaTablas;
import es.uji.ei102720gmtp.SanaProject.model.Zona;
import es.uji.ei102720gmtp.SanaProject.model.enums.EstatReserva;
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

    @Autowired
    EspaiPublicDao espaiPublicDao;

    @Autowired
    MunicipiDao municipiDao;

    @Override
    public List<ReservaTablas> reservesPerEspai(int idEspai) {
        List<ReservaTablas> res = new ArrayList<>();
        List<Zona> zonesEspai = zonaDao.getZonesFromEspai(idEspai);
        for (Zona zona : zonesEspai) {
            for (Ocupa ocupa : ocupaDao.getListaOcupaZona(zona.getId())) {
                int idZona = zona.getId();
                LocalTime horaInici = franjaHorariaDao.getFranjaHoraria(ocupa.getIdFranja()).getHoraInici();
                LocalTime horaFinal = franjaHorariaDao.getFranjaHoraria(ocupa.getIdFranja()).getHoraFinal();
                Reserva reserva = reservaDao.getReserva(ocupa.getIdReserva());
                LocalDate data = ocupa.getDataReserva();
                if (reserva.getEstat() != EstatReserva.CANCELADAPERGESTORMUNICIPAL && reserva.getEstat() != EstatReserva.CANCELADAPERCIUTADA && reserva.getEstat() != EstatReserva.CANCELADAPERCONTROLADOR) {
                    if (LocalDate.now().isAfter(data) && LocalTime.now().isAfter(horaFinal))
                        reserva.setEstat(EstatReserva.FIUS);
                    else if (LocalDate.now().equals(data) && LocalTime.now().isAfter(horaInici) && LocalTime.now().isBefore(horaFinal))
                        reserva.setEstat(EstatReserva.ENUS);
                    else
                        reserva.setEstat(EstatReserva.PENDENTUS);
                }


                ReservaTablas reservaTablas = new ReservaTablas(reserva, idEspai, espaiPublicDao.getEspaiPublic(idEspai).getNom(), municipiDao.getMunicipi(espaiPublicDao.getEspaiPublic(idEspai).getIdMunicipi()).getNom(), idZona, horaInici, horaFinal, data);
                res.add(reservaTablas);
            }
        }
        return res;
    }

    @Override
    public List<ReservaTablas> reservesPerClient(String nifCiutada) {
        List<ReservaTablas> res = new ArrayList<>();
        for (Reserva reserva : reservaDao.getReserves()){
            if (reserva.getNifCiutada().equals(nifCiutada)){
                for (Ocupa ocupa : ocupaDao.getOcupesPerIdReserva(reserva.getId())){
                    int idZona = ocupa.getIdZona();
                    int idEspai = zonaDao.getZona(idZona).getIdEspai();
                    LocalTime horaInici = franjaHorariaDao.getFranjaHoraria(ocupa.getIdFranja()).getHoraInici();
                    LocalTime horaFinal = franjaHorariaDao.getFranjaHoraria(ocupa.getIdFranja()).getHoraFinal();
                    LocalDate data = ocupa.getDataReserva();
                    reserva = reservaDao.getReserva(ocupa.getIdReserva());
                    if (reserva.getEstat() != EstatReserva.CANCELADAPERGESTORMUNICIPAL && reserva.getEstat() != EstatReserva.CANCELADAPERCIUTADA && reserva.getEstat() != EstatReserva.CANCELADAPERCONTROLADOR) {
                        if (LocalDate.now().isAfter(data) && LocalTime.now().isAfter(horaFinal))
                            reserva.setEstat(EstatReserva.FIUS);
                        else if (LocalDate.now().equals(data) && LocalTime.now().isAfter(horaInici) && LocalTime.now().isBefore(horaFinal))
                            reserva.setEstat(EstatReserva.ENUS);
                        else
                            reserva.setEstat(EstatReserva.PENDENTUS);
                    };

                    ReservaTablas reservaTablas = new ReservaTablas(reserva, idEspai, espaiPublicDao.getEspaiPublic(idEspai).getNom(), municipiDao.getMunicipi(espaiPublicDao.getEspaiPublic(idEspai).getIdMunicipi()).getNom(), idZona, horaInici, horaFinal, data);
                    res.add(reservaTablas);
                }
            }
        }
        return res;
    }

    @Override
    public int ocupacioPerEspai(int idEspai) {
        int ocupacio = 0;
        List<Zona> zonesEspai = zonaDao.getZonesFromEspai(idEspai);
        for (Zona zona : zonesEspai) {
            for (Ocupa ocupa : ocupaDao.getListaOcupaZona(zona.getId())) {
                int idZona = zona.getId();
                Reserva reserva = reservaDao.getReserva(ocupa.getIdReserva());
                if (reserva.getEstat() == EstatReserva.ENUS){
                    ocupacio += reserva.getNombrePersones();
                }
            }
        }
        return ocupacio;
    }
}
