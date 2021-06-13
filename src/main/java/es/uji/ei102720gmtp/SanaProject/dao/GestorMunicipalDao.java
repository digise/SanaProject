package es.uji.ei102720gmtp.SanaProject.dao;

import es.uji.ei102720gmtp.SanaProject.model.GestorMunicipal;
import es.uji.ei102720gmtp.SanaProject.model.Municipi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GestorMunicipalDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /* Afegim el gestor municipal */
    public void addGestorMunicipal(GestorMunicipal gestorMunicipal) {
        jdbcTemplate.update("INSERT INTO GestorMunicipal VALUES(?, ?, ?, ?, ?, ?, ?)",
                gestorMunicipal.getNif(),gestorMunicipal.getIdMunicipi(), gestorMunicipal.getNom(),
                gestorMunicipal.getCognoms(), gestorMunicipal.getEmail(), gestorMunicipal.getTelefon(),
                gestorMunicipal.getContrasenya());
    }

    /* Esborrem el gestor amb el idMunicipi*/
    public void deleteGestorMunicipal(String nif) {
        jdbcTemplate.update("DELETE FROM GestorMunicipal WHERE nif=?",
                nif);
    }

    /* Esborrem el gestor */
    public void deleteGestorMunicipal(GestorMunicipal gestorMunicipal) {
        jdbcTemplate.update("DELETE FROM GestorMunicipal WHERE nif=?",
                gestorMunicipal.getNif());
    }

    /* Actualitzem els atributs del gestor
       (excepte el nif, que és la clau primària) */
    public void updateGestorMunicipal(GestorMunicipal gestorMunicipal) {
        jdbcTemplate.update("UPDATE GestorMunicipal SET id_municipi = ?, nom = ?, cognoms = ?, email = ?, telefon = ?, contrasenya = ? WHERE nif = ?",
                gestorMunicipal.getIdMunicipi(), gestorMunicipal.getNom(), gestorMunicipal.getCognoms(),
                gestorMunicipal.getEmail(), gestorMunicipal.getTelefon(),gestorMunicipal.getContrasenya(),
                gestorMunicipal.getNif());
    }

    /* Obtenim el gestor amb el nif. Torna null si no existeix. */
    public GestorMunicipal getGestorMunicipal(String nif) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM GestorMunicipal WHERE nif = ?",
                    new GestorMunicipalRowMapper(),
                    nif);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    /* Obtenim tots els gestors. Torna una llista buida si no n'hi ha cap. */
    public List<GestorMunicipal> getGestorsMunicipals() {
        try {
            return jdbcTemplate.query(
                    "SELECT * FROM GestorMunicipal",
                    new GestorMunicipalRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<GestorMunicipal>();
        }
    }
}
