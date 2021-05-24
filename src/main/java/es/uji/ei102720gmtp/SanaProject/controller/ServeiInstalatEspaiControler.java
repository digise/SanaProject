package es.uji.ei102720gmtp.SanaProject.controller;

import es.uji.ei102720gmtp.SanaProject.dao.ServeiInstalatEspaiDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/serveiInstalatEspai")
public class ServeiInstalatEspaiControler {
    private ServeiInstalatEspaiDao serveiInstalatEspaiDao;


}
