package es.uji.ei102720gmtp.SanaProject.controller;


import es.uji.ei102720gmtp.SanaProject.dao.EspaiPublicDao;
import es.uji.ei102720gmtp.SanaProject.dao.MunicipiDao;
import es.uji.ei102720gmtp.SanaProject.model.*;
import es.uji.ei102720gmtp.SanaProject.model.*;
import es.uji.ei102720gmtp.SanaProject.model.enums.EstatReserva;
import es.uji.ei102720gmtp.SanaProject.services.EspaiPublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/espaiPublic")
public class EspaiPublicController {
    private EspaiPublicDao espaiPublicDao;
    private EspaiPublicService espaiPublicService;
    private MunicipiDao municipiDao;

    @Autowired
    public void setEspaiPublicDao(EspaiPublicDao espaiPublicDao){
        this.espaiPublicDao = espaiPublicDao;
    }

    @Autowired
    public void setEspaiPublicService(EspaiPublicService espaiPublicService){
        this.espaiPublicService = espaiPublicService;
    }

    @Autowired
    public void setMunicipiDao(MunicipiDao municipiDao) {
        this.municipiDao = municipiDao;
    }



    //Operacions: Crear, llistar, actualitzar, esborrar

    @RequestMapping("/list")
    public String listEspaiPublic(Model model){
        model.addAttribute("espais", espaiPublicDao.getEspaisPublics());
        return "espaiPublic/list";
    }

    @RequestMapping("espaisprovincia/{provincia}")
    public String listEspaiPublicsProvincia(Model model, @PathVariable String provincia){
        model.addAttribute("espais", espaiPublicService.getEspaisPublicsPerProvincia(provincia));

        model.addAttribute("provincia", provincia);
        return "espaiPublic/espaisprovincia";
    }

    @RequestMapping("espaisPerMunicipi")
    public String listEspaiPublicsPerMunicipi(Model model, HttpSession session){
        GestorMunicipal gestorMunicipal = (GestorMunicipal) session.getAttribute("gestorMunicipal");
        int idMunicipi = gestorMunicipal.getIdMunicipi();
        model.addAttribute("espais", espaiPublicService.getEspaisPublicsPerMunicipi(idMunicipi));
        model.addAttribute("municipi", municipiDao.getMunicipi(idMunicipi));
        return "espaiPublic/espaisPerMunicipi";
    }

    @RequestMapping(value = "/add")
    public String addEspaiPublic(Model model){
        model.addAttribute("espaiPublic", new EspaiPublic());
        return "espaiPublic/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("espaiPublic") EspaiPublic espaiPublic, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "espaiPublic/add";
        espaiPublicDao.addEspaiPublic(espaiPublic);
        return "redirect:list";
    }

    @RequestMapping(value="/update/{id}", method = RequestMethod.GET)
    public String editEspaiPublic(Model model, @PathVariable int id){
        model.addAttribute("espaiPublic", espaiPublicDao.getEspaiPublic(id));
        return "espaiPublic/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String processUpdateSubmit(@ModelAttribute("espaiPublic") EspaiPublic espaiPublic, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "espaiPublic/update";
        espaiPublicDao.updateEspaiPublic(espaiPublic);
        return "redirect:list";
    }

    @RequestMapping(value = "/delete/{id}")
    public String processDelete(@PathVariable int id){
        espaiPublicDao.deleteEspaiPublic(id);
        return "redirect:../list";
    }

    @RequestMapping(value= "/informacio/{id}")
    public String showInformacio(Model model, @PathVariable int id){
        EspaiPublic espai = espaiPublicDao.getEspaiPublic(id);
        model.addAttribute("espai", espai);
        return "/espaiPublic/informacio";
    }

    @RequestMapping(value = "/elegirZona/{id}")
    public String showEspaiPublic(Model model, @PathVariable int id, HttpSession session) {
        //Mirem si está loggeat ja
        EspaiPublic espai = espaiPublicDao.getEspaiPublic(id);
        model.addAttribute("espai", espai);



        // Passar municipi i provincia

        List<FranjaHoraria> frangesHoraries = espaiPublicService.getFrangesHoraries(espai.getId());
        model.addAttribute("franges", frangesHoraries);

        LocalDate diaDate = LocalDate.now().plus(2, ChronoUnit.DAYS);
        model.addAttribute("dia", diaDate);

        Map<Integer, List<Zona>> zonesDisponibles = espaiPublicService.getZonesDisponibles(diaDate, frangesHoraries, espai.getId());
        model.addAttribute("zones", zonesDisponibles);

        ElegirZonaBean dades = new ElegirZonaBean(espai.getId(), diaDate);
        model.addAttribute("dades", dades);

        ReservaDadesCompletes reserva = new ReservaDadesCompletes();
        reserva.setIdEspai(espai.getId());
        reserva.setEstat(EstatReserva.PENDENTUS);
        Ciutada ciutada = (Ciutada) session.getAttribute("ciutada");
        reserva.setNifCiutada(ciutada.getNif());
        reserva.setDataReserva(diaDate);
        model.addAttribute("reserva", reserva);

        return "/espaiPublic/elegirZona";
    }


    @RequestMapping(value ="/elegirZona", method = RequestMethod.POST)
    public String showEspaiPublicActualitzat(@ModelAttribute("dades") ElegirZonaBean dades, BindingResult bindingResult, Model model, HttpSession session) {
        EspaiPublic espai = espaiPublicDao.getEspaiPublic(dades.getIdEspai());
        model.addAttribute("espai", espai);

        // Passar municipi i provincia

        List<FranjaHoraria> frangesHoraries = espaiPublicService.getFrangesHoraries(espai.getId());
        model.addAttribute("franges", frangesHoraries);

        LocalDate diaDate = dades.getDiaElegit();
        model.addAttribute("dia", diaDate);

        Map<Integer, List<Zona>> zonesDisponibles = espaiPublicService.getZonesDisponibles(diaDate, frangesHoraries, espai.getId());
        model.addAttribute("zones", zonesDisponibles);

        ReservaDadesCompletes reserva = new ReservaDadesCompletes();
        reserva.setIdEspai(espai.getId());
        reserva.setEstat(EstatReserva.PENDENTUS);
        Ciutada ciutada = (Ciutada) session.getAttribute("ciutada");
        reserva.setNifCiutada(ciutada.getNif());
        reserva.setDataReserva(diaDate);
        model.addAttribute("reserva", reserva);

        ElegirZonaBean novesDades = new ElegirZonaBean(espai.getId(), dades.getDiaElegit());
        model.addAttribute("dades", novesDades);

        return "/espaiPublic/elegirZona";
    }

    @RequestMapping(value = "/informacio", method = RequestMethod.POST)
    public String showEspaiPublic(@ModelAttribute("zona") Zona zona) {
       return null;
    }

    @RequestMapping("/seleccionarProvincia")
    public String mostrarSeleccionarProvincia(HttpSession session, Model model){
        /*String nextUrl = "espaiPublic/seleccionarProvincia";
        if (session.getAttribute("user") == null)
        {

            model.addAttribute("user", new UserDetails());
            session.setAttribute("nextUrl", nextUrl);
            return "login";
        }
        session.setAttribute("nextUrl", nextUrl);*/
        return "espaiPublic/seleccionarProvincia";
    }


}