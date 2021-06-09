package es.uji.ei102720gmtp.SanaProject.services;

import es.uji.ei102720gmtp.SanaProject.dao.*;
import es.uji.ei102720gmtp.SanaProject.model.Controla;
import es.uji.ei102720gmtp.SanaProject.model.Controlador;
import es.uji.ei102720gmtp.SanaProject.model.ControladorAmbEspaiPublic;
import es.uji.ei102720gmtp.SanaProject.model.EspaiPublic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ControladorsPerMunicipiService implements InterfaceControladorsPerMunicipiService {

    @Autowired
    EspaiPublicDao espaiPublicDao;

    @Autowired
    ControlaDao controlaDao;

    @Autowired
    ControladorDao controladorDao;

    @Override
    public List<ControladorAmbEspaiPublic> controladorsPerMunicipi(int idMunicipi) {
        List<ControladorAmbEspaiPublic> res = new ArrayList<>();
        String nomEspai;
        ControladorAmbEspaiPublic controladorAmbEspaiPublic;
        Controlador controlador;
        for (EspaiPublic espaiPublic : espaiPublicDao.getEspaisPublics()){
            if (espaiPublic.getIdMunicipi() == idMunicipi){
                nomEspai = espaiPublic.getNom();
               for (Controla controla : controlaDao.getlistControlaPerEspai(espaiPublic.getId())){
                   controlador = controladorDao.getControlador(controla.getNifControlador());
                   controladorAmbEspaiPublic = new ControladorAmbEspaiPublic(controlador, nomEspai);
                   res.add(controladorAmbEspaiPublic);
               }
            }
        }
        System.out.println(res.toString());
        return res;
    }
}
