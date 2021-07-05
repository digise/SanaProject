package es.uji.ei102720gmtp.SanaProject.model;

import es.uji.ei102720gmtp.SanaProject.dao.EspaiPublicDao;
import es.uji.ei102720gmtp.SanaProject.dao.MunicipiDao;
import es.uji.ei102720gmtp.SanaProject.dao.ZonaDao;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ReservaTablas extends Reserva implements Comparable<ReservaTablas>{

    private int idEspai;
    private List<Integer> zones;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime horaInici;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime horaFinal;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataReserva;
    private Reserva reserva;
    private String nomEspai;
    private String nomMunicipi;

    public ReservaTablas(Reserva reserva, int idEspai, String nomEspai, String nomMunicipi, List<Integer> zones, LocalTime horaInici, LocalTime horaFinal, LocalDate dataReserva){
        this.reserva = reserva;
        this.idEspai = idEspai;
        this.nomEspai = nomEspai;
        this.nomMunicipi = nomMunicipi;
        this.zones = zones;
        this.horaInici = horaInici;
        this.horaFinal = horaFinal;
        this.dataReserva = dataReserva;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public int getIdEspai() {
        return idEspai;
    }

    public void setIdEspai(int idEspai) {
        this.idEspai = idEspai;
    }

    public List<Integer> getZones() {
        return zones;
    }
    public String mostrarZones(){
        StringBuilder sbZones = new StringBuilder();
        for (int i = 0; i < zones.size() - 1; i++)
            sbZones.append(zones.get(i)).append(", ");
        sbZones.append(zones.get(zones.size() - 1));
        return sbZones.toString();
    }

    public void setZones(List<Integer> zones) {
        this.zones = zones;
    }

    public LocalTime getHoraInici() {
        return horaInici;
    }

    public void setHoraInici(LocalTime horaInici) {
        this.horaInici = horaInici;
    }

    public LocalTime getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(LocalTime horaFinal) {
        this.horaFinal = horaFinal;
    }

    public LocalDate getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(LocalDate dataReserva) {
        this.dataReserva = dataReserva;
    }

    private EspaiPublicDao espaiPublicDao;

    private MunicipiDao municipiDao;

    public EspaiPublicDao getEspaiPublicDao() {
        return espaiPublicDao;
    }

    public void setEspaiPublicDao(EspaiPublicDao espaiPublicDao) {
        this.espaiPublicDao = espaiPublicDao;
    }

    public String getNomEspai() {
        return nomEspai;
    }

    public void setNomEspai(String nomEspai) {
        this.nomEspai = nomEspai;
    }

    public MunicipiDao getMunicipiDao() {
        return municipiDao;
    }

    public void setMunicipiDao(MunicipiDao municipiDao) {
        this.municipiDao = municipiDao;
    }

    public String nomEspaiCiutadaReserva(int idEspai){
        String nomEspai = espaiPublicDao.getEspaiPublic(idEspai).getNom();
        return nomEspai;
    }

    public String nomLocalitzacioCiutadaReserva(int idEspai){
        String localitzacio = municipiDao.getMunicipi(espaiPublicDao.getEspaiPublic(idEspai).getIdMunicipi()).getNom() + ", " + municipiDao.getMunicipi(espaiPublicDao.getEspaiPublic(idEspai).getIdMunicipi()).getProvincia().getDescripcion();
        return localitzacio;
    }

    @Override
    public int compareTo(ReservaTablas altraReserva) {
        return this.getDataReserva().compareTo(altraReserva.getDataReserva()) + this.getHoraInici().compareTo(altraReserva.getHoraInici());
    }


    @Override
    public String toString() {
        return "ReservaTablas{" +
                "idEspai=" + idEspai +
                ", zones=" + zones +
                ", horaInici=" + horaInici +
                ", horaFinal=" + horaFinal +
                ", dataReserva=" + dataReserva +
                ", reserva=" + reserva +
                ", nomEspai='" + nomEspai + '\'' +
                ", nomMunicipi='" + nomMunicipi + '\'' +
                ", espaiPublicDao=" + espaiPublicDao +
                ", municipiDao=" + municipiDao +
                '}';
    }
}
