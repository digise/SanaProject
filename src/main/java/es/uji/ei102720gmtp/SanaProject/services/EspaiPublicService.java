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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<EspaiPublic> getEspaisPublicsPerMunicipi(int idMunicipi){
        List<EspaiPublic> espaisPublicsPerMunicipi = new ArrayList<>();
        for (EspaiPublic espaiPublic : espaiPublicDao.getEspaisPublics()){
            if (espaiPublic.getIdMunicipi() == idMunicipi)
                espaisPublicsPerMunicipi.add(espaiPublic);
        }
        return espaisPublicsPerMunicipi;
    }

    @Override
    public Map<Integer, List<Zona>> getZonesDisponibles(LocalDate dia, List<FranjaHoraria> franges, int idEspai) {
        Map<Integer, List<Zona>> zonesDisponibles = new HashMap<>();
        for (FranjaHoraria franja : franges){
            List<Zona> zones = zonaDao.getZonesFromFranjaDia(idEspai, franja.getId(), dia);
            zonesDisponibles.put(franja.getId(), zones);
        }
        return zonesDisponibles;
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
