package es.uji.ei102720gmtp.SanaProject.controller;


import es.uji.ei102720gmtp.SanaProject.Validation.ControladorsAmbEspaiPublicValidator;
import es.uji.ei102720gmtp.SanaProject.dao.EspaiPublicDao;
import es.uji.ei102720gmtp.SanaProject.dao.MunicipiDao;
import es.uji.ei102720gmtp.SanaProject.model.*;
import es.uji.ei102720gmtp.SanaProject.model.enums.EstatReserva;
import es.uji.ei102720gmtp.SanaProject.model.enums.TipusAcces;
import es.uji.ei102720gmtp.SanaProject.model.enums.TipusTerreny;
import es.uji.ei102720gmtp.SanaProject.services.EspaiPublicService;
import es.uji.ei102720gmtp.SanaProject.services.MunicipisPerControladorService;
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
    private MunicipisPerControladorService municipisPerControladorService;

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

    @Autowired
    public void setMunicipisPerControladorService(MunicipisPerControladorService municipisPerControladorService){
        this.municipisPerControladorService = municipisPerControladorService;
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
        System.out.println(model.getAttribute("espais"));
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
        Municipi municipi = municipiDao.getMunicipi(espaiPublicDao.getEspaiPublic(id).getIdMunicipi());
        String provincia = municipi.getProvincia().name();
        model.addAttribute("provincia", provincia);
        model.addAttribute("nomMunicipi", municipi.getNom());
        model.addAttribute("municipis", municipiDao.getMunicipis());
        return "espaiPublic/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String processUpdateSubmit(@ModelAttribute("espaiPublic") EspaiPublic espaiPublic, BindingResult bindingResult){
        espaiPublic.setLocalitzacio(municipiDao.getMunicipi(espaiPublic.getIdMunicipi()).getNom() + ", " + municipiDao.getMunicipi(espaiPublic.getIdMunicipi()).getProvincia().toString());
        if (bindingResult.hasErrors()) {
            return "espaiPublic/update";
        }
        espaiPublicDao.updateEspaiPublic(espaiPublic);
        return "redirect:espaisPerMunicipi";
    }

    @RequestMapping(value = "/delete/{id}")
    public String processDelete(@PathVariable int id){
        espaiPublicDao.deleteEspaiPublic(id);
        return "redirect:../list";
    }

    @RequestMapping(value= "/informacioEspai/{id}")
    public String showInformacio(Model model, @PathVariable int id){
        EspaiPublic espai = espaiPublicDao.getEspaiPublic(id);
        model.addAttribute("espai", espai);
        Municipi municipi = municipiDao.getMunicipi(espaiPublicDao.getEspaiPublic(id).getIdMunicipi());
        String provincia = municipi.getProvincia().getDescripcion();
        model.addAttribute("provincia", provincia);
        model.addAttribute("nomMunicipi", municipi.getNom());
        return "/espaiPublic/informacioEspai";
    }

    @RequestMapping(value = "/elegirZona/{id}")
    public String showEspaiPublic(Model model, @PathVariable int id, HttpSession session) {
        //Mirem si está loggeat ja
        EspaiPublic espai = espaiPublicDao.getEspaiPublic(id);
        model.addAttribute("espai", espai);

        Municipi municipi = municipiDao.getMunicipi( espai.getIdMunicipi() );
        model.addAttribute("municipi", municipi);

        List<FranjaHoraria> frangesHoraries = espaiPublicService.getFrangesHoraries(espai.getId());
        model.addAttribute("franges", frangesHoraries);

        LocalDate diaDate = LocalDate.now().plus(2, ChronoUnit.DAYS);
        model.addAttribute("dia", diaDate);

        ElegirZonaBean dades = new ElegirZonaBean(espai.getId(), diaDate);
        model.addAttribute("dades", dades);

        return "/espaiPublic/elegirZona";
    }


    @RequestMapping(value ="/elegirZona", method = RequestMethod.POST)
    public String showEspaiPublicActualitzat(@ModelAttribute("dades") ElegirZonaBean dades, BindingResult bindingResult, Model model, HttpSession session) {
        EspaiPublic espai = espaiPublicDao.getEspaiPublic(dades.getIdEspai());
        model.addAttribute("espai", espai);

        // Passar municipi i provincia
        Municipi municipi = municipiDao.getMunicipi( espai.getIdMunicipi() );
        model.addAttribute("municipi", municipi);

        List<FranjaHoraria> frangesHoraries = espaiPublicService.getFrangesHoraries(espai.getId());
        model.addAttribute("franges", frangesHoraries);

        LocalDate diaDate = dades.getDiaElegit();
        model.addAttribute("dia", diaDate);


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

    @RequestMapping("/espaisPerControlador")
    public String mostrarEspaisPerControlador(Model model, HttpSession session){
        Controlador controlador = (Controlador) session.getAttribute("controlador");
        List<EspaiPublic> espaisControlador = municipisPerControladorService.municipisPerControlador(controlador.getNif());
        model.addAttribute("espaisControlador", espaisControlador);
        return "espaiPublic/espaisPerControlador";
    }
}