package es.uji.ei102720gmtp.SanaProject.dao;

import es.uji.ei102720gmtp.SanaProject.model.Ciutada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CiutadaDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /* Afegim el ciutada */
    public void addCiutada(Ciutada ciutada) {
        jdbcTemplate.update("INSERT INTO Ciutada VALUES(?, ?, ?, ?, ?, ? ,? ,?, ?)",
                ciutada.getNif(), ciutada.getNom(), ciutada.getCognoms(), ciutada.getTelefon(), ciutada.getEmail(), ciutada.getDomicili(), ciutada.getLocalitat(), ciutada.getPais(), ciutada.getContrasenya());
    }

    /* Esborrem el ciutada */
    public void deleteCiutada(String nifCiutada) {
        jdbcTemplate.update("DELETE from Ciutada where id=?",
                nifCiutada);
    }

    /* Esborrem la reserva */
    public void deleteCiutada(Ciutada ciutada) {
        jdbcTemplate.update("DELETE from Ciutada where id=?",
                ciutada.getNif());
    }

    /* Actualitzem els atributs del ciutada
       (excepte el nom, que és la clau primària) */
    public void updateReserva(Ciutada ciutada) {
        jdbcTemplate.update("UPDATE Ciutada SET nom = ?, cognoms = ?, telefon = ?, email = ?, domicili = ?, localitat = ?, pais = ?, pin = ? WHERE nif = ?",
                ciutada.getNom(), ciutada.getCognoms(), ciutada.getTelefon(), ciutada.getEmail(), ciutada.getDomicili(), ciutada.getLocalitat(), ciutada.getPais(), ciutada.getContrasenya(), ciutada.getNif());
    }

    /* Obtenim el ciutada amb el nif. Torna null si no existeix. */
    public Ciutada getCiutada(String nifCiutada) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM Ciutada WHERE nif = ?",
                    new CiutadaRowMapper(),
                    nifCiutada);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    /* Obtenim tots els ciutadans. Torna una llista buida si no n'hi ha cap. */
    public List<Ciutada> getCiutadans() {
        try {
            return jdbcTemplate.query(
                    "SELECT * FROM Ciutada",
                    new CiutadaRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<Ciutada>();
        }
    }

}
