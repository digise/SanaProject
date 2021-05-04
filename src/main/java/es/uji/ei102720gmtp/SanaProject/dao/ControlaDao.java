package es.uji.ei102720gmtp.SanaProject.dao;


import es.uji.ei102720gmtp.SanaProject.model.Controla;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;

@Repository
public class ControlaDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /* Afegim  */
    public void addControla(Controla controla)
    {
        jdbcTemplate.update("INSERT INTO Controla VALUES(?, ?, ?, ?)",
                controla.getNifControlador(), controla.getIdEspai(), controla.getDataInici(), controla.getDataFinal());
    }

    /* Esborrem  */
    public void deleteControla(String nifControlador,String idEspai,LocalDate dataInici) {
        jdbcTemplate.update("DELETE from Controla where nif_controlador=? AND id_espai=? AND data_inici=?",
                nifControlador,idEspai,dataInici);
    }

    /* Actualitzem els atributs  */
    public void updateControla(Controla controla) {
        jdbcTemplate.update("UPDATE Controla SET data_final = ? WHERE nif_controlador = ? AND id_espai = ? AND data_inici = ?",
                controla.getDataFinal(), controla.getNifControlador(),controla.getIdEspai(),controla.getDataInici());
    }

    /* Obtenim  amb el id. Torna null si no existeix. */
    public Controla getControla(String nifControlador, String idEspai, LocalDate dataInici) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM Controla WHERE nif_controlador=? AND id_espai=? AND data_inici=?",
                    new ControlaRowMapper(),
                    nifControlador,idEspai,dataInici);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    /* Obtenim lista de controla */
    public ArrayList<Controla> getlistControla() {
        try {
            return (ArrayList<Controla>) jdbcTemplate.query(
                    "SELECT * FROM Controla",
                    new ControlaRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<Controla>();
        }
    }
}

