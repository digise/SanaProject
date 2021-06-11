package es.uji.ei102720gmtp.SanaProject.services;

import es.uji.ei102720gmtp.SanaProject.dao.EspaiPublicDao;
import es.uji.ei102720gmtp.SanaProject.dao.FranjaHorariaDao;
import es.uji.ei102720gmtp.SanaProject.dao.MunicipiDao;
import es.uji.ei102720gmtp.SanaProject.dao.ZonaDao;
import es.uji.ei102720gmtp.SanaProject.model.EspaiPublic;
import es.uji.ei102720gmtp.SanaProject.model.FranjaHoraria;
import es.uji.ei102720gmtp.SanaProject.model.Municipi;
import es.uji.ei102720gmtp.SanaProject.model.Zona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EspaiPublicService implements InterfaceEspaiPublicService{

    @Autowired
    EspaiPublicDao espaiPublicDao;

    @Autowired
    MunicipiDao municipiDao;

    @Autowired
    FranjaHorariaDao franjaHorariaDao;

    @Autowired
    ZonaDao zonaDao;

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

    @Override
    public List<Zona> getZonesDisponibles(int id, LocalDate dia, List<FranjaHoraria> franges) {
        List<Zona> zonesByEspai = zonaDao.getZonesFromEspai(id);
        return zonesByEspai;
    }

    @Override
    public List<FranjaHoraria> getFrangesHoraries(int idEspai) {
        List<FranjaHoraria> frangesHoraries = franjaHorariaDao.getFranjasEspai(idEspai);
        return frangesHoraries;
    }

    @Override
    public Zona getZona(int idZona) {
        return zonaDao.getZona(idZona);
    }
}
