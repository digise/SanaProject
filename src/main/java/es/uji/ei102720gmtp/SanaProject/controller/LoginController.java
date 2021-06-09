package es.uji.ei102720gmtp.SanaProject.controller;


import es.uji.ei102720gmtp.SanaProject.dao.CiutadaDao;
import es.uji.ei102720gmtp.SanaProject.dao.EspaiPublicDao;
import es.uji.ei102720gmtp.SanaProject.dao.GestorMunicipalDao;
import es.uji.ei102720gmtp.SanaProject.dao.UserDao;
import es.uji.ei102720gmtp.SanaProject.model.Ciutada;
import es.uji.ei102720gmtp.SanaProject.model.EspaiPublic;
import es.uji.ei102720gmtp.SanaProject.model.GestorMunicipal;
import es.uji.ei102720gmtp.SanaProject.model.UserDetails;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> cls) {
        return UserDetails.class.isAssignableFrom(cls);
    }
    @Override
    public void validate(Object obj, Errors errors) {
        // Exercici: Afegeix codi per comprovar que
        // l'usuari i la contrasenya no estiguen buits
        // ...
        UserDetails userDetails = (UserDetails) obj;
        if (userDetails.getNif().trim().equals(""))
            errors.rejectValue("user", "obligatori",
                    "Cal introduir un valor");

        if (userDetails.getPassword().trim().equals(""))
            errors.rejectValue("password", "eobligatori" ,
                    "Cal introduir un valor");

    }
}

@Controller
public class LoginController {
    @Autowired
    private EspaiPublicDao espaiPublicDao;
    @Autowired
    private CiutadaDao ciutadaDao;
    @Autowired
    private GestorMunicipalDao gestorMunicipalDao;

    public void setCiutadaDao(CiutadaDao ciutadaDao) {
        this.ciutadaDao = ciutadaDao;
    }

    public void setGestorMunicipalDao(GestorMunicipalDao gestorMunicipalDao) {
        this.gestorMunicipalDao = gestorMunicipalDao;
    }

    public void setEspaiPublicDao(EspaiPublicDao espaiPublicDao) {
        this.espaiPublicDao = espaiPublicDao;
    }

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new UserDetails());
        return "login";
    }

    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String checkLogin(@ModelAttribute("user") UserDetails user,
                             BindingResult bindingResult, HttpSession session) {
        UserValidator userValidator = new UserValidator();
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "login";
        }
        // Comprova que el login siga correcte
        // intentant carregar les dades de l'usuari
        for (Ciutada ciutada : ciutadaDao.getCiutadans()) {
            if (ciutada.getNif().equals(user.getNif())) {
                if (!ciutada.getPin().equals(user.getPassword())) {
                    bindingResult.rejectValue("password", "badpw", "Contrasenya incorrecta");
                    return "login";
                }
                session.setAttribute("user", user);
                session.setAttribute("ciutada", ciutada);
                session.setAttribute("nextUrl", "/espaiPublic/seleccionarProvincia");
                // Torna a la pàgina principal
                if (session.getAttribute("nextUrl") != null) {
                    String redireccion = (String) session.getAttribute("nextUrl");
                    session.removeAttribute("nextUrl");
                    return "redirect:" + redireccion;
                }
            }
        }


        for (GestorMunicipal gestorMunicipal : gestorMunicipalDao.getGestorsMunicipals())
            if (gestorMunicipal.getNif().equals(user.getNif())) {
                if (!gestorMunicipal.getContrasenya().equals(user.getPassword())) {
                    bindingResult.rejectValue("password", "badpw", "Contrasenya incorrecta");
                    return "login";
                }
                session.setAttribute("user", user);
                session.setAttribute("gestorMunicipal", gestorMunicipal);
                session.setAttribute("nextUrl", "/gestorMunicipal/indexGestor");
                // Torna a la pàgina principal
                if (session.getAttribute("nextUrl") != null) {
                    String redireccion = (String) session.getAttribute("nextUrl");
                    session.removeAttribute("nextUrl");
                    return "redirect:" + redireccion;
                }
            }
        return "redirect:/";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}

