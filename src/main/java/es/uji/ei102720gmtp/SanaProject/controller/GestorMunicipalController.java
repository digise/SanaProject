package es.uji.ei102720gmtp.SanaProject.controller;

import es.uji.ei102720gmtp.SanaProject.Validation.GestorMunicipalValidator;
import es.uji.ei102720gmtp.SanaProject.dao.GestorMunicipalDao;
import es.uji.ei102720gmtp.SanaProject.dao.MunicipiDao;
import es.uji.ei102720gmtp.SanaProject.model.GestorMunicipal;
import es.uji.ei102720gmtp.SanaProject.model.Municipi;
import es.uji.ei102720gmtp.SanaProject.model.UserDetails;
import es.uji.ei102720gmtp.SanaProject.services.GestorsPerMunicipiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("gestorMunicipal")
public class GestorMunicipalController {
    private GestorMunicipalDao gestorMunicipalDao;
    private MunicipiDao municipiDao;
    private GestorsPerMunicipiService gestorsPerMunicipiService;
    @Autowired
    public void setGestorMunicipalDao(GestorMunicipalDao gestorMunicipalDao){
        this.gestorMunicipalDao = gestorMunicipalDao;
    }

    @Autowired
    public void setMunicipiDao(MunicipiDao municipiDao) {
        this.municipiDao = municipiDao;
    }

    @Autowired
    public void setGestorsPerMunicipiService(GestorsPerMunicipiService gestorsPerMunicipiService){
        this.gestorsPerMunicipiService = gestorsPerMunicipiService;
    }

    //Operacions: Crear, llistar, actualitzar, esborrar

    @RequestMapping("/list")
    public String listGestorsMunicipals(Model model){
        model.addAttribute("gestors", gestorMunicipalDao.getGestorsMunicipals());
        return "gestorMunicipal/list";
    }

    @RequestMapping(value = "/add/{id}")
    public String addGestorMunicipal(Model model, @PathVariable int id){
        model.addAttribute("id", id);
        model.addAttribute("gestorMunicipal", new GestorMunicipal());
        return "gestorMunicipal/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("gestorMunicipal") GestorMunicipal gestorMunicipal, BindingResult bindingResult, Model model){
        GestorMunicipalValidator gestorMunicipalValidator = new GestorMunicipalValidator();

        gestorMunicipalValidator.validate(gestorMunicipal, bindingResult);
        if(bindingResult.hasErrors()) {
            model.addAttribute("id", gestorMunicipal.getIdMunicipi());
            return "gestorMunicipal/add";
        }
        int id = gestorMunicipal.getIdMunicipi();
        gestorMunicipalDao.addGestorMunicipal(gestorMunicipal);
        return "redirect:gestorsPerMunicipi/"+id;
    }

    @RequestMapping(value="/update/{nif}", method = RequestMethod.GET)
    public String editGestorMunicipal(Model model, @PathVariable String nif){
        model.addAttribute("gestorMunicipal", gestorMunicipalDao.getGestorMunicipal(nif));
        model.addAttribute("id", gestorMunicipalDao.getGestorMunicipal(nif).getIdMunicipi());
        return "gestorMunicipal/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String processUpdateSubmit(@ModelAttribute("gestorMunicipal") GestorMunicipal gestorMunicipal, BindingResult bindingResult, Model model){
        GestorMunicipalValidator gestorMunicipalValidator = new GestorMunicipalValidator();
        gestorMunicipalValidator.validate(gestorMunicipal, bindingResult);
        int id = gestorMunicipal.getIdMunicipi();
        if (bindingResult.hasErrors()) {
            model.addAttribute("id", gestorMunicipal.getIdMunicipi());
            return "gestorMunicipal/update";
        }
        gestorMunicipalDao.updateGestorMunicipal(gestorMunicipal);
        return "redirect:gestorsPerMunicipi/"+id;
    }

    @RequestMapping(value = "/delete/{nif}")
    public String processDelete(@PathVariable String nif){
        int id = gestorMunicipalDao.getGestorMunicipal(nif).getIdMunicipi();
        gestorMunicipalDao.deleteGestorMunicipal(gestorMunicipalDao.getGestorMunicipal(nif));
        return "redirect:../gestorsPerMunicipi/"+id;
    }

    @RequestMapping("/indexGestor")
    public String mostrarIndexGestor(Model model, HttpSession session){
        GestorMunicipal gestorMunicipal = (GestorMunicipal) session.getAttribute("gestorMunicipal");
        Municipi municipi = municipiDao.getMunicipi(gestorMunicipal.getIdMunicipi());
        model.addAttribute("municipi", municipi);
        return "gestorMunicipal/indexGestor";
    }

    @RequestMapping("/gestorsPerMunicipi/{id}")
    public String listGestorsMunicipi(Model model, @PathVariable int id){
        model.addAttribute("gestors", gestorsPerMunicipiService.getGestorsPerMunicipi(id));
        Municipi municipi = municipiDao.getMunicipi(id);
        model.addAttribute("municipi", municipi);
        return "gestorMunicipal/gestorsPerMunicipi";
    }
}
