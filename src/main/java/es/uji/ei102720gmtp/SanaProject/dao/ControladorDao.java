package es.uji.ei102720gmtp.SanaProject.dao;

import es.uji.ei102720gmtp.SanaProject.model.Controlador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;

@Repository
public class ControladorDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /* Afegim el controlador */
    public void addControlador(Controlador controlador) {
        jdbcTemplate.update("INSERT INTO Controlador VALUES(?, ?, ?, ?, ?, ?)",
                controlador.getNif(), controlador.getNom(), controlador.getCognoms(), controlador.getEmail(), controlador.getTelefon(), controlador.getContrasenya());
    }

    /* Esborrem el controlador */
    public void deleteControlador(String nif) {
        jdbcTemplate.update("DELETE from Controlador where nif=?",
                nif);
    }

    /* Esborrem el controlador */
    public void deleteControlador(Controlador controlador) {
        jdbcTemplate.update("DELETE from Controlador where nif=?",
                controlador.getNif());
    }

    //Actualitzem els atributs del controlador

    public void updateControlador(Controlador controlador) {
        jdbcTemplate.update("UPDATE Controlador SET nom = ?, cognoms = ?, email = ?, telefon = ?, contrasenya = ? WHERE nif=?",
                controlador.getNom(), controlador.getCognoms(), controlador.getEmail(), controlador.getTelefon(), controlador.getContrasenya(), controlador.getNif());
    }

    /* Obtenim el controlador amb el nif. Torna null si no existeix. */
    public Controlador getControlador (String nifControlador) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM Controlador WHERE nif = ?",
                    new ControladorRowMapper(),
                    nifControlador);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    /* Obtenim tote els controladors. Torna una llista buida si no n'hi ha cap. */
    public ArrayList<Controlador> getlistControladors() {
        try {
            return (ArrayList<Controlador>) jdbcTemplate.query(
                    "SELECT * FROM Controlador",
                    new ControladorRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<Controlador>();
        }
    }
}



