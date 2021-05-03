package es.uji.ei102720gmtp.SanaProject.controller;

import es.uji.ei102720gmtp.SanaProject.dao.ZonaDao;
import es.uji.ei102720gmtp.SanaProject.model.Zona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("zona")
public class ZonaController {


    private ZonaDao zonaDao;

    @Autowired
    public void setZonaDao(ZonaDao zonaDao){
        this.zonaDao = zonaDao;
    }

    //Operacions: Crear, llistar, actualitzar, esborrar

    @RequestMapping("/list")
    public String listZones(Model model){
        model.addAttribute("zones", zonaDao.getZones());
        return "zona/list";
    }

    @RequestMapping(value = "/add")
    public String addZona(Model model){
        model.addAttribute("templates/zona", new Zona());
        return "zona/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("templates/zona") Zona zona, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "zona/add";
        zonaDao.addZona(zona);
        return "redirect:list";
    }

    @RequestMapping(value="/update/{id}", method = RequestMethod.GET)
    public String editZona(Model model, @PathVariable String id){
        model.addAttribute("templates/zona", zonaDao.getZona(id));
        return "zona/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String processUpdateSubmit(@ModelAttribute("templates/zona") Zona zona, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "templates/zona/update";
        zonaDao.updateZona(zona);
        return "redirect:list";
    }

    @RequestMapping(value = "/delete/{id}")
    public String processDelete(@PathVariable String id){
        zonaDao.deleteZona(id);
        return "redirect:../list";
    }

}
