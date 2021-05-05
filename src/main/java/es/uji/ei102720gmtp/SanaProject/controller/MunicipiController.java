package es.uji.ei102720gmtp.SanaProject.controller;


import es.uji.ei102720gmtp.SanaProject.dao.MunicipiDao;
import es.uji.ei102720gmtp.SanaProject.model.Municipi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/municipi")
public class MunicipiController {
    private MunicipiDao municipiDao;

    @Autowired
    public void setMunicipiDao(MunicipiDao municipiDao){
        this.municipiDao = municipiDao;
    }

    //Operacions: Crear, llistar, actualitzar, esborrar

    @RequestMapping("/list")
    public String listMunicipis(Model model){
        model.addAttribute("municipis", municipiDao.getMunicipis());
        return "municipi/list";
    }

    @RequestMapping(value = "/add")
    public String addMunicipi(Model model){
        model.addAttribute("municipi", new Municipi());
        return "municipi/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("municipi") Municipi municipi, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "municipi/add";
        municipiDao.addMunicipi(municipi);
        return "redirect:list";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String editMunicipi(Model model, @PathVariable String id){
        model.addAttribute("municipi", municipiDao.getMunicipi(id));
        return "municipi/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String processUpdateSubmit(@ModelAttribute("municipi") Municipi municipi, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "municipi/update";
        municipiDao.updateMunicipi(municipi);
        return "redirect:list";
    }

    @RequestMapping(value = "/delete/{id}")
    public String processDelete(@PathVariable String id){
        municipiDao.deleteMunicipi(id);
        return "redirect:../list";
    }
}