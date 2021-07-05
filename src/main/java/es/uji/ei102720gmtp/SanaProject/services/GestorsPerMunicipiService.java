package es.uji.ei102720gmtp.SanaProject.services;

import es.uji.ei102720gmtp.SanaProject.dao.EspaiPublicDao;
import es.uji.ei102720gmtp.SanaProject.dao.GestorMunicipalDao;
import es.uji.ei102720gmtp.SanaProject.model.EspaiPublic;
import es.uji.ei102720gmtp.SanaProject.model.GestorMunicipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GestorsPerMunicipiService implements InterfaceGestorsPerMunicipiService {

    @Autowired
    GestorMunicipalDao gestorMunicipalDao;

    @Autowired
    EspaiPublicDao espaiPublicDao;

    @Override
    public List<GestorMunicipal> getGestorsPerMunicipi(int idMunicipi) {
        List<GestorMunicipal> res = new ArrayList<>();
        for (GestorMunicipal gestorMunicipal : gestorMunicipalDao.getGestorsMunicipals()){
            if (gestorMunicipal.getIdMunicipi() == idMunicipi){
                res.add(gestorMunicipal);
            }
        }
        return res;
    }
}
