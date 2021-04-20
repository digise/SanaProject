package es.uji.ei102720gmtp.SanaProject.dao;


import es.uji.ei102720gmtp.SanaProject.model.Ocupa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OcupaDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /* Afegim el object ocupa */
    public void addOcupa(Ocupa ocupa) {
        jdbcTemplate.update("INSERT INTO Ocupa VALUES(?, ?)",
                ocupa.getIdReserva(), ocupa.getIdZona());
    }

    /* Esborrem l'objecte ocupa */
    public void deleteOcupa(String idReserva, String idZona) {
        jdbcTemplate.update("DELETE from Zona where id_reserva=? and id_zona=?",
                idReserva, idZona);
    }

    /* Esborrem l'objecte ocupa */
    public void deleteOcupa(Ocupa ocupa) {
        jdbcTemplate.update("DELETE from Ocupa where id_reserva=? and id_zona = ?",
                ocupa.getIdReserva(), ocupa.getIdZona());
    }


    /* Obtenim l'objecte ocupa amb el id_reserva, id_zona. Torna null si no existeix. */
    public Ocupa getOcupa(String idReserva, String idZona) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM Ocupa WHERE id_reserva = ? and id_zona = ?",
                    new OcupaRowMapper(),
                    idReserva, idZona);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    /* Obtenim tots els objectes ocupa. Torna una llista buida si no n'hi ha cap. */
    public List<Ocupa> getOcupes() {
        try {
            return jdbcTemplate.query(
                    "SELECT * FROM Ocupa",
                    new OcupaRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<Ocupa>();
        }
    }
}
