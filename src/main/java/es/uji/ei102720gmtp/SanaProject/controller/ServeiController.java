package es.uji.ei102720gmtp.SanaProject.controller;

import es.uji.ei102720gmtp.SanaProject.Validation.EspaiPublicValidator;
import es.uji.ei102720gmtp.SanaProject.Validation.ServeiEstacionalValidator;
import es.uji.ei102720gmtp.SanaProject.Validation.ServeiPermanentValidator;
import es.uji.ei102720gmtp.SanaProject.dao.EspaiPublicDao;
import es.uji.ei102720gmtp.SanaProject.dao.ServeiEstacionalDao;
import es.uji.ei102720gmtp.SanaProject.dao.ServeiInstalatEspaiDao;
import es.uji.ei102720gmtp.SanaProject.dao.ServeiPermanentDao;
import es.uji.ei102720gmtp.SanaProject.model.*;
import es.uji.ei102720gmtp.SanaProject.services.InterfaceServeiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/servei")
public class ServeiController {

    private ServeiPermanentDao serveiPermanentDao;
    private ServeiEstacionalDao serveiEstacionalDao;
    private InterfaceServeiService serveiService;
    private EspaiPublicDao espaiPublicDao;
    private ServeiInstalatEspaiDao serveiInstalatEspaiDao;

    @Autowired
    public void setServeiInstalatEspaiDao(ServeiInstalatEspaiDao serveiInstalatEspaiDao) {
        this.serveiInstalatEspaiDao = serveiInstalatEspaiDao;
    }

    @Autowired
    public void setServeiPermanentDao(ServeiPermanentDao serveiPermanentDao) {
        this.serveiPermanentDao = serveiPermanentDao;
    }

    @Autowired
    public void setServeiEstacionalDao(ServeiEstacionalDao serveiEstacionalDao) {
        this.serveiEstacionalDao = serveiEstacionalDao;
    }

    @Autowired
    public void setServeiService(InterfaceServeiService serveiService) {
        this.serveiService = serveiService;
    }

    @Autowired
    public void setEspaiPublicDao(EspaiPublicDao espaiPublicDao) {
        this.espaiPublicDao = espaiPublicDao;
    }

    @RequestMapping("/seleccionarTipusServei")
    public String seleccionarTipusServei(Model model){
        return "servei/seleccionarTipusServei";
    }


    // Servei permanent

    @RequestMapping("/listServeiPermanent")
    public String listServeiPermanent(Model model){
        model.addAttribute("serveis", serveiPermanentDao.getServeisPermanents());
        return "servei/listServeiPermanent";
    }

    @RequestMapping(value = "/addServeiPermanent", method = RequestMethod.GET)
    public String addServeiPermanent(Model model){
        model.addAttribute("serveiPermanent", new ServeiPermanent());
        return "servei/addServeiPermanent";
    }

    @RequestMapping(value="/addServeiPermanent", method= RequestMethod.POST)
    public String processAddServeiPermanent(@ModelAttribute("serveiPermanent") ServeiPermanent serveiPermanent, BindingResult bindingResult){
        ServeiPermanentValidator serveiPermanentValidator = new ServeiPermanentValidator();
        serveiPermanentValidator.validate(serveiPermanent, bindingResult);
        if(bindingResult.hasErrors())
            return "servei/addServeiPermanent";
        serveiPermanentDao.addServeiPermanent(serveiPermanent);
        return "redirect:listServeiPermanent";
    }

    @RequestMapping(value = "/updateServeiPermanent/{nom}", method = RequestMethod.GET)
    public String editServeiPermanent(Model model, @PathVariable String nom){
        model.addAttribute("servei", serveiPermanentDao.getServeiPermanent(nom));
        return "servei/updateServeiPermanent";
    }

    @RequestMapping(value = "/updateServeiPermanent", method = RequestMethod.POST)
    public String processUpdateServeiPermanent(@ModelAttribute("servei") ServeiPermanent serveiPermanent, BindingResult bindingResult){
        ServeiPermanentValidator serveiPermanentValidator = new ServeiPermanentValidator();
        serveiPermanentValidator.validate(serveiPermanent, bindingResult);
        if (bindingResult.hasErrors())
            return "servei/updateServeiPermanent";
        serveiPermanentDao.updateServeiPermanent(serveiPermanent);
        return "redirect:listServeiPermanent";
    }

    @RequestMapping(value = "/deleteServeiPermanent/{nom}")
    public String deleteServeiPermanent(Model model, @PathVariable String nom){
        serveiPermanentDao.deleteServeiPermanent(nom);
        return "redirect:../listServeiPermanent";
    }

    // Servei estacional

    @RequestMapping("/listServeiEstacional")
    public String listServeiEstacional(Model model){
        model.addAttribute("serveis", serveiEstacionalDao.getServeisEstacionals());
        return "servei/listServeiEstacional";
    }


    @RequestMapping(value = "/addServeiEstacional", method = RequestMethod.GET)
    public String addServeiEstacional(Model model){
        model.addAttribute("serveiEstacional", new ServeiEstacional());
        return "servei/addServeiEstacional";
    }

