package es.uji.ei102720gmtp.SanaProject.controller;

import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import es.uji.ei102720gmtp.SanaProject.Validation.ReservaValidator;
import es.uji.ei102720gmtp.SanaProject.dao.*;
import es.uji.ei102720gmtp.SanaProject.model.*;
import es.uji.ei102720gmtp.SanaProject.model.enums.EstatReserva;
import es.uji.ei102720gmtp.SanaProject.model.ReservaDadesCompletes;
import es.uji.ei102720gmtp.SanaProject.services.EspaiPublicService;
import es.uji.ei102720gmtp.SanaProject.services.InterfaceReservesService;
import es.uji.ei102720gmtp.SanaProject.services.ReservesClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/reserva")
public class ReservaController {

    private ReservaDao reservaDao;
    private EspaiPublicDao espaiPublicDao;
    private EspaiPublicService espaiPublicService;
    private OcupaDao ocupaDao;
    private ZonaDao zonaDao;
    private FranjaHorariaDao franjaHorariaDao;
    private InterfaceReservesService reservesService;
    private ReservesClientService reservesClientService;

    @Autowired
    public void setReservaDao(ReservaDao reservaDao){
        this.reservaDao = reservaDao;
    }

    @Autowired
    public void setEspaiPublicDao(EspaiPublicDao espaiPublicDao) {
        this.espaiPublicDao = espaiPublicDao;
    }

    @Autowired
    public void setZonaDao(ZonaDao zonaDao) {
        this.zonaDao = zonaDao;
    }

    @Autowired
    public void setEspaiPublicService(EspaiPublicService espaiPublicService) {
        this.espaiPublicService = espaiPublicService;
    }

    @Autowired
    public void setReservesService(InterfaceReservesService reservesService) {
        this.reservesService = reservesService;
    }

    @Autowired
    public void setOcupaDao(OcupaDao ocupaDao) {
        this.ocupaDao = ocupaDao;
    }

    @Autowired
    public void setFranjaHorariaDao(FranjaHorariaDao franjaHorariaDao) {
        this.franjaHorariaDao = franjaHorariaDao;
    }

    @Autowired
    public void setReservesClientService(ReservesClientService reservesClientService) {
        this.reservesClientService = reservesClientService;
    }

    //Operacions: Crear, llistar, actualitzar, esborrar

    @RequestMapping("/list")
    public String listReserves(Model model){
        model.addAttribute("reserves", reservaDao.getReserves());
        return "reserva/list";
    }

    @RequestMapping(value = "/reservesClient/{nif}", method = RequestMethod.GET)
    public String mostrarReservesClient(@ModelAttribute("idReserva") int idReserva, Model model, @PathVariable String nif, RedirectAttributes redirectAttributes){
        model.addAttribute("reserves", reservesService.reservesPerClient(nif));
        model.addAttribute("nif", nif);
        String msg = String.format("Les dades de la reserva amb id " + reservaDao.getReserva(idReserva).getId() + " se ha cancelat correctament");
        redirectAttributes.addFlashAttribute("alert", msg);
        return "reserva/reservesClient";
    }

    @RequestMapping(value = "/reservesEspai/{id}", method = RequestMethod.GET)
    public String mostrarReservesEspai(Model model, @PathVariable int id){
        EspaiPublic espai = espaiPublicDao.getEspaiPublic(id);
        model.addAttribute("espai", espai);
        model.addAttribute("reserves", reservesService.reservesPerEspai(id));
        return "reserva/reservesEspai";
    }

    @RequestMapping(value = "/add/{idEspai}/{idFranja}/{dia}")
    public String addReserva(Model model, @PathVariable String idEspai, @PathVariable String idFranja, @PathVariable String dia, HttpSession session){

        LocalDate data = LocalDate.parse(dia);

        ReservaDadesCompletes reservaCompleta = new ReservaDadesCompletes();

        reservaCompleta.setIdEspai(idEspai);

        reservaCompleta.setIdFranja(idFranja);

        reservaCompleta.setDataReserva(data);

        Ciutada ciutada = (Ciutada) session.getAttribute("ciutada");
        reservaCompleta.setNifCiutada(ciutada.getNif());

        model.addAttribute("reservaDadesCompletes", reservaCompleta);

        FranjaHoraria franjaHoraria = franjaHorariaDao.getFranjaHoraria(Integer.valueOf(idFranja));
        model.addAttribute("franjaHoraria", franjaHoraria);

        List<Zona> zonesDisponibles = espaiPublicService.getZonesDisponibles(data, franjaHoraria, Integer.valueOf(idEspai));
        model.addAttribute("zones", zonesDisponibles);

        return "reserva/add";
    }

