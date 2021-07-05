package es.uji.ei102720gmtp.SanaProject.controller;

import es.uji.ei102720gmtp.SanaProject.Validation.FranjaHorariaValidator;
import es.uji.ei102720gmtp.SanaProject.Validation.ZonaValidator;
import es.uji.ei102720gmtp.SanaProject.dao.EspaiPublicDao;
import es.uji.ei102720gmtp.SanaProject.dao.FranjaHorariaDao;
import es.uji.ei102720gmtp.SanaProject.dao.ReservaDao;
import es.uji.ei102720gmtp.SanaProject.model.FranjaHoraria;
import es.uji.ei102720gmtp.SanaProject.model.Zona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/franja")
public class FranjaHorariaController {
    private FranjaHorariaDao franjaHorariaDao;
    private EspaiPublicDao espaiPublicDao;
    private ReservaDao reservaDao;

    @Autowired
    public void setFranjaHorariaDao(FranjaHorariaDao franjaHorariaDao){
        this.franjaHorariaDao = franjaHorariaDao;
    }

    @Autowired
    public void setEspaiPublicDao(EspaiPublicDao espaiPublicDao){
        this.espaiPublicDao = espaiPublicDao;
    }

    @Autowired
    public void setReservaDao(ReservaDao reservaDao){
        this.reservaDao = reservaDao;
    }

    //Operacions: Crear, llistar, actualitzar, esborrar

    @RequestMapping("/list/{id}")
    public String listFranjes(Model model, @PathVariable int id){
        model.addAttribute("franges", franjaHorariaDao.getFranjasEspai(id));
        model.addAttribute("espai", espaiPublicDao.getEspaiPublic(id));
        return "franja/list";
    }

    @RequestMapping(value = "/add/{id}")
    public String addFranja(Model model, @PathVariable int id){
        FranjaHoraria franjaHoraria = new FranjaHoraria();
        franjaHoraria.setIdEspai(id);
        model.addAttribute("franja", franjaHoraria);
        model.addAttribute("espai", espaiPublicDao.getEspaiPublic(id));
        return "franja/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("franja") FranjaHoraria franjaHoraria, BindingResult bindingResult , RedirectAttributes redirectAttributes, Model model){
        FranjaHorariaValidator franjaHorariaValidator = new FranjaHorariaValidator();
        franjaHorariaValidator.validate(franjaHoraria, bindingResult);
        if(bindingResult.hasErrors()) {
            model.addAttribute("espai", espaiPublicDao.getEspaiPublic(franjaHoraria.getIdEspai()));
            return "franja/add";
        }
        franjaHorariaDao.addFranjaHoraria(franjaHoraria);
        List<FranjaHoraria> franjaHorarias = franjaHorariaDao.getFranjas();
        FranjaHoraria franjaAux = franjaHorarias.get(franjaHorarias.size() - 1);
        String msg = String.format("La franja horaria amb id " + franjaAux.getId() + " s'ha afegit correctament");
        redirectAttributes.addFlashAttribute("alert", msg);
        return "redirect:list/" + franjaAux.getIdEspai();
    }

    @RequestMapping(value="/update/{id}", method = RequestMethod.GET)
    public String editFranjaHoraria(Model model, @PathVariable int id){
        model.addAttribute("franjaHoraria", franjaHorariaDao.getFranjaHoraria(id));
        return "franjaHoraria/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String processUpdateSubmit(@ModelAttribute("franjaHoraria") FranjaHoraria franjaHoraria, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "franjaHoraria/update";
        franjaHorariaDao.updateFranjaHoraria(franjaHoraria);
        return "redirect:list";
    }

    @RequestMapping(value = "/delete/{id}")
    public String processDelete(@PathVariable int id, RedirectAttributes redirectAttributes){
        if (!reservaDao.getReservesPerFranges(id).isEmpty()){
            String msg = String.format("No es pot eliminar la franja horaria ja que ha sigut o está sent utilitzada");
            redirectAttributes.addFlashAttribute("alertErroni", msg);
            return "redirect:../list/" + franjaHorariaDao.getFranjaHoraria(id).getIdEspai();
        }
        int idEspai = franjaHorariaDao.getFranjaHoraria(id).getIdEspai();
        franjaHorariaDao.deleteFranjaHoraria(id);
        String msg = String.format("La franja amb id " + id + " s'ha borrat correctament");
        redirectAttributes.addFlashAttribute("alert", msg);
        return "redirect:../list/" + idEspai;
    }
}

