package es.uji.ei102720gmtp.SanaProject.controller;

import es.uji.ei102720gmtp.SanaProject.dao.ServeiEstacionalDao;
import es.uji.ei102720gmtp.SanaProject.dao.ServeiPermanentDao;
import es.uji.ei102720gmtp.SanaProject.model.EspaiPublic;
import es.uji.ei102720gmtp.SanaProject.model.Municipi;
import es.uji.ei102720gmtp.SanaProject.model.ServeiPermanent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/servei")
public class ServeiController {

    private ServeiPermanentDao serveiPermanentDao;
    private ServeiEstacionalDao serveiEstacionalDao;

    @Autowired
    public void setServeiPermanentDao(ServeiPermanentDao serveiPermanentDao) {
        this.serveiPermanentDao = serveiPermanentDao;
    }

    @Autowired
    public void setServeiEstacionalDao(ServeiEstacionalDao serveiEstacionalDao) {
        this.serveiEstacionalDao = serveiEstacionalDao;
    }

    @RequestMapping("/seleccionarTipusServei")
    public String seleccionarTipusServei(Model model){
        return "servei/seleccionarTipusServei";
    }

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
        if (bindingResult.hasErrors())
            return "servei/updateServeiPermanent";
        serveiPermanentDao.updateServeiPermanent(serveiPermanent);
        return "redirect:listServeiPermanent";
    }







    @RequestMapping("/listServeiEstacional")
    public String listServeiEstacional(Model model){
        return "servei/listServeiEstacional";
    }

}
