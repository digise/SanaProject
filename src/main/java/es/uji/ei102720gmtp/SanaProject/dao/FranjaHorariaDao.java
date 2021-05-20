package es.uji.ei102720gmtp.SanaProject.dao;

import es.uji.ei102720gmtp.SanaProject.model.FranjaHoraria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FranjaHorariaDao{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /* Afegim  */
    public void addFranjaHoraria(FranjaHoraria franjaHoraria) {
        jdbcTemplate.update("INSERT INTO FranjaHoraria VALUES(?, ?, ?, ?, ? ,?)",
                franjaHoraria.getIdEspai(), franjaHoraria.getDescripcio(),
                franjaHoraria.getHoraInici(), franjaHoraria.getHoraFinal(), franjaHoraria.getDataInici(),
                franjaHoraria.getDataFinal());
    }

    /* Esborrem */
    public void deleteFranjaHoraria(String idFranja) {
        jdbcTemplate.update("DELETE from franjaHoraria where id=?",
                idFranja);
    }

    /* Actualitzem els atributs
       (excepte el id, que és la clau primària) */
    public void updateFranjaHoraria(FranjaHoraria franjaHoraria) {
        jdbcTemplate.update("UPDATE franjaHoraria SET id_espai = ?, descripcio = ?, hora_inici = ?, hora_final = ?, data_inici = ?, data_final = ?  WHERE id = ?",
                franjaHoraria.getIdEspai(), franjaHoraria.getDescripcio(), franjaHoraria.getHoraInici(),
                franjaHoraria.getHoraFinal(), franjaHoraria.getDataInici(), franjaHoraria.getDataFinal(),
                franjaHoraria.getId());
    }

    /* Obtenim la franja amb el id. Torna null si no existeix. */
    public FranjaHoraria getFranjaHoraria(String idFranja) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM franjaHoraria WHERE id = ?",
                    new FranjaHorariaRowMapper(),
                    idFranja);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    /* Obtenim totes les franjes. Torna una llista buida si no n'hi ha cap. */
    public List<FranjaHoraria> getFranjas() {
        try {
            return jdbcTemplate.query(
                    "SELECT * FROM franjaHoraria",
                    new FranjaHorariaRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<FranjaHoraria>();
        }
    }
}

