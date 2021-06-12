package es.uji.ei102720gmtp.SanaProject.controller;

import es.uji.ei102720gmtp.SanaProject.dao.FakeUser;
import es.uji.ei102720gmtp.SanaProject.model.Controlador;
import es.uji.ei102720gmtp.SanaProject.model.EspaiPublic;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/responsable")
public class FakeUserController {
    private FakeUser fakeUser;

    public void setFakeUser(FakeUser fakeUser) {
        this.fakeUser = fakeUser;
    }

    @RequestMapping("/indexResponsable")
    public String mostrarIndexResponsable(Model model, HttpSession session){
        FakeUser responsable = (FakeUser) session.getAttribute("responsable");
        model.addAttribute("responsable", responsable);
        return "responsable/indexResponsable";
    }
}
