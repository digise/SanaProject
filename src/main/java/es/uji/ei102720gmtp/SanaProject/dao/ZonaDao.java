package es.uji.ei102720gmtp.SanaProject.dao;

import es.uji.ei102720gmtp.SanaProject.model.Ciutada;
import es.uji.ei102720gmtp.SanaProject.model.Zona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ZonaDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /* Afegim la zona */
    public void addZona(Zona zona) {
        jdbcTemplate.update("INSERT INTO Zona VALUES(?, ?, ?, ?, ?)",
                zona.getId(), zona.getIdEspai(), zona.getNom(), zona.getCapacitat(), zona.getCoordenades());
    }

    /* Esborrem la zona */
    public void deleteZona(String idZona) {
        jdbcTemplate.update("DELETE from Zona where id=?",
                idZona);
    }

    /* Esborrem la zona */
    public void deleteZona(Zona zona) {
        jdbcTemplate.update("DELETE from Zona where id=?",
                zona.getId());
    }

    /* Actualitzem els atributs de la zona
       (excepte el nom, que és la clau primària) */
    public void updateZona(Zona zona) {
        jdbcTemplate.update("UPDATE Zona SET id_espai = ?, nom = ?, capacitat = ?, coordenades = ? WHERE id = ?",
                zona.getIdEspai(), zona.getNom(), zona.getCapacitat(), zona.getCoordenades(), zona.getId());
    }

    /* Obtenim la zona amb el id. Torna null si no existeix. */
    public Zona getZona(String idZona) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM Zona WHERE id = ?",
                    new ZonaRowMapper(),
                    idZona);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    /* Obtenim tots les zones. Torna una llista buida si no n'hi ha cap. */
    public List<Zona> getZones() {
        try {
            return jdbcTemplate.query(
                    "SELECT * FROM Zona",
                    new ZonaRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<Zona>();
        }
    }
}
