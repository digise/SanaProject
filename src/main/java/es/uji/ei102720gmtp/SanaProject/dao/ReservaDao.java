package es.uji.ei102720gmtp.SanaProject.dao;

import es.uji.ei102720gmtp.SanaProject.model.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReservaDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /* Afegim la reserva */
    public void addReserva(Reserva reserva) {
        jdbcTemplate.update("INSERT INTO Reserva (codi_qr, nombre_persones, estat, data_reserva, id_franja, nif_ciutada) VALUES(?, ?, CAST(? AS estat_reserva), ?, ?, ?)",
                reserva.getCodiQr(), reserva.getNombrePersones(), reserva.getEstat().name(), reserva.getDataReserva(), reserva.getIdFranja(), reserva.getNifCiutada());
    }

    /* Esborrem la reserva */
    public void deleteReserva(int idReserva) {
        jdbcTemplate.update("DELETE from Reserva where id=?",
                idReserva);
    }

    /* Esborrem la reserva */
    public void deleteReserva(Reserva reserva) {
        jdbcTemplate.update("DELETE from Reserva where id=?",
                reserva.getId());
    }

    /* Actualitzem els atributs de la reserva
       (excepte el nom, que és la clau primària) */
    public void updateReserva(Reserva reserva) {
        jdbcTemplate.update("UPDATE Reserva SET codi_qr = ?, nombre_persones = ?, estat = CAST(? AS estat_reserva), data_reserva = ?, id_franja = ?, nif_ciutada = ? WHERE id = ?",
                reserva.getCodiQr(), reserva.getNombrePersones(), reserva.getEstat().name(), reserva.getDataReserva(), reserva.getIdFranja(), reserva.getNifCiutada(), reserva.getId());
    }


    /* Obtenim la reserva amb el id. Torna null si no existeix. */
    public Reserva getReserva(int idReserva) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM reserva WHERE id = ?",
                    new ReservaRowMapper(),
                    idReserva);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Reserva getReservaFromQR(String codiQR) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM reserva WHERE codi_qr = ?",
                    new ReservaRowMapper(),
                    codiQR);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    /* Obtenim totes les reserves. Torna una llista buida si no n'hi ha cap. */
    public List<Reserva> getReserves() {
        try {
            return jdbcTemplate.query(
                    "SELECT * FROM Reserva",
                    new ReservaRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<Reserva>();
        }
    }

    public List<Reserva> getReservesClient(String nifCiutada) {
        try{
            return jdbcTemplate.query(
                    "SELECT * FROM Reserva WHERE nif_ciutada = ?",
                    new ReservaRowMapper(), nifCiutada);
        }catch (EmptyResultDataAccessException e) {
            return new ArrayList<Reserva>();
        }
    }



    public List<Reserva> getReservesPerEspai(int idEspai) {
        try{
            return jdbcTemplate.query(
                    "SELECT * FROM Reserva WHERE id_franja IN (" +
                            "SELECT id FROM FranjaHoraria WHERE id_espai = ?)",
                    new ReservaRowMapper(), idEspai);
        }catch (EmptyResultDataAccessException e) {
            return new ArrayList<Reserva>();
        }
    }
    public List<Reserva> getReservesPerFranges(int idFranja){
        try{
            return jdbcTemplate.query(
                    "SELECT * FROM Reserva WHERE id_franja = ?",
                    new ReservaRowMapper(), idFranja);
        }catch (EmptyResultDataAccessException e) {
            return new ArrayList<Reserva>();
        }
    }


}
