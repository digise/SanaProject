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
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/controla")
public class ControlaController {
    private ControlaDao controlaDao;

    @Autowired
    public void setControlaDao(ControlaDao controlaDao){
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
    public String processAddSubmit(@ModelAttribute("controla") Controla controla, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "controla/add";
        controlaDao.addControla(controla);
        return "redirect:list";
    }

    @RequestMapping(value="/update/{nifControlador}/{idEspai}/{dataInici}", method = RequestMethod.GET)
    public String editControla(Model model, @PathVariable String nifControlador, @PathVariable int idEspai, @PathVariable String dataInici){
        LocalDate data = LocalDate.parse(dataInici, DateTimeFormatter.ISO_DATE);
        model.addAttribute("controla", controlaDao.getControla(nifControlador, idEspai,data));
        return "controla/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String processUpdateSubmit(@ModelAttribute("controla") Controla controla, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "controla/update";
        controlaDao.updateControla(controla);
        return "redirect:list";
    }

    @RequestMapping(value = "/delete/{nifControlador}/{idEspai}/{dataInici}")
    public String processDelete( @PathVariable String nifControlador, @PathVariable int idEspai, @PathVariable String dataInici){
        LocalDate data = LocalDate.parse(dataInici, DateTimeFormatter.ISO_DATE);
        controlaDao.deleteControla(nifControlador,idEspai,data);
        return "redirect:../../../list";
    }
}

