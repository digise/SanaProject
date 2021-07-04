package es.uji.ei102720gmtp.SanaProject.services;

import es.uji.ei102720gmtp.SanaProject.dao.PeriodeServeiEspaiDao;
import es.uji.ei102720gmtp.SanaProject.dao.ServeiEstacionalDao;
import es.uji.ei102720gmtp.SanaProject.dao.ServeiInstalatEspaiDao;
import es.uji.ei102720gmtp.SanaProject.dao.ServeiPermanentDao;
import es.uji.ei102720gmtp.SanaProject.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Service
public class ServeiService implements InterfaceServeiService{

    @Autowired
    private ServeiPermanentDao serveiPermanentDao;

    @Autowired
    private ServeiEstacionalDao serveiEstacionalDao;

    @Autowired
    private ServeiInstalatEspaiDao serveiInstalatEspaiDao;

    @Autowired
    private PeriodeServeiEspaiDao periodeServeiEspaiDao;

    @Override
    public List<ServeiPermanentComplet> getServeiPermanentInstalats(int idEspai) {
        List<ServeiPermanent> list = serveiPermanentDao.getServeisPermanentsFromEspai(idEspai);
        List<ServeiPermanentComplet> definitiu = new ArrayList<>();

        for (ServeiPermanent servei: list){
            ServeiPermanentComplet serveiComplet = new ServeiPermanentComplet();
            serveiComplet.setNom(servei.getNom());
            serveiComplet.setTipus(servei.getTipus());

            ServeiInstalatEspai serveiPerAData = serveiInstalatEspaiDao.getServeiInstalatEspai(idEspai, servei.getNom());
            serveiComplet.setDataApertura(serveiPerAData.getDataApertura());

            definitiu.add(serveiComplet);
        }

        return definitiu;
    }

    @Override
    public List<ServeiEstacionalComplet> getServeisEstacionalsInstalats(int idEspai) {
        List<PeriodeServeiEspai> list = periodeServeiEspaiDao.getServeisEstacionalsFromEspai(idEspai);
        List<ServeiEstacionalComplet> listDefinitiu = new ArrayList<>();
        for (PeriodeServeiEspai periode : list){
            ServeiEstacionalComplet servei = new ServeiEstacionalComplet();
            servei.setNom(periode.getNomServei());
            servei.setHoraInici(periode.getHoraInici());
            servei.setHoraFinal(periode.getHoraFinal());
            servei.setDataInici(periode.getDataInici());
            servei.setDataFinal(periode.getDataFinal());
            servei.setTipus(serveiEstacionalDao.getServeiEstacional(periode.getNomServei()).getTipus());

            listDefinitiu.add(servei);
        }
        return listDefinitiu;
    }

    @Override
    public List<ServeiPermanent> getServeisPermanentsRestants(List<ServeiPermanent> list) {
        List<ServeiPermanent> tots = serveiPermanentDao.getServeisPermanents();
        LinkedList<ServeiPermanent> definitu = new LinkedList<>();

        for (ServeiPermanent servei : tots)
            definitu.add(servei);

        Iterator<ServeiPermanent> iterator = definitu.iterator();

        while (iterator.hasNext()){
            ServeiPermanent servei = iterator.next();
            for (ServeiPermanent serveiPermanent: list){
                if (servei.getNom().equals(serveiPermanent.getNom())) {
                    tots.remove(servei);
                    break;
                }
            }
        }
        return tots;
    }

    @Override
    public List<ServeiEstacional> getServeisEstacionalsRestants(List<ServeiEstacional> list) {
        List<ServeiEstacional> tots = serveiEstacionalDao.getServeisEstacionals();
        LinkedList<ServeiEstacional> definitu = new LinkedList<>();

        for (ServeiEstacional servei : tots)
            definitu.add(servei);

        Iterator<ServeiEstacional> iterator = definitu.iterator();

        while (iterator.hasNext()){
            ServeiEstacional servei = iterator.next();
            for (ServeiEstacional serveiEstacional: list){
                if (servei.getNom().equals(serveiEstacional.getNom())) {
                    tots.remove(servei);
                    break;
                }
            }
        }
        return tots;
    }

    @Override
    public boolean isPermanentServeiUsed(String nom) {
        List<ServeiInstalatEspai> usedService = serveiInstalatEspaiDao.getServeisInstalatsFromNom(nom);
        if (usedService.isEmpty())
            return false;
        return true;
    }

    @Override
    public boolean isEstacionalServeiUsed(String nom) {
        List<PeriodeServeiEspai> usedService = periodeServeiEspaiDao.getServeisEstacionalsFromNom(nom);
        if (usedService.isEmpty())
            return false;
        return true;
    }
}
