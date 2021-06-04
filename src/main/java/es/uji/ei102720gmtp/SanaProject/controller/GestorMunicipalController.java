package es.uji.ei102720gmtp.SanaProject.controller;

import es.uji.ei102720gmtp.SanaProject.dao.GestorMunicipalDao;
import es.uji.ei102720gmtp.SanaProject.dao.MunicipiDao;
import es.uji.ei102720gmtp.SanaProject.model.GestorMunicipal;
import es.uji.ei102720gmtp.SanaProject.model.Municipi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("gestorMunicipal")
public class GestorMunicipalController {
    private GestorMunicipalDao gestorMunicipalDao;
    private MunicipiDao municipiDao;
    @Autowired
    public void setGestorMunicipalDao(GestorMunicipalDao gestorMunicipalDao){
        this.gestorMunicipalDao = gestorMunicipalDao;
    }

    @Autowired
    public void setMunicipiDao(MunicipiDao municipiDao) {
        this.municipiDao = municipiDao;
    }


    //Operacions: Crear, llistar, actualitzar, esborrar

    @RequestMapping("/list")
    public String listGestorsMunicipals(Model model){
        model.addAttribute("gestors", gestorMunicipalDao.getGestorsMunicipals());
        return "gestorMunicipal/list";
    }

    @RequestMapping(value = "/add")
    public String addGestorMunicipal(Model model){
        model.addAttribute("gestorMunicipal", new GestorMunicipal());
        return "gestorMunicipal/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("gestorMunicipal") GestorMunicipal gestorMunicipal, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "gestorMunicipal/add";
        gestorMunicipalDao.addGestorMunicipal(gestorMunicipal);
        return "redirect:list";
    }

    @RequestMapping(value="/update/{nif}", method = RequestMethod.GET)
    public String editGestorMunicipal(Model model, @PathVariable String nif){
        model.addAttribute("gestorMunicipal", gestorMunicipalDao.getGestorMunicipal(nif));
        return "gestorMunicipal/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String processUpdateSubmit(@ModelAttribute("gestorMunicipal") GestorMunicipal gestorMunicipal, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "gestorMunicipal/update";
        gestorMunicipalDao.updateGestorMunicipal(gestorMunicipal);
        return "redirect:list";
    }

    @RequestMapping(value = "/delete/{nif}")
    public String processDelete(@PathVariable String nif){
        gestorMunicipalDao.deleteGestorMunicipal(gestorMunicipalDao.getGestorMunicipal(nif));
        return "redirect:../list";
    }

    @RequestMapping(value= "/indexGestor")
    public String mostrarIndexGestor(@ModelAttribute("gestorMunicipal") GestorMunicipal gestorMunicipal, Model model){
        int idMunicipi = gestorMunicipal.getIdMunicipi();
        String nomMunicipi = municipiDao.getMunicipi(idMunicipi).getNom();
        model.addAttribute("nomMunicipi", nomMunicipi);
        return "gestorMunicipal/indexGestor";
    }
}
