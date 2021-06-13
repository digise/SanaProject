package es.uji.ei102720gmtp.SanaProject.services;


import java.util.List;

public interface InterfaceReservesClientService {
    public List<List<String>> getReservesDeClientTabla(String idReserva, String nifCiutada);
}
