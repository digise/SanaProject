package es.uji.ei102720gmtp.SanaProject.services;

import es.uji.ei102720gmtp.SanaProject.dao.ControlaDao;
import es.uji.ei102720gmtp.SanaProject.dao.EspaiPublicDao;
import es.uji.ei102720gmtp.SanaProject.model.Controla;
import es.uji.ei102720gmtp.SanaProject.model.EspaiPublic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MunicipisPerControladorService implements InterfaceMunicipisPerControladorService {

    @Autowired
    EspaiPublicDao espaiPublicDao;

    @Autowired
    ControlaDao controlaDao;

    @Override
    public List<EspaiPublic> municipisPerControlador(String nifControlador) {
        List<EspaiPublic> res = new ArrayList<>();
        int idEspai;
        for (Controla controla : controlaDao.getListControlaPerControlador(nifControlador)) {
            idEspai = controla.getIdEspai();
            EspaiPublic espaiPublic = espaiPublicDao.getEspaiPublic(idEspai);
            res.add(espaiPublic);
        }
        return res;
    }

}
