package es.uji.ei102720gmtp.SanaProject.services;


import es.uji.ei102720gmtp.SanaProject.dao.*;
import es.uji.ei102720gmtp.SanaProject.model.Ocupa;
import es.uji.ei102720gmtp.SanaProject.model.Reserva;
import es.uji.ei102720gmtp.SanaProject.model.ReservaDadesCompletes;
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

    @Autowired
    OcupaDao ocupaDao;

    @Override
    public List<List<String>> getReservesDeClientTabla(String idReserva, String nifCiutada){
        List<Reserva> reservesClients = reservaDao.getReservesClient(nifCiutada);
        List<String> objetoTablaReserva = new ArrayList<>();
        List<List<String>> res = new ArrayList<>();
        /*
        for (Reserva reservaClient : reservesClients){
            reservaClient = reservaDao.getReserva(idReserva);
            Zona zona = zonaDao.getZona(reservaClient.getIdZona());
            String idZona = String.valueOf(zonaDao.getZona(reservaClient.getIdZona()).getId());
            String horaInici = franjaHorariaDao.getFranjaHoraria(reservaClient.getIdFranja()).getHoraInici().toString();
            String horaFinal = franjaHorariaDao.getFranjaHoraria(reservaClient.getIdFranja()).getHoraFinal().toString();
            String nomEspai = espaiPublicDao.getEspaiPublic(zona.getIdEspai()).getNom();

            objetoTablaReserva.add(nomEspai);
            objetoTablaReserva.add(idZona);
            objetoTablaReserva.add(String.valueOf(reservaClient.getNombrePersones()));
            objetoTablaReserva.add(horaInici);
            objetoTablaReserva.add(horaFinal);
            objetoTablaReserva.add(reservaClient.getDataReserva().toString());
            res.add(objetoTablaReserva);
        }

         */
        return res;
    }

    // @Todo
    @Override
    public ReservaDadesCompletes getReservaDadesCompletes(String idReserva) {
        ReservaDadesCompletes reservaDadesCompletes = new ReservaDadesCompletes();

        Reserva reserva = reservaDao.getReserva(Integer.valueOf(idReserva));
        Ocupa ocupa = ocupaDao.getOcupaFromIdReserva(Integer.valueOf(idReserva));

        reservaDadesCompletes.setIdReserva(idReserva);
        //   reservaDadesCompletes.setDataReserva(ocupa.getDataReserva());
        reservaDadesCompletes.setEstat(reserva.getEstat());
        reservaDadesCompletes.setCodiQR(reserva.getCodiQr());
        //  reservaDadesCompletes.setIdFranja(String.valueOf(ocupa.getIdFranja()));
        reservaDadesCompletes.setNombrePersones(reserva.getNombrePersones());
        reservaDadesCompletes.setIdZona(String.valueOf(ocupa.getIdZona()));

        return reservaDadesCompletes;
    }
}
