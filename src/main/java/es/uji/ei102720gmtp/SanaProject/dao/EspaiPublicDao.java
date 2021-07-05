package es.uji.ei102720gmtp.SanaProject.dao;


import es.uji.ei102720gmtp.SanaProject.model.EspaiPublic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EspaiPublicDao
{
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /* Afegim */
    public void addEspaiPublic(EspaiPublic espaiPublic) {
        jdbcTemplate.update("INSERT INTO espaiPublic (id_municipi, nom, tipus, terreny, tipus_acces, localitzacio, longitud, amplaria, imagen, descripcio) VALUES(?, ?, ?, CAST(? AS tipus_sol), CAST(? AS tipus_acces), ?, ?, ?, ?, ?)",
                espaiPublic.getIdMunicipi(), espaiPublic.getNom(), espaiPublic.getTipus(), espaiPublic.getTipusTerreny().name(), espaiPublic.getTipusAcces().name(),
                espaiPublic.getLocalitzacio(), espaiPublic.getLongitud(), espaiPublic.getAmplaria(),
                espaiPublic.getImagen(), espaiPublic.getDescripcio());
    }

    /* Esborrem  */
    public void deleteEspaiPublic(int idEspai) {
        jdbcTemplate.update("DELETE from espaiPublic where id=?",
                idEspai);
    }

    /* Actualitzem els atributs
       (excepte el id, que és la clau primària) */
    public void updateEspaiPublic(EspaiPublic espaiPublic) {
        jdbcTemplate.update("UPDATE espaiPublic SET id_municipi = ?, nom = ?, tipus = ?, terreny = CAST(? AS tipus_sol), tipus_acces = CAST(? AS tipus_acces), localitzacio = ?, longitud = ? , amplaria = ?, imagen = ?, descripcio = ? WHERE id = ?",
                espaiPublic.getIdMunicipi(), espaiPublic.getNom(), espaiPublic.getTipus(),
                espaiPublic.getTipusTerreny().name(), espaiPublic.getTipusAcces().name(), espaiPublic.getLocalitzacio(),
                espaiPublic.getLongitud(), espaiPublic.getAmplaria(), espaiPublic.getImagen(),
                espaiPublic.getDescripcio(), espaiPublic.getId());
        System.out.println(espaiPublic.toString());
    }

    public void updateNomEspaiPublic(String nom, EspaiPublic espaiPublic){
        jdbcTemplate.update("UPDATE espaiPublic SET nom = ? WHERE id = ?",
                nom, espaiPublic.getId());

    }

    /* Obtenim el espai amb el id. Torna null si no existeix. */
    public EspaiPublic getEspaiPublic(int idEspai) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM espaiPublic WHERE id = ?",
                    new EspaiPublicRowMapper(),
                    idEspai);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    /* Obtenim totes les reserves. Torna una llista buida si no n'hi ha cap. */
    public List<EspaiPublic> getEspaisPublics() {
        try {
            return jdbcTemplate.query(
                    "SELECT * FROM espaiPublic",
                    new EspaiPublicRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<EspaiPublic>();
        }
    }

    public List<EspaiPublic> getEspaisPublicsFromMunicipi(int idMunicipi) {
        try {
            return jdbcTemplate.query(
                    "SELECT * FROM espaiPublic WHERE id_municipi = ?",
                    new EspaiPublicRowMapper(),
                    idMunicipi);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<EspaiPublic>();
        }
    }


}

