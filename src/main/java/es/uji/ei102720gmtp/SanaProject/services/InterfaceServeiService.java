package es.uji.ei102720gmtp.SanaProject.services;

import es.uji.ei102720gmtp.SanaProject.model.ServeiEstacionalComplet;
import es.uji.ei102720gmtp.SanaProject.model.ServeiPermanent;
import es.uji.ei102720gmtp.SanaProject.model.ServeiPermanentComplet;

import java.util.List;

public interface InterfaceServeiService {
    public List<ServeiPermanentComplet> getServeiPermanentInstalats(int idEspai);
    public List<ServeiEstacionalComplet> getServeisEstacionalsInstalats(int idEspai);
    public List<ServeiPermanent> getServeisRestants(List<ServeiPermanent> list);
}
