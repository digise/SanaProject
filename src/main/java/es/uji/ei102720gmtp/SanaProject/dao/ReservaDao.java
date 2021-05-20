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
        jdbcTemplate.update("INSERT INTO Reserva VALUES(?, ?, ?, ?, ? ,?)",
                reserva.getCodiQr(), reserva.getNombrePersones(), reserva.getDataCreacio(), reserva.getEstat(), reserva.getNifCiutada(), reserva.getIdFranja());
    }

    /* Esborrem la reserva */
    public void deleteReserva(String idReserva) {
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
        jdbcTemplate.update("UPDATE Reserva SET codi_qr = ?, nombre_persones = ?, data_creacio = ?, estat = ?, nif_ciutada = ?, id_franja = ? WHERE id = ?",
                reserva.getCodiQr(), reserva.getNombrePersones(), reserva.getDataCreacio(), reserva.getEstat(), reserva.getNifCiutada(), reserva.getIdFranja(), reserva.getId());
    }

    /* Obtenim la reserva amb el id. Torna null si no existeix. */
    public Reserva getReserva(String idReserva) {
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
}
