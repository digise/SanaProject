package es.uji.ei102720gmtp.SanaProject.services;

import es.uji.ei102720gmtp.SanaProject.model.ControladorAmbEspaiPublic;
import es.uji.ei102720gmtp.SanaProject.model.EspaiPublic;

import java.util.List;

public interface InterfaceMunicipisPerControladorService {

    public List<EspaiPublic> municipisPerControlador(String nifControlador);
}
