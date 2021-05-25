package es.uji.ei102720gmtp.SanaProject.controller;

import es.uji.ei102720gmtp.SanaProject.dao.EspaiPublicDao;
import es.uji.ei102720gmtp.SanaProject.dao.ReservaDao;
import es.uji.ei102720gmtp.SanaProject.model.EspaiPublic;
import es.uji.ei102720gmtp.SanaProject.model.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/reserva")
public class ReservaController {

    private ReservaDao reservaDao;
    private EspaiPublicDao espaiPublicDao;

    @Autowired
    public void setReservaDao(ReservaDao reservaDao){
        this.reservaDao = reservaDao;
    }

    @Autowired
    public ReservaController(EspaiPublicDao espaiPublicDao) {
        this.espaiPublicDao = espaiPublicDao;
    }

    //Operacions: Crear, llistar, actualitzar, esborrar

    @RequestMapping("/list")
    public String listReserves(Model model){
        model.addAttribute("reserves", reservaDao.getReserves());
        return "reserva/list";
    }

    @RequestMapping(value = "/add/{idEspai}")
    public String addReserva(Model model, @PathVariable int idEspai){
        EspaiPublic espaiPublic = espaiPublicDao.getEspaiPublic(idEspai);
        model.addAttribute("nom", espaiPublic.getNom());

        model.addAttribute("reserva", new Reserva());
        return "reserva/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("reserva") Reserva reserva, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "reserva/add";
        reservaDao.addReserva(reserva);
        return "redirect:list";
    }

    @RequestMapping(value="/update/{id}", method = RequestMethod.GET)
    public String editReserva(Model model, @PathVariable String id){
        model.addAttribute("reserva", reservaDao.getReserva(id));
        return "reserva/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String processUpdateSubmit(@ModelAttribute("reserva") Reserva reserva, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "reserva/update";
        reservaDao.updateReserva(reserva);
        return "redirect:list";
    }

    @RequestMapping(value = "/delete/{id}")
    public String processDelete(@PathVariable String id){
        reservaDao.deleteReserva(id);
        return "redirect:../list";
    }


}
