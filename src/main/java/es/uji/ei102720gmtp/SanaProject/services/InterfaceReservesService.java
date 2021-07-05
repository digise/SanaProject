package es.uji.ei102720gmtp.SanaProject.services;

import es.uji.ei102720gmtp.SanaProject.model.Reserva;
import es.uji.ei102720gmtp.SanaProject.model.ReservaTablas;

import java.util.List;

public interface InterfaceReservesService {

    public List<ReservaTablas> reservesPerEspaiGeneral(int idEspai);

    public List<ReservaTablas> reservesPerClient(String nifCiutada);

    public int ocupacioPerEspai(int idEspai);
}