    @RequestMapping(value="/addServeiEstacional", method= RequestMethod.POST)
    public String processAddServeiEstacional(@ModelAttribute("serveiEstacional") ServeiEstacional serveiEstacional, BindingResult bindingResult){
        ServeiEstacionalValidator serveiEstacionalValidator = new ServeiEstacionalValidator();
        serveiEstacionalValidator.validate(serveiEstacional, bindingResult);
        if(bindingResult.hasErrors())
            return "servei/addServeiEstacional";
        serveiEstacionalDao.addServeiEstacional(serveiEstacional);
        return "redirect:listServeiEstacional";
    }

    @RequestMapping(value = "/updateServeiEstacional/{nom}", method = RequestMethod.GET)
    public String editServeiEstacional(Model model, @PathVariable String nom){
        model.addAttribute("servei", serveiEstacionalDao.getServeiEstacional(nom));
        return "servei/updateServeiEstacional";
    }

    @RequestMapping(value = "/updateServeiEstacional", method = RequestMethod.POST)
    public String processUpdateServeiEstacional(@ModelAttribute("servei") ServeiEstacional serveiEstacional, BindingResult bindingResult){
        ServeiEstacionalValidator serveiEstacionalValidator = new ServeiEstacionalValidator();
        serveiEstacionalValidator.validate(serveiEstacional, bindingResult);
        if (bindingResult.hasErrors())
            return "servei/updateServeiEstacional";
        serveiEstacionalDao.updateServeiEstacional(serveiEstacional);
        return "redirect:listServeiEstacional";
    }

    @RequestMapping(value = "/deleteServeiEstacional/{nom}")
    public String deleteServeiEstacional(Model model, @PathVariable String nom){
        serveiEstacionalDao.deleteServeiEstacional(nom);
        return "redirect:../listServeiEstacional";
    }

    // Servei gestor

    @RequestMapping(value = "/listServeisEspai/{id}")
    public String listServeiEspai(Model model, @PathVariable int id) {
        List<ServeiPermanentComplet> serveisPermanentsInstalats = serveiService.getServeiPermanentInstalats(id);
        List<ServeiEstacionalComplet> serveiEstacionalInstalats = serveiService.getServeisEstacionalsInstalats(id);

        model.addAttribute("serveisPermanents", serveisPermanentsInstalats);
        model.addAttribute("serveisEstacionals", serveiEstacionalInstalats);

        model.addAttribute("espai", espaiPublicDao.getEspaiPublic(id));

        return "servei/listServeisEspai";
    }

    @RequestMapping(value = "/addServeiPermanentGestor/{id}")
    public String addServeiPermanentGestor(Model model, @PathVariable int id) {
        EspaiPublic espai = espaiPublicDao.getEspaiPublic(id);

        List<ServeiPermanent> instalats = serveiPermanentDao.getServeisPermanentsFromEspai(id);
        List<ServeiPermanent> list = serveiService.getServeisRestants(instalats);

        model.addAttribute("espai", espai);
        model.addAttribute("serveis", list);

        return "servei/addServeiPermanentGestor";
    }

    @RequestMapping(value = "/addServeiPermanentGestor/{id}/{nom}")
    public String addServeiPermanentGestorPost(Model model, @PathVariable int id, @PathVariable String nom) {
        EspaiPublic espai = espaiPublicDao.getEspaiPublic(id);

        ServeiPermanent serveiPermanent = serveiPermanentDao.getServeiPermanent(nom);
        serveiInstalatEspaiDao.addServeiInstalatEspai(id, nom, LocalDate.now());

        List<ServeiPermanentComplet> serveisPermanentsInstalats = serveiService.getServeiPermanentInstalats(id);
        List<ServeiEstacionalComplet> serveiEstacionalInstalats = serveiService.getServeisEstacionalsInstalats(id);

        model.addAttribute("serveisPermanents", serveisPermanentsInstalats);
        model.addAttribute("serveisEstacionals", serveiEstacionalInstalats);

        model.addAttribute("espai", espai);

        return "servei/listServeisEspai";
    }

    @RequestMapping(value = "/deleteServeiPermanentGestor/{id}/{nom}")
    public String deleteServeiPermanentGestor(Model model, @PathVariable int id, @PathVariable String nom){
        serveiInstalatEspaiDao.deleteServeiInstalatEspai(id, nom);

        List<ServeiPermanentComplet> serveisPermanentsInstalats = serveiService.getServeiPermanentInstalats(id);
        List<ServeiEstacionalComplet> serveiEstacionalInstalats = serveiService.getServeisEstacionalsInstalats(id);

        model.addAttribute("serveisPermanents", serveisPermanentsInstalats);
        model.addAttribute("serveisEstacionals", serveiEstacionalInstalats);

        model.addAttribute("espai", espaiPublicDao.getEspaiPublic(id));
        return "servei/listServeisEspai";
    }



}
