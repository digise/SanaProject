package es.uji.ei102720gmtp.SanaProject.services;

import es.uji.ei102720gmtp.SanaProject.model.EspaiPublic;
import es.uji.ei102720gmtp.SanaProject.model.FranjaHoraria;
import es.uji.ei102720gmtp.SanaProject.model.Zona;

import java.time.LocalDate;
import java.util.List;

public interface InterfaceEspaiPublicService {
    public List<EspaiPublic> getEspaisPublicsPerProvincia(String provincia);
    public List<Zona> getZonesDisponibles(int id, LocalDate dia, List<FranjaHoraria> franges);
    public List<FranjaHoraria> getFrangesHoraries(int idEspai);
    public Zona getZona(int idZona);
}
