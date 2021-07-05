package es.uji.ei102720gmtp.SanaProject.controller;

import es.uji.ei102720gmtp.SanaProject.Validation.EspaiPublicValidator;
import es.uji.ei102720gmtp.SanaProject.Validation.ZonaValidator;
import es.uji.ei102720gmtp.SanaProject.dao.EspaiPublicDao;
import es.uji.ei102720gmtp.SanaProject.dao.OcupaDao;
import es.uji.ei102720gmtp.SanaProject.dao.ReservaDao;
import es.uji.ei102720gmtp.SanaProject.dao.ZonaDao;
import es.uji.ei102720gmtp.SanaProject.model.EspaiPublic;
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
@RequestMapping("zona")
public class ZonaController {


    private ZonaDao zonaDao;
    private EspaiPublicDao espaiPublicDao;
    private ReservaDao reservaDao;
    private OcupaDao ocupaDao;

    @Autowired
    public void setZonaDao(ZonaDao zonaDao){
        this.zonaDao = zonaDao;
    }

    @Autowired
    public void setReservaDao(ReservaDao reservaDao){
        this.reservaDao = reservaDao;
    }

    @Autowired
    public void setOcupaDao(OcupaDao ocupaDao){
        this.ocupaDao = ocupaDao;
    }

    @Autowired
    public void setEspaiPublicDao(EspaiPublicDao espaiPublicDao){
        this.espaiPublicDao = espaiPublicDao;
    }
    //Operacions: Crear, llistar, actualitzar, esborrar

    @RequestMapping("/list/{id}")
    public String listZones(Model model, @PathVariable int id){
        model.addAttribute("zones", zonaDao.getZonesFromEspai(id));
        model.addAttribute("espai", espaiPublicDao.getEspaiPublic(id));

        return "zona/list";
    }

    @RequestMapping(value = "/add/{id}")
    public String addZona(Model model, @PathVariable int id){
        Zona zona = new Zona();
        zona.setIdEspai(id);
        model.addAttribute("zona", zona);
        model.addAttribute("espai", espaiPublicDao.getEspaiPublic(id));
        return "zona/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("zona") Zona zona, BindingResult bindingResult , RedirectAttributes redirectAttributes, Model model){
        ZonaValidator zonaValidator = new ZonaValidator();
        zonaValidator.validate(zona, bindingResult);
        if(bindingResult.hasErrors()) {
            model.addAttribute("espai", espaiPublicDao.getEspaiPublic(zona.getIdEspai()));
            return "zona/add";
        }
        zonaDao.addZona(zona);
        List<Zona> zones = zonaDao.getZones();
        Zona zonaAux = zones.get(zones.size() - 1);
        String msg = String.format("La zona amb id " + zonaAux.getId() + " s'ha afegit correctament");
        redirectAttributes.addFlashAttribute("alert", msg);
        return "redirect:list/" + zona.getIdEspai();
    }

    @RequestMapping(value="/update/{id}", method = RequestMethod.GET)
    public String editZona(Model model, @PathVariable int id){
        model.addAttribute("zona", zonaDao.getZona(id));
        return "zona/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String processUpdateSubmit(@ModelAttribute("zona") Zona zona, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors())
            return "zona/update";
        zonaDao.updateZona(zona);
        return "redirect:list";
    }

    @RequestMapping(value = "/delete/{id}")
    public String processDelete(@PathVariable int id, RedirectAttributes redirectAttributes){
        if (!ocupaDao.getOcupesPerZona(id).isEmpty()){
            String msg = String.format("No es pot eliminar la zona ja que ha sigut o está sent utilitzada");
            redirectAttributes.addFlashAttribute("alertErroni", msg);
            return "redirect:../list/" + zonaDao.getZona(id).getIdEspai();
        }
        int idEspai = zonaDao.getZona(id).getIdEspai();
        zonaDao.deleteZona(id);
        String msg = String.format("La zona amb id " + id + " s'ha borrat correctament");
        redirectAttributes.addFlashAttribute("alert", msg);
        return "redirect:../list/" + idEspai;
    }







}
