package es.uji.ei102720gmtp.SanaProject.controller;


import es.uji.ei102720gmtp.SanaProject.Validation.ControladorsAmbEspaiPublicValidator;
import es.uji.ei102720gmtp.SanaProject.dao.ControlaDao;
import es.uji.ei102720gmtp.SanaProject.dao.ControladorDao;
import es.uji.ei102720gmtp.SanaProject.dao.EspaiPublicDao;
import es.uji.ei102720gmtp.SanaProject.dao.MunicipiDao;
import es.uji.ei102720gmtp.SanaProject.model.Controlador;
import es.uji.ei102720gmtp.SanaProject.model.ControladorAmbEspaiPublic;
import es.uji.ei102720gmtp.SanaProject.model.GestorMunicipal;
import es.uji.ei102720gmtp.SanaProject.model.Municipi;
import es.uji.ei102720gmtp.SanaProject.services.InterfaceControladorsPerMunicipiService;
import es.uji.ei102720gmtp.SanaProject.model.*;
import es.uji.ei102720gmtp.SanaProject.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/controlador")
public class ControladorController  {
    private ControladorDao controladorDao;
    private InterfaceControladorsPerMunicipiService controladorsPerMunicipiService;
    private MunicipiDao municipiDao;
    private EspaiPublicDao espaiPublicDao;
    private ControlaDao controlaDao;
    private MunicipisPerControladorService municipisPerControladorService;


    @Autowired
    public void setControladorDao(ControladorDao controladorDao){
        this.controladorDao = controladorDao;
    }

    @Autowired
    public void setControladorsPerMunicipiService(InterfaceControladorsPerMunicipiService controladorsPerMunicipiService) {
        this.controladorsPerMunicipiService = controladorsPerMunicipiService;
    }

    @Autowired
    public void setMunicipiDao(MunicipiDao municipiDao){
        this.municipiDao = municipiDao;
    }

    @Autowired
    public void setEspaiPublicDao(EspaiPublicDao espaiPublicDao) {
        this.espaiPublicDao = espaiPublicDao;
    }

    @Autowired
    public void setMunicipisPerControladorService(MunicipisPerControladorService municipisPerControladorService){
        this.municipisPerControladorService = municipisPerControladorService;
    }

    @Autowired
    public void setControlaDao(ControlaDao controlaDao){
        this.controlaDao = controlaDao;
    }

    //Operacions: Crear, llistar, actualitzar, esborrar

    @RequestMapping("/list")
    public String listControllers(Model model){
        model.addAttribute("controladors", controladorDao.getlistControladors());
        return "controlador/list";
    }
    @RequestMapping("/controladorsPerMunicipi")
    public String listControladorsMunicipi(Model model, HttpSession session){

        for (Controla controla : controlaDao.getlistControla()){
            if (controla.getDataFinal().isBefore(LocalDate.now()))
                controlaDao.getControla(controla.getNifControlador(), controla.getIdEspai(), controla.getDataInici());
        }
        GestorMunicipal gestorMunicipal = (GestorMunicipal) session.getAttribute("gestorMunicipal");
        int idMunicipi = gestorMunicipal.getIdMunicipi();
        model.addAttribute("controladors", controladorsPerMunicipiService.controladorsPerMunicipi(idMunicipi));
        Municipi municipi = municipiDao.getMunicipi(idMunicipi);
        model.addAttribute("municipi", municipi);
        return "controlador/controladorsPerMunicipi";
    }


