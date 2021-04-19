package es.uji.ei102720gmtp.SanaProject.controller;

import es.uji.ei102720gmtp.SanaProject.dao.GestorMunicipalDao;
import es.uji.ei102720gmtp.SanaProject.model.GestorMunicipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/gestorMunicipal")
public class GestorMunicipalController {
    private GestorMunicipalDao gestorMunicipalDao;

    @Autowired
    public void setGestorMunicipalDao(GestorMunicipalDao gestorMunicipalDao){
        this.gestorMunicipalDao = gestorMunicipalDao;
    }

    //Operacions: Crear, llistar, actualitzar, esborrar

    @RequestMapping("/list")
    public String listGestorsMunicipals(Model model){
        model.addAttribute("gestors", gestorMunicipalDao.getGestors());
        return "gestorMunicipal/list";
    }

    @RequestMapping("/add")
    public String addGestorMunicipal(Model model){
        model.addAttribute("gestor", new GestorMunicipal());
        return "gestorMunicipal/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("gestor") GestorMunicipal gestorMunicipal, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "nadador/add";
        gestorMunicipalDao.addGestorMunicipal(gestorMunicipal);
        return "redirect:list";
    }

    @RequestMapping(value="/update/{nif}", method = RequestMethod.GET)
    public String editGestorMunicipal(Model model, @PathVariable String nif){
        model.addAttribute("gestor", gestorMunicipalDao.getGestor(nif));
        return "gestorMunicipal/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String processUpdateSubmit(@ModelAttribute("gestor") GestorMunicipal gestorMunicipal, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "gestorMunicipal/update";
        gestorMunicipalDao.updateGestor(gestorMunicipal);
        return "redirect:list";
    }

    @RequestMapping(value = "/delete/{nif}")
    public String processDelete(@PathVariable String nif){
        gestorMunicipalDao.deleteGestor(nif);
        return "redirect:../list";
    }
}
