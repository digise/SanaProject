package es.uji.ei102720gmtp.SanaProject.services;

import es.uji.ei102720gmtp.SanaProject.model.EspaiPublic;
import es.uji.ei102720gmtp.SanaProject.model.FranjaHoraria;
import es.uji.ei102720gmtp.SanaProject.model.Zona;

import java.time.LocalDate;
import java.util.List;

public interface InterfaceEspaiPublicService {
    public List<EspaiPublic> getEspaisPublicsPerProvincia(String provincia);
    public List<Zona> getZonesDisponibles(LocalDate dia, FranjaHoraria franjaHoraria, int idEspai);
    public List<FranjaHoraria> getFrangesHoraries(int idEspai);
    public Zona getZona(int idZona);
    public List<EspaiPublic> getEspaisPublicsPerMunicipi(int idMunicipi);
}
