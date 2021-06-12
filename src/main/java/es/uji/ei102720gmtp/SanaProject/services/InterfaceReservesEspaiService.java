package es.uji.ei102720gmtp.SanaProject.services;

import es.uji.ei102720gmtp.SanaProject.model.Reserva;

import java.util.List;

public interface InterfaceReservesEspaiService {

    public List<Reserva> reservesPerEspai(int idEspai);
}
