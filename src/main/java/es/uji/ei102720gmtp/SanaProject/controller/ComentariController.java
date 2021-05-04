package es.uji.ei102720gmtp.SanaProject.controller;


import es.uji.ei102720gmtp.SanaProject.dao.ComentarisDao;
import es.uji.ei102720gmtp.SanaProject.model.Comentari;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comentari")
public class ComentariController {
    private ComentarisDao comentarisDao;

    @Autowired
    public void setComentarisDao(ComentarisDao comentarisDao){
        this.comentarisDao = comentarisDao;
    }

    //Operacions: Crear, llistar, actualitzar, esborrar

    @RequestMapping("/list")
    public String listComentaris(Model model){
        model.addAttribute("comentaris", comentarisDao.getComentaris());
        return "comentari/list";
    }

    @RequestMapping("/listEspai/{id}")
    public String listComentarisEspai(Model model, @PathVariable String id){
        model.addAttribute("comentaris", comentarisDao.getComentarisEspaiPublic("2"));
        return "comentari/listEspai";
    }

    @RequestMapping("/listCiutada/{nif}")
    public String listComentarisCiutada(Model model, @PathVariable String nif){
        model.addAttribute("comentaris", comentarisDao.getComentarisCiutada("33344556T"));
        return "comentari/listCiutada";
    }

    @RequestMapping(value = "/add")
    public String addComentari(Model model){
        model.addAttribute("comentari", new Comentari());
        return "comentari/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("comentari") Comentari comentari, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "comentari/add";
        comentarisDao.addComentari(comentari);
        return "redirect:list";
    }

    @RequestMapping(value = "/update/{idEspai}/{nifCiutada}/{contador}", method = RequestMethod.GET)
    public String processUpdateSubmit(Model model, @PathVariable String idEspai, @PathVariable String nifCiutada, @PathVariable long contador){
        model.addAttribute("comentari", comentarisDao.getComentari(idEspai, nifCiutada, contador));
        return "comentari/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String processUpdateSubmit(@ModelAttribute("comentari") Comentari comentari, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "comentari/update";
        comentarisDao.updateComentari(comentari);
        return "redirect:list";
    }

    @RequestMapping(value = "/delete/{idEspai}/{nifCiutada}/{contador}")
    public String processDelete(@PathVariable String idEspai, @PathVariable String nifCiutada, @PathVariable long contador){
        comentarisDao.deleteComentari(idEspai, nifCiutada, contador);
        return "redirect:../../../list";
    }

}

