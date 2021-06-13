package es.uji.ei102720gmtp.SanaProject.services;

import es.uji.ei102720gmtp.SanaProject.model.Reserva;
import es.uji.ei102720gmtp.SanaProject.model.ReservaTablas;

import java.util.List;

public interface InterfaceReservesService {

    public List<ReservaTablas> reservesPerEspai(int idEspai);

    public List<ReservaTablas> reservesPerClient(String nifCiutada);
}
