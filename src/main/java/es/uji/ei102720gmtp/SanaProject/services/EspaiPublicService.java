package es.uji.ei102720gmtp.SanaProject.services;

import es.uji.ei102720gmtp.SanaProject.dao.EspaiPublicDao;
import es.uji.ei102720gmtp.SanaProject.dao.MunicipiDao;
import es.uji.ei102720gmtp.SanaProject.model.EspaiPublic;
import es.uji.ei102720gmtp.SanaProject.model.Municipi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EspaiPublicService implements InterfaceEspaiPublicService{

    @Autowired
    EspaiPublicDao espaiPublicDao;

    @Autowired
    MunicipiDao municipiDao;

    @Override
    public List<EspaiPublic> getEspaisPublicsPerProvincia(String provincia){
        List<EspaiPublic> espaisPublicsPerProvincia = new ArrayList<>();
        for (EspaiPublic espaiPublic : espaiPublicDao.getEspaisPublics()){
            Municipi municipi = municipiDao.getMunicipi(espaiPublic.getIdMunicipi());
            if (municipi.getProvincia().toString().equals(provincia))
                espaisPublicsPerProvincia.add(espaiPublic);
        }
        return espaisPublicsPerProvincia;
    }

}
