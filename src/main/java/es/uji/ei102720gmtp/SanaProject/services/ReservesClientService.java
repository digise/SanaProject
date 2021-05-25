package es.uji.ei102720gmtp.SanaProject.services;


import es.uji.ei102720gmtp.SanaProject.dao.*;
import es.uji.ei102720gmtp.SanaProject.model.Reserva;
import es.uji.ei102720gmtp.SanaProject.model.Zona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ReservesClientService implements InterfaceReservesClientService{


    @Autowired
    ReservaDao reservaDao;

    @Autowired
    FranjaHorariaDao franjaHorariaDao;

    @Autowired
    EspaiPublicDao espaiPublicDao;

    @Autowired
    ZonaDao zonaDao;

    @Override
    public List<List<String>> getReservesDeClientTabla(String idReserva, String nifCiutada){
        List<Reserva> reservesClients = reservaDao.getReservesClient(nifCiutada);
        List<String> objetoTablaReserva = new ArrayList<>();
        List<List<String>> res = new ArrayList<>();
        for (Reserva reservaClient : reservesClients){
            reservaClient = reservaDao.getReserva(idReserva);
            Zona zona = zonaDao.getZona(reservaClient.getIdZona());
            String nomZona = zonaDao.getZona(reservaClient.getIdZona()).getNom();
            String horaInici = franjaHorariaDao.getFranjaHoraria(reservaClient.getIdFranja()).getHoraInici().toString();
            String horaFinal = franjaHorariaDao.getFranjaHoraria(reservaClient.getIdFranja()).getHoraFinal().toString();
            String nomEspai = espaiPublicDao.getEspaiPublic(zona.getIdEspai()).getNom();

            objetoTablaReserva.add(nomEspai);
            objetoTablaReserva.add(nomZona);
            objetoTablaReserva.add(String.valueOf(reservaClient.getNombrePersones()));
            objetoTablaReserva.add(horaInici);
            objetoTablaReserva.add(horaFinal);
            objetoTablaReserva.add(reservaClient.getDataReserva().toString());
            res.add(objetoTablaReserva);
        }

        return res;










    }



}