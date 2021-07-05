package es.uji.ei102720gmtp.SanaProject.controller;

import es.uji.ei102720gmtp.SanaProject.Validation.PeriodeServeiValidator;
import es.uji.ei102720gmtp.SanaProject.Validation.ServeiEstacionalValidator;
import es.uji.ei102720gmtp.SanaProject.Validation.ServeiPermanentValidator;
import es.uji.ei102720gmtp.SanaProject.dao.*;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    private PeriodeServeiEspaiDao periodeServeiEspaiDao;

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

    @Autowired
    public void setPeriodeServeiEspaiDao(PeriodeServeiEspaiDao periodeServeiEspaiDao) {
        this.periodeServeiEspaiDao = periodeServeiEspaiDao;
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
        for (ServeiPermanent serveiPermanent1: serveiPermanentDao.getServeisPermanents())
            if (serveiPermanent1.getNom().equals(serveiPermanent.getNom()))
                bindingResult.rejectValue("nom", "repetit", "Aquest servei ja eisteix");
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
    public String deleteServeiPermanent(Model model, @PathVariable String nom, RedirectAttributes redirectAttributes){
        if (serveiService.isPermanentServeiUsed(nom)){
            String msg = "No es pot borrar el servei " + nom + " perque s'esta usant en alguna area";
            redirectAttributes.addFlashAttribute("alert", msg);
            return "redirect:../listServeiPermanent";
        }
        serveiPermanentDao.deleteServeiPermanent(nom);
        String msg = "S'ha borrat el servei: " + nom;
        redirectAttributes.addFlashAttribute("alert", msg);
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
        for (ServeiEstacional serveiEstacional1: serveiEstacionalDao.getServeisEstacionals())
            if (serveiEstacional1.getNom().equals(serveiEstacional.getNom()))
                bindingResult.rejectValue("nom", "repetit", "Aquest servei ja eisteix");
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
    public String deleteServeiEstacional(Model model, @PathVariable String nom, RedirectAttributes redirectAttributes){
        if (serveiService.isEstacionalServeiUsed(nom)){
            String msg = "No es pot borrar el servei " + nom + " perque s'esta usant en alguna area";
            redirectAttributes.addFlashAttribute("alert", msg);
            return "redirect:../listServeiEstacional";
        }

        serveiEstacionalDao.deleteServeiEstacional(nom);
        String msg = "S'ha borrat el servei: " + nom;
        redirectAttributes.addFlashAttribute("alert", msg);
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

    // Serveis Permanents

    @RequestMapping(value = "/addServeiPermanentGestor/{id}")
    public String addServeiPermanentGestor(Model model, @PathVariable int id) {
        EspaiPublic espai = espaiPublicDao.getEspaiPublic(id);

        List<ServeiPermanent> instalats = serveiPermanentDao.getServeisPermanentsFromEspai(id);
        List<ServeiPermanent> list = serveiService.getServeisPermanentsRestants(instalats);

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

        model.addAttribute("alertPermanent", "S'ha borrat el servei " + nom);
        return "servei/listServeisEspai";
    }

    // Serveis estacionals

    @RequestMapping(value = "/addServeiEstacionalGestor/{id}")
    public String addServeiEstacionalGestor(Model model, @PathVariable int id) {
        EspaiPublic espai = espaiPublicDao.getEspaiPublic(id);

        List<ServeiEstacional> instalats = serveiEstacionalDao.getServeisEstacionalsFromEspai(id);
        List<ServeiEstacional> list = serveiService.getServeisEstacionalsRestants(instalats);

        model.addAttribute("espai", espai);
        model.addAttribute("serveis", list);

        ServeiEstacionalComplet serveiEstacionalComplet = new ServeiEstacionalComplet();
        serveiEstacionalComplet.setIdEspai(id);
        model.addAttribute("serveiEstacionalComplet", serveiEstacionalComplet);

        return "servei/addServeiEstacionalGestor";
    }

    @RequestMapping(value = "/addServeiEstacionalGestor", method= RequestMethod.POST)
    public String addServeiEstacionalGestorPost(@ModelAttribute("serveiEstacionalComplet") ServeiEstacionalComplet serveiEstacionalComplet, BindingResult bindingResult, Model model) {
        EspaiPublic espai = espaiPublicDao.getEspaiPublic(serveiEstacionalComplet.getIdEspai());

        PeriodeServeiEspai periodeServeiEspai = new PeriodeServeiEspai();
        periodeServeiEspai.setNomServei(serveiEstacionalComplet.getNom());
        periodeServeiEspai.setIdEspai(serveiEstacionalComplet.getIdEspai());
        periodeServeiEspai.setHoraInici(serveiEstacionalComplet.getHoraInici());
        periodeServeiEspai.setHoraFinal(serveiEstacionalComplet.getHoraFinal());
        periodeServeiEspai.setDataInici(serveiEstacionalComplet.getDataInici());
        periodeServeiEspai.setDataFinal(serveiEstacionalComplet.getDataFinal());

        PeriodeServeiValidator periodeServeiValidator = new PeriodeServeiValidator();
        periodeServeiValidator.validate(periodeServeiEspai, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("espai", espai);
            List<ServeiEstacional> instalats = serveiEstacionalDao.getServeisEstacionalsFromEspai(espai.getId());
            List<ServeiEstacional> list = serveiService.getServeisEstacionalsRestants(instalats);
            model.addAttribute("serveis", list);
            return "servei/addServeiEstacionalGestor";
        }

        periodeServeiEspaiDao.addPeriodeServeiEspai(periodeServeiEspai);

        List<ServeiPermanentComplet> serveisPermanentsInstalats = serveiService.getServeiPermanentInstalats(espai.getId());
        List<ServeiEstacionalComplet> serveiEstacionalInstalats = serveiService.getServeisEstacionalsInstalats(espai.getId());

        model.addAttribute("serveisPermanents", serveisPermanentsInstalats);
        model.addAttribute("serveisEstacionals", serveiEstacionalInstalats);

        model.addAttribute("espai", espai);

        return "servei/listServeisEspai";
    }

    @RequestMapping(value = "/deleteServeiEstacionalGestor/{id}/{nom}")
    public String deleteServeiEstacionalGestor(Model model, @PathVariable int id, @PathVariable String nom){
        periodeServeiEspaiDao.deletePeriodeServeiEspai(id, nom);

        List<ServeiPermanentComplet> serveisPermanentsInstalats = serveiService.getServeiPermanentInstalats(id);
        List<ServeiEstacionalComplet> serveiEstacionalInstalats = serveiService.getServeisEstacionalsInstalats(id);

        model.addAttribute("serveisPermanents", serveisPermanentsInstalats);
        model.addAttribute("serveisEstacionals", serveiEstacionalInstalats);

        model.addAttribute("espai", espaiPublicDao.getEspaiPublic(id));

        model.addAttribute("alertEstacional", "S'ha borrat el servei " + nom);
        return "servei/listServeisEspai";
    }



}
