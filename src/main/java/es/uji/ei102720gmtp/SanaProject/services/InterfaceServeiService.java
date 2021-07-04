package es.uji.ei102720gmtp.SanaProject.services;

import es.uji.ei102720gmtp.SanaProject.model.ServeiEstacional;
import es.uji.ei102720gmtp.SanaProject.model.ServeiEstacionalComplet;
import es.uji.ei102720gmtp.SanaProject.model.ServeiPermanent;
import es.uji.ei102720gmtp.SanaProject.model.ServeiPermanentComplet;

import java.util.List;

public interface InterfaceServeiService {
    public List<ServeiPermanentComplet> getServeiPermanentInstalats(int idEspai);
    public List<ServeiEstacionalComplet> getServeisEstacionalsInstalats(int idEspai);
    public List<ServeiPermanent> getServeisPermanentsRestants(List<ServeiPermanent> list);
    public List<ServeiEstacional> getServeisEstacionalsRestants(List<ServeiEstacional> list);
    public boolean isPermanentServeiUsed(String nom);
    public boolean isEstacionalServeiUsed(String nom);
}
