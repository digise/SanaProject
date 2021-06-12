package es.uji.ei102720gmtp.SanaProject.services;

import es.uji.ei102720gmtp.SanaProject.model.EspaiPublic;
import es.uji.ei102720gmtp.SanaProject.model.FranjaHoraria;
import es.uji.ei102720gmtp.SanaProject.model.Zona;
import es.uji.ei102720gmtp.SanaProject.model.enums.Provincia;

import java.util.List;
import java.util.Map;

public interface InterfaceEspaiPublicService {
    public List<EspaiPublic> getEspaisPublicsPerProvincia(String provincia);
    public List<Zona> getZonesFromEspai(int id);
    public List<FranjaHoraria> getFrangesHorariesDisponibles(int idEspai, int idZona);
    public Zona getZona(int idZona);
    public List<EspaiPublic> getEspaisPublicsPerMunicipi(int idMunicipi);
}
