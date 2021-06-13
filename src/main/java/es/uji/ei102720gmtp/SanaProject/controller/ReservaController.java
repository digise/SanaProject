package es.uji.ei102720gmtp.SanaProject.controller;

import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import es.uji.ei102720gmtp.SanaProject.Validation.ReservaValidator;
import es.uji.ei102720gmtp.SanaProject.dao.*;
import es.uji.ei102720gmtp.SanaProject.model.*;
import es.uji.ei102720gmtp.SanaProject.model.enums.EstatReserva;
import es.uji.ei102720gmtp.SanaProject.services.EspaiPublicService;
import es.uji.ei102720gmtp.SanaProject.services.ReservaService;
import es.uji.ei102720gmtp.SanaProject.services.ReservesEspaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
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
    private ReservesEspaiService reservesEspaiService;

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
    public void setOcupaDao(OcupaDao ocupaDao) {
        this.ocupaDao = ocupaDao;
    }

    @Autowired
    public void setFranjaHorariaDao(FranjaHorariaDao franjaHorariaDao) {
        this.franjaHorariaDao = franjaHorariaDao;
    }

    @Autowired
    public void setReservesEspaiService(ReservesEspaiService reservesEspaiService){
        this.reservesEspaiService = reservesEspaiService;
    }

    //Operacions: Crear, llistar, actualitzar, esborrar

    @RequestMapping("/list")
    public String listReserves(Model model){
        model.addAttribute("reserves", reservaDao.getReserves());
        return "reserva/list";
    }

    @RequestMapping("/mostrarReserves/{id}")
    public String mostrarReserves(Model model, @PathVariable int id){
        model.addAttribute("reserves", reservaDao.getReserves());
        reservesEspaiService.reservesPerEspai(id);
        model.addAttribute("espai", espaiPublicDao.getEspaiPublic(id));
        return "reserva/mostrarReserves";
    }

    /*
    @RequestMapping(value = "/add/{idZona}")
    public String addReserva(Model model, @PathVariable int idZona){

        Zona zona = zonaDao.getZona(idZona);

        EspaiPublic espaiPublic = espaiPublicDao.getEspaiPublic(zona.getIdEspai());
        model.addAttribute("nom", espaiPublic.getNom());

        //List<FranjaHoraria> frangesDisponibles = reservaService.getFrangesHorariesDisponibles();

        model.addAttribute("reserva", new Reserva());
        return "reserva/add";
    }

     */

    @RequestMapping(value="/reservaFeta", method= RequestMethod.POST)
    public String ferReserva(@ModelAttribute("reserva") ReservaDadesCompletes reserva, BindingResult bindingResult, Model model, HttpSession session){
        System.out.println(reserva);
        reserva.setZonaDao(zonaDao);
        ReservaValidator reservaValidator = new ReservaValidator();
        reservaValidator.validate(reserva, bindingResult);
        if(bindingResult.hasErrors()){

            EspaiPublic espai = espaiPublicDao.getEspaiPublic(reserva.getIdEspai());
            model.addAttribute("espai", espai);

            List<FranjaHoraria> frangesHoraries = espaiPublicService.getFrangesHoraries(espai.getId());
            model.addAttribute("franges", frangesHoraries);

            LocalDate diaDate = reserva.getDataReserva();
            model.addAttribute("dia", diaDate);

            Map<Integer, List<Zona>> zonesDisponibles = espaiPublicService.getZonesDisponibles(diaDate, frangesHoraries, espai.getId());
            model.addAttribute("zones", zonesDisponibles);

            reserva = new ReservaDadesCompletes();
            reserva.setIdEspai(espai.getId());
            reserva.setEstat(EstatReserva.PENDENTUS);
            Ciutada ciutada = (Ciutada) session.getAttribute("ciutada");
            reserva.setNifCiutada(ciutada.getNif());
            reserva.setDataReserva(diaDate);
            model.addAttribute("reserva", reserva);

            ElegirZonaBean novesDades = new ElegirZonaBean(espai.getId(), reserva.getDataReserva());
            model.addAttribute("dades", novesDades);
            return "redirect:/espaiPublic/elegirZona";
        }

        Reserva reservaSimple = new Reserva();
        reservaSimple.setNombrePersones(reserva.getNombrePersones());
        reservaSimple.setEstat(reserva.getEstat());
        reservaSimple.setNifCiutada(reserva.getNifCiutada());

        String dadesCodiQr = String.valueOf(reserva.getIdEspai()) + String.valueOf(reserva.getIdFranja()) + String.valueOf(reserva.getIdZona()) + reserva.getDataReserva().toString();
        File f = new File("src/main/resources/static/imagenes/reserva" + dadesCodiQr + ".png");
        String data = "localhost:8080/reserva/" + dadesCodiQr;

        System.out.println(f.getPath());
        try {
            createQR(f, data, 300, 300);
            System.out.println("Codi QR creat");
            reservaSimple.setCodiQr("imagenes/reserva" + dadesCodiQr + ".png");
        } catch (Exception e){
            System.out.println("No s'ha pogut crear el codi QR");
            reservaSimple.setCodiQr("");
        }


        reservaDao.addReserva(reservaSimple);
        Reserva reservaGuardada = reservaDao.getReservaFromQR("imagenes/reserva" + dadesCodiQr + ".png");

        Ocupa ocupa = new Ocupa();
        ocupa.setDataReserva(reserva.getDataReserva());
        ocupa.setIdFranja(reserva.getIdFranja());
        ocupa.setIdZona(reserva.getIdZona());
        ocupa.setIdReserva(reservaGuardada.getId());

        ocupaDao.addOcupa(ocupa);

        model.addAttribute("dades", reserva);
        model.addAttribute("franjaHoraria", franjaHorariaDao.getFranjaHoraria(reserva.getIdFranja()));
        model.addAttribute("zona", zonaDao.getZona(reserva.getIdZona()));
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

    @RequestMapping(value = "/delete/{id}")
    public String processDelete(@PathVariable int id){
        reservaDao.deleteReserva(id);
        return "redirect:../list";
    }
}
