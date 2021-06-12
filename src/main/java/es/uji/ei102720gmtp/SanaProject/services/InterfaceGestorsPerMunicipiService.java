package es.uji.ei102720gmtp.SanaProject.services;

import es.uji.ei102720gmtp.SanaProject.model.GestorMunicipal;

import java.util.List;

public interface InterfaceGestorsPerMunicipiService {
    public List<GestorMunicipal> getGestorsPerMunicipi(int idMunicipi);
}
