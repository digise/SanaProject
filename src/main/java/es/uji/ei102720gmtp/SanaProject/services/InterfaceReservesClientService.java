package es.uji.ei102720gmtp.SanaProject.services;


import es.uji.ei102720gmtp.SanaProject.model.ReservaDadesCompletes;

import java.util.List;

public interface InterfaceReservesClientService {
    public List<List<String>> getReservesDeClientTabla(String idReserva, String nifCiutada);
    public ReservaDadesCompletes getReservaDadesCompletes(String idReserva);
}
