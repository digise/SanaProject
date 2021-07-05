package es.uji.ei102720gmtp.SanaProject.controller;


import es.uji.ei102720gmtp.SanaProject.Validation.MunicipiValidator;
import es.uji.ei102720gmtp.SanaProject.dao.EspaiPublicDao;
import es.uji.ei102720gmtp.SanaProject.dao.MunicipiDao;
import es.uji.ei102720gmtp.SanaProject.model.Municipi;
import es.uji.ei102720gmtp.SanaProject.services.ControladorsPerMunicipiService;
import es.uji.ei102720gmtp.SanaProject.services.GestorsPerMunicipiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/municipi")
public class MunicipiController {
    private MunicipiDao municipiDao;
    private GestorsPerMunicipiService gestorsPerMunicipiService;
    private EspaiPublicDao espaiPublicDao;

    @Autowired
    public void setMunicipiDao(MunicipiDao municipiDao){
        this.municipiDao = municipiDao;
    }

    @Autowired
    public void setGestorsPerMunicipiService(GestorsPerMunicipiService gestorsPerMunicipiService){
        this.gestorsPerMunicipiService = gestorsPerMunicipiService;
    }

    @Autowired
    public void setEspaiPublicDao(EspaiPublicDao espaiPublicDao) {
        this.espaiPublicDao = espaiPublicDao;
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
        MunicipiValidator municipiValidator = new MunicipiValidator();
        municipiValidator.validate(municipi, bindingResult);
        if(bindingResult.hasErrors())
            return "municipi/add";
        municipiDao.addMunicipi(municipi);
        return "redirect:list";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String editMunicipi(Model model, @PathVariable int id){
        model.addAttribute("municipi", municipiDao.getMunicipi(id));
        return "municipi/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String processUpdateSubmit(@ModelAttribute("municipi") Municipi municipi, BindingResult bindingResult, Model model){
        MunicipiValidator municipiValidator = new MunicipiValidator();
        municipiValidator.validate(municipi, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("municipi", municipi);
            return "municipi/update";
        }
        municipiDao.updateMunicipi(municipi);
        return "redirect:list";
    }

    @RequestMapping(value = "/delete/{id}")
    public String processDelete(@PathVariable int id, RedirectAttributes redirectAttributes){
        if (!gestorsPerMunicipiService.getGestorsPerMunicipi(id).isEmpty()){
            String msg = String.format("No es pot eliminar un municipi que conté gestors");
            redirectAttributes.addFlashAttribute("alert", msg);
            return "redirect:../list";
        }
        if (!espaiPublicDao.getEspaisPublicsFromMunicipi(id).isEmpty()){
            String msg = String.format("No es pot eliminar un municipi que conté espais publics");
            redirectAttributes.addFlashAttribute("alert", msg);
            return "redirect:../list";
        }

        String msg = String.format("S'ha borrat el municipi: " + municipiDao.getMunicipi(id).getNom());
        redirectAttributes.addFlashAttribute("alert", msg);
        municipiDao.deleteMunicipi(id);
        return "redirect:../list";
    }
}
