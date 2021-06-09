package es.uji.ei102720gmtp.SanaProject.controller;


import es.uji.ei102720gmtp.SanaProject.dao.ControladorDao;
import es.uji.ei102720gmtp.SanaProject.dao.MunicipiDao;
import es.uji.ei102720gmtp.SanaProject.model.Controlador;
import es.uji.ei102720gmtp.SanaProject.model.GestorMunicipal;
import es.uji.ei102720gmtp.SanaProject.model.Municipi;
import es.uji.ei102720gmtp.SanaProject.services.ControladorsPerMunicipiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/controlador")
public class ControladorController  {
    private ControladorDao controladorDao;
    private ControladorsPerMunicipiService controladorsPerMunicipiService;
    private MunicipiDao municipiDao;
    @Autowired
    public void setControladorDao(ControladorDao controladorDao){
        this.controladorDao = controladorDao;
    }

    @Autowired
    public void setControladorsPerMunicipiService(ControladorsPerMunicipiService controladorsPerMunicipiService) {
        this.controladorsPerMunicipiService = controladorsPerMunicipiService;
    }

    @Autowired
    public void setMunicipiDao(MunicipiDao municipiDao){
        this.municipiDao = municipiDao;
    }

    //Operacions: Crear, llistar, actualitzar, esborrar

    @RequestMapping("/list")
    public String listControllers(Model model){
        model.addAttribute("controladors", controladorDao.getlistControladors());
        return "controlador/list";
    }
    @RequestMapping("/controladorsPerMunicipi")
    public String listControladorsMunicipi(Model model, HttpSession session){
        GestorMunicipal gestorMunicipal = (GestorMunicipal) session.getAttribute("gestorMunicipal");
        int idMunicipi = gestorMunicipal.getIdMunicipi();
        model.addAttribute("controladors", controladorsPerMunicipiService.controladorsPerMunicipi(idMunicipi));
        Municipi municipi = municipiDao.getMunicipi(idMunicipi);
        model.addAttribute("municipi", municipi);
        return "controlador/controladorsPerMunicipi";
    }

    @RequestMapping(value = "/add")
    public String addControlador(Model model){
        model.addAttribute("controlador", new Controlador());
        return "controlador/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("controlador") Controlador controlador, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "controlador/add";
        controladorDao.addControlador(controlador);
        return "redirect:list";
    }

    @RequestMapping(value="/update/{nifControlador}", method = RequestMethod.GET)
    public String editControlador(Model model, @PathVariable String nifControlador){
        model.addAttribute("controlador", controladorDao.getControlador(nifControlador));
        return "controlador/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String processUpdateSubmit(@ModelAttribute("controlador") Controlador controlador, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "controlador/update";
        controladorDao.updateControlador(controlador);
        return "redirect:list";
    }

    @RequestMapping(value = "/delete/{nifControlador}")
    public String processDelete(@PathVariable String nifControlador){
        controladorDao.deleteControlador(controladorDao.getControlador(nifControlador));
        return "redirect:../list";
    }
}
