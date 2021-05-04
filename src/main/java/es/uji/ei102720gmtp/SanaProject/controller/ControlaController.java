package es.uji.ei102720gmtp.SanaProject.controller;



import es.uji.ei102720gmtp.SanaProject.dao.ControlaDao;
import es.uji.ei102720gmtp.SanaProject.model.Controla;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;

@Controller
@RequestMapping("/controla")
public class ControlaController {
    private ControlaDao controlaDao;

    @Autowired
    public void setGestorMunicipalDao(ControlaDao controlaDao){
        this.controlaDao = controlaDao;
    }

    //Operacions: Crear, llistar, actualitzar, esborrar

    @RequestMapping("/list")
    public String listControla(Model model){
        model.addAttribute("listControla", controlaDao.getlistControla());
        return "controla/list";
    }

    @RequestMapping(value = "/add")
    public String addControla(Model model){
        model.addAttribute("controla", new Controla());
        return "controla/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("gestorMunicipal") Controla controla, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "controla/add";
        controlaDao.addControla(controla);
        return "redirect:list";
    }

    @RequestMapping(value="/update/{nifControlaor}/{idEspai}/{dataInici}", method = RequestMethod.GET)
    public String editControla(Model model, @PathVariable String nifControlador, @PathVariable String idEspai, @PathVariable LocalDate dataInici){
        model.addAttribute("controla", controlaDao.getControla(nifControlador,idEspai,dataInici));
        return "controla/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String processUpdateSubmit(@ModelAttribute("controla") Controla controla, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "controla/update";
        controlaDao.updateControla(controla);
        return "redirect:list";
    }

    @RequestMapping(value = "/delete/{nifControlaor}/{idEspai}/{dataInici}")
    public String processDelete( @PathVariable String nifControlador, @PathVariable String idEspai, @PathVariable LocalDate dataInici){
        controlaDao.deleteControla(nifControlador,idEspai,dataInici);
        return "redirect:../list";
    }
}

