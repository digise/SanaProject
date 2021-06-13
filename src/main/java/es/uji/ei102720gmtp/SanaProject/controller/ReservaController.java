package es.uji.ei102720gmtp.SanaProject.controller;

import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import es.uji.ei102720gmtp.SanaProject.Validation.ReservaValidator;
import es.uji.ei102720gmtp.SanaProject.dao.EspaiPublicDao;
import es.uji.ei102720gmtp.SanaProject.dao.OcupaDao;
import es.uji.ei102720gmtp.SanaProject.dao.ReservaDao;
import es.uji.ei102720gmtp.SanaProject.dao.ZonaDao;
import es.uji.ei102720gmtp.SanaProject.model.*;
import es.uji.ei102720gmtp.SanaProject.model.enums.EstatReserva;
import es.uji.ei102720gmtp.SanaProject.services.EspaiPublicService;
import es.uji.ei102720gmtp.SanaProject.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
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

    //Operacions: Crear, llistar, actualitzar, esborrar

    @RequestMapping("/list")
    public String listReserves(Model model){
        model.addAttribute("reserves", reservaDao.getReserves());
        return "reserva/list";
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

    @RequestMapping(value="/add", method= RequestMethod.POST)
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

        String data = "localhost:8080/reserva/" + reserva.getIdEspai() + reserva.getIdFranja()
                + reserva.getIdZona() + reserva.getDataReserva().toString();
        String path = "imagenes/reserva" + reserva.getIdEspai() + reserva.getIdFranja()
                + reserva.getIdZona() + reserva.getDataReserva().toString() + ".png";
        String charset= "UTF-8";
        Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        try {
            createQR(data, path, charset, hashMap, 200, 200);
            reservaSimple.setCodiQr(path);
        } catch (Exception e){
            System.out.println("No s'ha pogut crear el codi QR");
            path = "";
            reservaSimple.setCodiQr(path);
        }

        reservaDao.addReserva(reservaSimple);
        Reserva reservaGuardada = reservaDao.getReservaFromQR(path);

        Ocupa ocupa = new Ocupa();
        ocupa.setDataReserva(reserva.getDataReserva());
        ocupa.setIdFranja(reserva.getIdFranja());
        ocupa.setIdZona(reserva.getIdZona());
        ocupa.setIdReserva(reservaGuardada.getId());
        ocupaDao.addOcupa(ocupa);

        return "redirect:/espaiPublic/seleccionarProvincia";
    }

    private static void createQR(String data, String path, String charset, Map hashMap, int height, int width)
            throws WriterException, IOException, NotFoundException {
        BitMatrix matrix = new MultiFormatWriter().encode(
                new String(data.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, width, height);

        MatrixToImageWriter.writeToFile(
                matrix,
                path.substring(path.lastIndexOf('.') + 1),
                new File(path));
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
