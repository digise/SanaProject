package es.uji.ei102720gmtp.SanaProject.controller;


import es.uji.ei102720gmtp.SanaProject.dao.UserDao;
import es.uji.ei102720gmtp.SanaProject.model.UserDetails;
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
        if (userDetails.getUsername().trim().equals(""))
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
    private UserDao userDao;

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
        user = userDao.loadUserByUsername(user.getUsername(), user.getPassword());
        if (user == null) {
            bindingResult.rejectValue("password", "badpw", "Contrasenya incorrecta");
            return "login";
        }
        // Autenticats correctament.
        // Guardem les dades de l'usuari autenticat a la sessió
        session.setAttribute("user", user);
        session.setAttribute("nextUrl", "/user/list");
        // Torna a la pàgina principal
        if (session.getAttribute("nextUrl") != null) {
            String redireccion = (String)session.getAttribute("nextUrl");
            session.removeAttribute("nextUrl");
            return redireccion;
        }
        return "redirect:/";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}