    @RequestMapping(value="/reservaFeta", method= RequestMethod.POST)
    public String ferReserva(@ModelAttribute("reservaDadesCompletes") ReservaDadesCompletes reservaDadesCompletes, BindingResult bindingResult, Model model, HttpSession session){
        System.out.println(reservaDadesCompletes);
        reservaDadesCompletes.setZonaDao(zonaDao);
        Validator reservaValidator = new ReservaValidator();
        reservaValidator.validate(reservaDadesCompletes, bindingResult);
        if(bindingResult.hasErrors()){

            if (bindingResult.hasErrors())
                System.out.println(bindingResult.getFieldError("nombrePersones").toString());
            model.addAttribute("reserva", reservaDadesCompletes);

            FranjaHoraria franjaHoraria = franjaHorariaDao.getFranjaHoraria(Integer.valueOf(reservaDadesCompletes.getIdFranja()));
            model.addAttribute("franjaHoraria", franjaHoraria);

            List<Zona> zonesDisponibles = espaiPublicService.getZonesDisponibles(reservaDadesCompletes.getDataReserva(), franjaHoraria, Integer.valueOf(reservaDadesCompletes.getIdEspai()));
            model.addAttribute("zones", zonesDisponibles);

            /*
            reserva = new ReservaDadesCompletes();
            reserva.setIdEspai(espai.getId());
            reserva.setEstat(EstatReserva.PENDENTUS);
            Ciutada ciutada = (Ciutada) session.getAttribute("ciutada");
            reserva.setNifCiutada(ciutada.getNif());
            reserva.setDataReserva(diaDate);
            model.addAttribute("reserva", reserva);

             */

            return "redirect:/reserva/add/" + reservaDadesCompletes.getIdEspai() + '/' + reservaDadesCompletes.getIdFranja() + '/' + reservaDadesCompletes.getDataReserva().toString();
        }

        Reserva reservaSimple = new Reserva();
        reservaSimple.setNombrePersones(reservaDadesCompletes.getNombrePersones());
        reservaSimple.setEstat(EstatReserva.PENDENTUS);
        Ciutada ciutada = (Ciutada) session.getAttribute("ciutada");
        reservaSimple.setNifCiutada(ciutada.getNif());

        String dadesCodiQr = String.valueOf(reservaDadesCompletes.getIdEspai()) + String.valueOf(reservaDadesCompletes.getIdFranja()) + String.valueOf(reservaDadesCompletes.getIdZona()) + reservaDadesCompletes.getDataReserva().toString();
        File f = new File("src/main/resources/static/imagenes/reserva" + dadesCodiQr + ".png");
        String data = "localhost:8080/reserva/" + dadesCodiQr;

        System.out.println(f.getPath());
        try {
            createQR(f, data, 300, 300);
            System.out.println("Codi QR creat");
            reservaSimple.setCodiQr("/imagenes/reserva" + dadesCodiQr + ".png");
            reservaDadesCompletes.setCodiQR("/imagenes/reserva" + dadesCodiQr + ".png");
        } catch (Exception e){
            System.out.println("No s'ha pogut crear el codi QR");
            reservaSimple.setCodiQr("");
            reservaDadesCompletes.setCodiQR("");
        }


        System.out.println(reservaDadesCompletes);
        System.out.println(reservaSimple);

        reservaDao.addReserva(reservaSimple);
        Reserva reservaGuardada = reservaDao.getReservaFromQR("/imagenes/reserva" + dadesCodiQr + ".png");

        Ocupa ocupa = new Ocupa();
        ocupa.setDataReserva(reservaDadesCompletes.getDataReserva());
        ocupa.setIdFranja(Integer.valueOf(reservaDadesCompletes.getIdFranja()));
        ocupa.setIdZona(Integer.valueOf(reservaDadesCompletes.getIdZona()));
        ocupa.setIdReserva(reservaGuardada.getId());
        ocupaDao.addOcupa(ocupa);

        reservaDadesCompletes.setEstat(EstatReserva.PENDENTUS);
        reservaDadesCompletes.setNifCiutada(ciutada.getNif());
        reservaDadesCompletes.setIdReserva(String.valueOf(reservaGuardada.getId()));

        EspaiPublic espaiPublic = espaiPublicDao.getEspaiPublic(Integer.valueOf(reservaDadesCompletes.getIdEspai()));
        model.addAttribute("nomEspai", espaiPublic.getNom());

        model.addAttribute("dades", reservaDadesCompletes);
        model.addAttribute("franjaHoraria", franjaHorariaDao.getFranjaHoraria(Integer.valueOf(reservaDadesCompletes.getIdFranja())));
        model.addAttribute("zona", zonaDao.getZona(Integer.valueOf(reservaDadesCompletes.getIdZona())));
        return "/reserva/reservaFeta";
    }

