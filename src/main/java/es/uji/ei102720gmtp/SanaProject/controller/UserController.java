package es.uji.ei102720gmtp.SanaProject.controller;

import javax.servlet.http.HttpSession;

import es.uji.ei102720gmtp.SanaProject.dao.UserDao;
import es.uji.ei102720gmtp.SanaProject.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/espaiPublic")
public class UserController {
    private UserDao userDao;

    @Autowired
    public void setSociDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping("/seleccionarProvincia")
    public String listProvincies(HttpSession session, Model model) {
        if (session.getAttribute("user") == null)
        {
            model.addAttribute("user", new UserDetails());
            return "login";
        }
        model.addAttribute("users", userDao.listAllUsers());
        session.setAttribute("nextUrl", "espaiPublic/seleccionarProvincia");
        return "espaiPublic/seleccionarProvincia";
    }
}
