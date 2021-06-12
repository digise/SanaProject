package es.uji.ei102720gmtp.SanaProject.services;

import es.uji.ei102720gmtp.SanaProject.model.ControladorAmbEspaiPublic;
import es.uji.ei102720gmtp.SanaProject.model.EspaiPublic;

import java.util.HashMap;
import java.util.List;

public interface InterfaceControladorsPerMunicipiService {

    public List<ControladorAmbEspaiPublic> controladorsPerMunicipi(int idMunicipi);
    public ControladorAmbEspaiPublic getControladorService(String nifControlador, int idMunicipi);
    public EspaiPublic getEspaiPublicDelControlador(int idMunicipi);
    public List<EspaiPublic> getEspaisPublicsPerMunicipi(int idMunicipi);
}
