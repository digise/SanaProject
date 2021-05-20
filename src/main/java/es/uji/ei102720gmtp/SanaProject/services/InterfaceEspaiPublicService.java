package es.uji.ei102720gmtp.SanaProject.services;

import es.uji.ei102720gmtp.SanaProject.model.EspaiPublic;
import es.uji.ei102720gmtp.SanaProject.model.enums.Provincia;

import java.util.List;
import java.util.Map;

public interface InterfaceEspaiPublicService {
    public List<EspaiPublic> getEspaisPublicsPerProvincia(String provincia);
}
