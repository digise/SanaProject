package es.uji.ei102720gmtp.SanaProject.controller;


import es.uji.ei102720gmtp.SanaProject.dao.EspaiPublicDao;
import es.uji.ei102720gmtp.SanaProject.model.EspaiPublic;
import es.uji.ei102720gmtp.SanaProject.model.FranjaHoraria;
import es.uji.ei102720gmtp.SanaProject.model.Zona;
import es.uji.ei102720gmtp.SanaProject.services.EspaiPublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/espaiPublic")
public class EspaiPublicController {
    private EspaiPublicDao espaiPublicDao;
    private EspaiPublicService espaiPublicService;

    @Autowired
    public void setEspaiPublicDao(EspaiPublicDao espaiPublicDao){
        this.espaiPublicDao = espaiPublicDao;
    }

    @Autowired
    public void setEspaiPublicService(EspaiPublicService espaiPublicService){
        this.espaiPublicService = espaiPublicService;
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
        System.out.println(espaiPublicService.getEspaisPublicsPerProvincia(provincia));

        model.addAttribute("provincia", provincia);
        return "espaiPublic/espaisprovincia";
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

    @RequestMapping(value = "/informacio/{id}")
    public String showEspaiPublic(Model model, @PathVariable int id) {

        EspaiPublic espai = espaiPublicDao.getEspaiPublic(id);
        model.addAttribute("espai", espai);

        // Passar municipi i provincia

        List<Zona> zonesEspai = espaiPublicService.getZonesFromEspai(espai.getId());
        model.addAttribute("zones", zonesEspai);

        if ( !zonesEspai.isEmpty() ) {
            Zona zonaPrimera = zonesEspai.get(0);
            model.addAttribute("zonaElegida", zonaPrimera);

            List<FranjaHoraria> frangesZona = espaiPublicService.getFrangesHorariesDisponibles(id, zonaPrimera.getId());
            model.addAttribute("franges", frangesZona);
        }

        model.addAttribute("zona", new Zona());

        return "/espaiPublic/informacio";
    }

    @RequestMapping(value = "/informacio/{idEspai}/{idZona}")
    public String showEspaiPublic(Model model, @PathVariable int idEspai, @PathVariable int idZona) {
        EspaiPublic espai = espaiPublicDao.getEspaiPublic(idEspai);
        model.addAttribute("espai", espai);

        List<Zona> zonesEspai = espaiPublicService.getZonesFromEspai(espai.getId());
        model.addAttribute("zones", zonesEspai);

        Zona zona = espaiPublicService.getZona(idZona);
        model.addAttribute("zonaElegida", zona);

        List<FranjaHoraria> frangesZona = espaiPublicService.getFrangesHorariesDisponibles(idEspai, idZona);
        model.addAttribute("franges", frangesZona);

        model.addAttribute("zona", new Zona());


        return "/espaiPublic/informacio";
    }

    @RequestMapping(value = "/informacio", method = RequestMethod.POST)
    public String showEspaiPublic(@ModelAttribute("zona") Zona zona) {
       return null;
    }


}