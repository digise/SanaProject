package es.uji.ei102720gmtp.SanaProject.dao;

import es.uji.ei102720gmtp.SanaProject.model.Municipi;
import es.uji.ei102720gmtp.SanaProject.model.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Repository
public class MunicipiDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /* Afegim el municipi */
    public void addMunicipi(Municipi municipi) {
        jdbcTemplate.update("INSERT INTO Municipi VALUES(?, ?, CAST(? AS tipus_provincia))",
                municipi.getIdMunicipi(), municipi.getNom(), municipi.getProvincia().name());
    }

    /* Esborrem el municipi amb el id*/
    public void deleteMunicipi(String idMunicipi) {
        jdbcTemplate.update("DELETE from Municipi where id=?",
                idMunicipi);
    }

    /* Esborrem el municipi */
    public void deleteMunicipi(Municipi municipi) {
        jdbcTemplate.update("DELETE from Municipi where id=?",
                municipi.getIdMunicipi());
    }

    /* Actualitzem els atributs del municipi
       (excepte el id, que és la clau primària) */
    public void updateMunicipi(Municipi municipi) {
        jdbcTemplate.update("UPDATE Municipi SET nom = ?, provincia = CAST(? AS tipus_provincia) WHERE id = ?",
                municipi.getNom(), municipi.getProvincia().name(), municipi.getIdMunicipi());
    }

    /* Obtenim el municipi amb el id. Torna null si no existeix. */
    public Municipi getMunicipi(String idMunicipi) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM Municipi WHERE id = ?",
                    new MunicipiRowMapper(),
                    idMunicipi);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    /* Obtenim tots els municipis. Torna una llista buida si no n'hi ha cap. */
    public List<Municipi> getMunicipis() {
        try {
            return jdbcTemplate.query(
                    "SELECT * FROM Municipi",
                    new MunicipiRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<Municipi>();
        }
    }
}
