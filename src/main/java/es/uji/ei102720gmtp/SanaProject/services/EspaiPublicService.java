package es.uji.ei102720gmtp.SanaProject.services;

import es.uji.ei102720gmtp.SanaProject.dao.*;
import es.uji.ei102720gmtp.SanaProject.model.*;
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
            if (municipi.getProvincia().getDescripcion().equals(provincia))
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
    public List<Zona> getZonesDisponibles(LocalDate dia, FranjaHoraria franjaHoraria, int idEspai) {
        List<Zona> zonesDisponibles = zonaDao.getZonesFromFranjaDia(idEspai, franjaHoraria.getId(), dia);
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