    @RequestMapping(value = "/add")
    public String addControlador(Model model, HttpSession session){
        model.addAttribute("controlador", new ControladorAmbEspaiPublic(new Controlador(), null));
        GestorMunicipal gestorMunicipal = (GestorMunicipal) session.getAttribute("gestorMunicipal");
        int idMunicipi = gestorMunicipal.getIdMunicipi();
        model.addAttribute("espaisPerProvincia", controladorsPerMunicipiService.getEspaisPublicsPerMunicipi(idMunicipi));
        return "controlador/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("controlador") ControladorAmbEspaiPublic controladorAmbEspaiPublic, BindingResult bindingResult){
        ControladorsAmbEspaiPublicValidator controladorsAmbEspaiPublicValidator = new ControladorsAmbEspaiPublicValidator();
        controladorsAmbEspaiPublicValidator.validate(controladorAmbEspaiPublic, bindingResult);
        if(bindingResult.hasErrors())
            return "controlador/add";
        System.out.println(controladorAmbEspaiPublic.toString());
        controladorDao.addControlador(controladorAmbEspaiPublic.getControlador());
        return "redirect:controladorsPerMunicipi";
    }

    @RequestMapping(value="/update/{nifControlador}", method = RequestMethod.GET)
    public String editControlador(Model model, HttpSession session, @PathVariable String nifControlador) {
        GestorMunicipal gestorMunicipal = (GestorMunicipal)  session.getAttribute("gestorMunicipal");
        int idMunicipi = municipiDao.getMunicipi(gestorMunicipal.getIdMunicipi()).getId();
        model.addAttribute("controlador", controladorsPerMunicipiService.getControladorService(nifControlador, idMunicipi));
        return "controlador/update";
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String processUpdateSubmit(@ModelAttribute("controlador") ControladorAmbEspaiPublic controladorAmbEspaiPublic, BindingResult bindingResult, HttpSession session, RedirectAttributes redirectAttributes){
        ControladorsAmbEspaiPublicValidator controladorsAmbEspaiPublicValidator = new ControladorsAmbEspaiPublicValidator();
        controladorsAmbEspaiPublicValidator.validate(controladorAmbEspaiPublic, bindingResult);
        if (bindingResult.hasErrors())
            return "controlador/update";

        controladorDao.updateControlador(controladorAmbEspaiPublic.getControlador());
        GestorMunicipal gestorMunicipal = (GestorMunicipal) session.getAttribute("gestorMunicipal");
        int idMunicipi = gestorMunicipal.getIdMunicipi();
        espaiPublicDao.updateNomEspaiPublic(controladorAmbEspaiPublic.getNomEspaiPublic(), controladorsPerMunicipiService.getEspaiPublicDelControlador(idMunicipi));
        return "redirect:controladorsPerMunicipi";
    }

    @RequestMapping(value = "/delete/{nifControlador}")
    public String processDelete(@PathVariable String nifControlador, HttpSession session, RedirectAttributes redirectAttributes){
        GestorMunicipal gestorMunicipal = (GestorMunicipal) session.getAttribute("gestorMunicipal");
        int idMunicipi = gestorMunicipal.getIdMunicipi();
        int senseBorrar = controladorDao.getlistControladors().size();
        controladorDao.deleteControlador(controladorsPerMunicipiService.getControladorService(nifControlador, idMunicipi));
        int borrat = controladorDao.getlistControladors().size();
        String msg;
        if (senseBorrar != borrat){
            msg = String.format("El controlador amb nom " + controladorDao.getControlador(nifControlador).getNom() + controladorDao.getControlador(nifControlador).getCognoms() + "se ha borrat correctament");
            redirectAttributes.addFlashAttribute("alertBona", msg);
        }
        else{
            msg = String.format("El controlador amb nom " + controladorDao.getControlador(nifControlador).getNom() + " " + controladorDao.getControlador(nifControlador).getCognoms() + " no se ha borrat ja que está controlant un espai ");
            redirectAttributes.addFlashAttribute("alertMala", msg);
        }


        return "redirect:../controladorsPerMunicipi";
    }

    @RequestMapping("/indexControlador")
    public String mostrarIndexControlador(Model model, HttpSession session){
        Controlador controlador = (Controlador) session.getAttribute("controlador");
        model.addAttribute("controlador", controlador);
        return "controlador/indexControlador";
    }

}
