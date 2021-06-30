package es.uji.ei102720gmtp.SanaProject.controller;

import es.uji.ei102720gmtp.SanaProject.dao.*;
import es.uji.ei102720gmtp.SanaProject.model.Ciutada;
import es.uji.ei102720gmtp.SanaProject.model.Controlador;
import es.uji.ei102720gmtp.SanaProject.model.GestorMunicipal;
import es.uji.ei102720gmtp.SanaProject.model.UserDetails;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

class RegistrarseValidator implements Validator {

    @Override
    public boolean supports(Class<?> cls) {
        return UserDetails.class.isAssignableFrom(cls);
    }
    @Override
    public void validate(Object obj, Errors errors) {
        // Exercici: Afegeix codi per comprovar que
        // l'usuari i la contrasenya no estiguen buits
        // ...
        Ciutada ciutada = (Ciutada) obj;
        if (ciutada.getNif().trim().equals(""))
            errors.rejectValue("nif", "obligatori",
                    "Cal introduir un valor");

        if (ciutada.getNom().trim().equals(""))
            errors.rejectValue("contrasenya", "obligatori" ,
                    "Cal introduir un valor");
        if (ciutada.getCognoms().trim().equals(""))
            errors.rejectValue("contrasenya", "obligatori" ,
                    "Cal introduir un valor");
        if (ciutada.getTelefon().trim().length() != 9)
            errors.rejectValue("telefon", "obligatori" ,
                    "Cal introduir 9 dígits");
        if (ciutada.getEmail().trim().equals("") || !ciutada.getEmail().contains("@"))
            errors.rejectValue("email", "obligatori" ,
                    "Cal introduir be el correu. Ex: diego@gmail.com");
        if (ciutada.getDomicili().trim().equals("") || ciutada.getDomicili().charAt(0) != 'C' || ciutada.getDomicili().charAt(1) != ' ')
            errors.rejectValue("domicili", "obligatori" ,
                    "Cal introduir be el domicili. Ex: C Ramon y Cajal, 7");
        if (ciutada.getPais().trim().equals(""))
            errors.rejectValue("pais", "obligatori",
                    "Cal introduir un valor");


    }
}

@Controller
public class RegistrarseController {

    @Autowired
    private CiutadaDao ciutadaDao;

    public void setCiutadaDao(CiutadaDao ciutadaDao) {
        this.ciutadaDao = ciutadaDao;
    }

    @RequestMapping("/registrarse")
    public String login(Model model) {
        model.addAttribute("ciutada", new Ciutada());
        return "registrarse";
    }

    @RequestMapping(value="/registrarse", method= RequestMethod.POST)
    public String checkLogin(@ModelAttribute("ciutada") Ciutada ciutada,
                             BindingResult bindingResult, HttpSession session, RedirectAttributes redirectAttributes) {

        RegistrarseValidator ciutadaValidator = new RegistrarseValidator();
        ciutadaValidator.validate(ciutada, bindingResult);
        if( !ciutadaDao.getCiutada(ciutada.getNif()).equals(ciutada)){
            bindingResult.rejectValue("nif", "obligatori", "El nif ya existeix en la bbdd");
        }

        if (bindingResult.hasErrors()) {
            return "registrarse";
        }
        UserDetails user = new UserDetails();
        user.setNif(ciutada.getNif());
        user.setPassword(ciutada.getContrasenya());
        session.setAttribute("user", user);
        ciutadaDao.addCiutada(ciutada);
        String msg = String.format("T'has registrat correctament!!");
        redirectAttributes.addFlashAttribute("alert", msg);
        return "redirect:login";
    }

}
