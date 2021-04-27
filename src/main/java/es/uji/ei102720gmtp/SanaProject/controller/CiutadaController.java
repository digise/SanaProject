package es.uji.ei102720gmtp.SanaProject.controller;

import es.uji.ei102720gmtp.SanaProject.dao.CiutadaDao;
import es.uji.ei102720gmtp.SanaProject.model.Ciutada;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/ciutada")
public class CiutadaController {

    private CiutadaDao ciutadaDao;

    @Autowired
    public void setCiutadaDao(CiutadaDao ciutadaDao){
        this.ciutadaDao = ciutadaDao;
    }

    //Operacions: Crear, llistar, actualitzar, esborrar

    @RequestMapping("/list")
    public String listReserves(Model model){
        model.addAttribute("ciutadans", ciutadaDao.getCiutadans());
        return "ciutada/list";
    }

    @RequestMapping(value = "/add")
    public String addCiutada(Model model){
        model.addAttribute("ciutada", new Ciutada());
        return "ciutada/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("ciutada") Ciutada ciutada, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "ciutada/add";
        ciutadaDao.addCiutada(ciutada);
        return "redirect:list";
    }

    @RequestMapping(value="/update/{nif}", method = RequestMethod.GET)
    public String editCiutada(Model model, @PathVariable String nif){
        model.addAttribute("ciutada", ciutadaDao.getCiutada(nif));
        return "ciutada/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String processUpdateSubmit(@ModelAttribute("ciutada") Ciutada ciutada, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "ciutada/update";
        ciutadaDao.updateReserva(ciutada);
        return "redirect:list";
    }

    @RequestMapping(value = "/delete/{nif}")
    public String processDelete(@PathVariable String nif){
        ciutadaDao.deleteCiutada(nif);
        return "redirect:../list";
    }


}