    private File createQR(File file, String data, int height, int width)
            throws Exception {
        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix matrix = writer.encode(data, com.google.zxing.BarcodeFormat.QR_CODE, width, height);

        BufferedImage image = new BufferedImage(matrix.getWidth(), matrix.getHeight(), BufferedImage.TYPE_INT_RGB);
        image.createGraphics();

        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, matrix.getWidth(), matrix.getHeight());
        graphics.setColor(Color.BLACK);

        for (int i = 0; i < matrix.getWidth(); i++) {
            for (int j = 0; j < matrix.getHeight(); j++) {
                if (matrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }

        ImageIO.write(image, "png", file);

        return file;
    }

    @RequestMapping(value = "/mostrarQR/{idReserva}/{idEspai}")
    public String mostrarCodiQR(@PathVariable String idReserva, @PathVariable int idEspai, Model model){
        System.out.println("Es va a mostrar el QR");

        ReservaDadesCompletes reservaDadesCompletes = reservesClientService.getReservaDadesCompletes(idReserva);
        FranjaHoraria franjaHoraria = franjaHorariaDao.getFranjaHoraria(Integer.valueOf(reservaDadesCompletes.getIdFranja()));
        EspaiPublic espaiPublic = espaiPublicDao.getEspaiPublic(idEspai);

        model.addAttribute("dades", reservaDadesCompletes);
        model.addAttribute("franjaHoraria", franjaHoraria);
        model.addAttribute("espai", espaiPublic);

        return "/reserva/mostrarQR";
    }

    @RequestMapping(value="/update/{id}", method = RequestMethod.GET)
    public String editReserva(Model model, @PathVariable int id){
        model.addAttribute("reserva", reservaDao.getReserva(id));
        return "reserva/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String processUpdateSubmit(@ModelAttribute("reserva") Reserva reserva, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "reserva/update";
        reservaDao.updateReserva(reserva);
        return "redirect:list";
    }

    @RequestMapping(value ="/deleteEspai/{idEspai}/{idReserva}")
    public String processDeleteEspai(@PathVariable int idReserva, @PathVariable int idEspai, Model model, HttpSession session, RedirectAttributes redirectAttributes){
        reservaDao.getReserva(idReserva).setEstat(EstatReserva.CANCELADAGESTORMUNICIPAL);
        String msg = String.format("Les dades de la reserva amb id " + reservaDao.getReserva(idReserva).getId() + " se ha cancelat correctament");
        redirectAttributes.addFlashAttribute("alert", msg);
        EspaiPublic espai = espaiPublicDao.getEspaiPublic(idEspai);
        model.addAttribute("espai", espai);
        model.addAttribute("reserves", reservesService.reservesPerEspai(idEspai));
        return "reserva/reservesEspai";
    }

    @RequestMapping(value ="/deletePerClient/{id}")
    public String processDeletePerClient(@PathVariable int id, Model model){
        reservaDao.getReserva(id).setEstat(EstatReserva.CANCELADACIUTADA);
        return "reserva/reservesClient";
    }
}
