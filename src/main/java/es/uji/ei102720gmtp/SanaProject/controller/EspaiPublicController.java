package es.uji.ei102720gmtp.SanaProject.controller;


import es.uji.ei102720gmtp.SanaProject.dao.EspaiPublicDao;
import es.uji.ei102720gmtp.SanaProject.model.EspaiPublic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/espaiPublic")
public class EspaiPublicController {
    private EspaiPublicDao espaiPublicDao;

    @Autowired
    public void setEspaiPublicDao(EspaiPublicDao espaiPublicDao){
        this.espaiPublicDao = espaiPublicDao;
    }

    //Operacions: Crear, llistar, actualitzar, esborrar

    @RequestMapping("/list")
    public String listEspaiPublic(Model model){
        model.addAttribute("espais", espaiPublicDao.getEspaisPublics());
        return "espaiPublic/list";
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
    public String editEspaiPublic(Model model, @PathVariable String id){
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
    public String processDelete(@PathVariable String id){
        espaiPublicDao.deleteEspaiPublic(id);
        return "redirect:../list";
    }
}