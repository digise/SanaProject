package es.uji.ei102720gmtp.SanaProject.controller;


import es.uji.ei102720gmtp.SanaProject.Validation.ControladorsAmbEspaiPublicValidator;
import es.uji.ei102720gmtp.SanaProject.Validation.ElegirZonaBeanValidator;
import es.uji.ei102720gmtp.SanaProject.Validation.EspaiPublicValidator;
import es.uji.ei102720gmtp.SanaProject.Validation.ReservaValidator;
import es.uji.ei102720gmtp.SanaProject.dao.EspaiPublicDao;
import es.uji.ei102720gmtp.SanaProject.dao.MunicipiDao;
import es.uji.ei102720gmtp.SanaProject.model.*;
import es.uji.ei102720gmtp.SanaProject.model.enums.EstatReserva;
import es.uji.ei102720gmtp.SanaProject.model.enums.TipusAcces;
import es.uji.ei102720gmtp.SanaProject.model.enums.TipusTerreny;
import es.uji.ei102720gmtp.SanaProject.services.EspaiPublicService;
import es.uji.ei102720gmtp.SanaProject.services.MunicipisPerControladorService;
import es.uji.ei102720gmtp.SanaProject.services.ReservesService;
import es.uji.ei102720gmtp.SanaProject.services.ServeiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    private ReservesService reservesService;
    private ServeiService serveiService;

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

    @Autowired
    public void setReservesService(ReservesService reservesService){
        this.reservesService = reservesService;
    }

    @Autowired
    public void setServeiService(ServeiService serveiService) {
        this.serveiService = serveiService;
    }

    //Operacions: Crear, llistar, actualitzar, esborrar

    @RequestMapping("/list")
    public String listEspaiPublic(Model model){
        model.addAttribute("espais", espaiPublicDao.getEspaisPublics());
        return "espaiPublic/list";
    }

    @RequestMapping("espaisprovincia/{provincia}")
    public String listEspaiPublicsProvincia(Model model, @PathVariable String provincia, HttpSession session){
        String registrat = "No registrat";
        Ciutada ciutada = (Ciutada) session.getAttribute("ciutada");
        if (!(ciutada == null))
            registrat = "Registrat";
        model.addAttribute("registrat", registrat);
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

    @RequestMapping(value = "/add/{id}")
    public String addEspaiPublic(Model model, @PathVariable int id){
        EspaiPublic espaiPublic = new EspaiPublic();
        espaiPublic.setIdMunicipi(id);
        model.addAttribute("espaiPublic", espaiPublic);
        return "espaiPublic/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("espaiPublic") EspaiPublic espaiPublic, BindingResult bindingResult, Model model) {
        EspaiPublicValidator espaiPublicValidator = new EspaiPublicValidator();
        espaiPublicValidator.validate(espaiPublic, bindingResult);
        if (bindingResult.hasErrors()){
            return "espaiPublic/add";
        }
        espaiPublicDao.addEspaiPublic(espaiPublic);
        return "redirect:espaisPerMunicipi";
    }

    @RequestMapping(value="/update/{id}", method = RequestMethod.GET)
    public String editEspaiPublic(Model model, @PathVariable int id){
        model.addAttribute("espaiPublic", espaiPublicDao.getEspaiPublic(id));
        Municipi municipi = municipiDao.getMunicipi(espaiPublicDao.getEspaiPublic(id).getIdMunicipi());
        String provincia = municipi.getProvincia().name();
        model.addAttribute("provincia", provincia);
        model.addAttribute("nomMunicipi", municipi.getNom());
        return "espaiPublic/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String processUpdateSubmit(@ModelAttribute("espaiPublic") EspaiPublic espaiPublic, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
        espaiPublic.setLocalitzacio(municipiDao.getMunicipi(espaiPublic.getIdMunicipi()).getNom() + ", " + municipiDao.getMunicipi(espaiPublic.getIdMunicipi()).getProvincia().toString());
        EspaiPublicValidator espaiPublicValidator = new EspaiPublicValidator();
        espaiPublicValidator.validate(espaiPublic, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("municipis", municipiDao.getMunicipis());
            return "espaiPublic/update";
        }
        String msg = String.format("Les dades de l'espai amb nom: " + espaiPublic.getNom() + " se han actualitzat correctament");
        redirectAttributes.addFlashAttribute("alert", msg);
        espaiPublicDao.updateEspaiPublic(espaiPublic);
        return "redirect:espaisPerMunicipi";
    }

    @RequestMapping(value = "/delete/{id}")
    public String processDelete(@PathVariable int id){
        espaiPublicDao.deleteEspaiPublic(id);
        return "redirect:../espaisPerMunicipi";
    }

    @RequestMapping(value= "/informacioEspai/{id}")
    public String showInformacio(Model model, @PathVariable int id){
        EspaiPublic espai = espaiPublicDao.getEspaiPublic(id);
        model.addAttribute("espai", espai);
        Municipi municipi = municipiDao.getMunicipi(espaiPublicDao.getEspaiPublic(id).getIdMunicipi());
        String provincia = municipi.getProvincia().getDescripcion();
        model.addAttribute("provincia", provincia);
        model.addAttribute("nomMunicipi", municipi.getNom());
        List<ServeiPermanentComplet> serveiPermanentList = serveiService.getServeiPermanentInstalats(espai.getId());
        model.addAttribute("serveiPermanentList", serveiPermanentList);
        List<ServeiEstacionalComplet> serveiEstacionalList = serveiService.getServeisEstacionalsInstalats(espai.getId());
        model.addAttribute("serveiEstacionalList", serveiEstacionalList);
        return "/espaiPublic/informacioEspai";
    }

    @RequestMapping(value = "/elegirZona/{id}")
    public String showEspaiPublic(Model model, @PathVariable int id, HttpSession session) {
        //Mirem si está loggeat ja
        EspaiPublic espai = espaiPublicDao.getEspaiPublic(id);
        model.addAttribute("espai", espai);


        // Passar municipi i provincia
        Municipi municipi = municipiDao.getMunicipi( espai.getIdMunicipi() );
        model.addAttribute("municipi", municipi);

        List<FranjaHoraria> frangesHoraries = espaiPublicService.getFrangesHoraries(espai.getId());
        model.addAttribute("franges", frangesHoraries);

        LocalDate diaDate = LocalDate.now().plus(2, ChronoUnit.DAYS);
        model.addAttribute("dia", diaDate);

        ElegirZonaBean dades = new ElegirZonaBean(espai.getId(), diaDate);
        model.addAttribute("dades", dades);

        List<ServeiPermanentComplet> serveiPermanentList = serveiService.getServeiPermanentInstalats(espai.getId());
        model.addAttribute("serveiPermanentList", serveiPermanentList);

        List<ServeiEstacionalComplet> serveiEstacionalList = serveiService.getServeisEstacionalsInstalats(espai.getId());
        model.addAttribute("serveiEstacionalList", serveiEstacionalList);

        String registrat = "No registrat";
        Ciutada ciutada = (Ciutada) session.getAttribute("ciutada");
        if (!(ciutada == null))
            registrat = "Registrat";

        model.addAttribute("registrat", registrat);
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

        List<ServeiPermanentComplet> serveiPermanentList = serveiService.getServeiPermanentInstalats(espai.getId());
        model.addAttribute("serveiPermanentList", serveiPermanentList);

        List<ServeiEstacionalComplet> serveiEstacionalList = serveiService.getServeisEstacionalsInstalats(espai.getId());
        model.addAttribute("serveiEstacionalList", serveiEstacionalList);

        String registrat = "Registrat";
        model.addAttribute("registrat", registrat);

        ElegirZonaBeanValidator elegirZonaBeanValidator = new ElegirZonaBeanValidator();
        elegirZonaBeanValidator.validate(dades, bindingResult);
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getFieldError("diaElegit").toString());
            LocalDate diaDate = LocalDate.now().plus(2, ChronoUnit.DAYS);
            model.addAttribute("dia", diaDate);
            return "espaiPublic/elegirZona";
        }

        ElegirZonaBean novesDades = new ElegirZonaBean(espai.getId(), dades.getDiaElegit());
        model.addAttribute("dades", novesDades);

        LocalDate diaDate = dades.getDiaElegit();
        model.addAttribute("dia", diaDate);

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


    @RequestMapping("/seleccionarProvinciaSenseRegistrar")
    public String mostrarSeleccionarProvinciaSenseRegistrar(){
        return "espaiPublic/seleccionarProvinciaSenseRegistrar";
    }


    @RequestMapping("espaisProvinciaSenseRegistrar/{provincia}")
    public String listEspaiPublicsProvinciaSenseRegistrar(Model model, @PathVariable String provincia, HttpSession session){
        model.addAttribute("espais", espaiPublicService.getEspaisPublicsPerProvincia(provincia));
        model.addAttribute("provincia", provincia);
        return "espaiPublic/espaisProvinciaSenseRegistrar";
    }

    @RequestMapping(value= "/informacioEspaiSenseRegistrar/{id}")
    public String showInformacioSenseRegistrar(Model model, @PathVariable int id){
        EspaiPublic espai = espaiPublicDao.getEspaiPublic(id);
        model.addAttribute("espai", espai);
        Municipi municipi = municipiDao.getMunicipi(espaiPublicDao.getEspaiPublic(id).getIdMunicipi());
        String provincia = municipi.getProvincia().getDescripcion();
        model.addAttribute("provincia", provincia);
        model.addAttribute("municipi", municipi);
        List<ServeiPermanentComplet> serveiPermanentList = serveiService.getServeiPermanentInstalats(espai.getId());
        model.addAttribute("serveiPermanentList", serveiPermanentList);
        List<ServeiEstacionalComplet> serveiEstacionalList = serveiService.getServeisEstacionalsInstalats(espai.getId());
        model.addAttribute("serveiEstacionalList", serveiEstacionalList);
        return "/espaiPublic/informacioEspaiSenseRegistrar";
    }
}