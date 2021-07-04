package es.uji.ei102720gmtp.SanaProject.controller;

import es.uji.ei102720gmtp.SanaProject.dao.OcupaDao;
import es.uji.ei102720gmtp.SanaProject.model.Ocupa;
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
@RequestMapping("/ocupa")
public class OcupaController {

    private OcupaDao ocupaDao;

    @Autowired
    public void setOcupaDao(OcupaDao ocupaDao){
        this.ocupaDao = ocupaDao;
    }

    //Operacions: Crear, llistar, actualitzar, esborrar

    @RequestMapping("/list")
    public String listOcupacions(Model model){
        model.addAttribute("ocupacions", ocupaDao.getOcupes());
        return "zones/list";
    }

    @RequestMapping(value = "/add")
    public String addOcupacio(Model model){
        model.addAttribute("ocupa", new Ocupa());
        return "ocupa/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("ocupa") Ocupa ocupa, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "ocupa/add";
        ocupaDao.addOcupa(ocupa);
        return "redirect:list";
    }


    @RequestMapping(value = "/delete/{idReserva}/{idZona}")
    public String processDelete(@PathVariable int idReserva, @PathVariable int idZona){
        ocupaDao.deleteOcupa(idReserva, idZona);
        return "redirect:../list";
    }
}
