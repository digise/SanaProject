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
        jdbcTemplate.update("INSERT INTO espai_public VALUES(?, ?, ?, ?, ?, ? ,? , ?, ?, ?, ?, ?)",
                espaiPublic.getId(),espaiPublic.getIdMunicipi(),espaiPublic.getNom(),espaiPublic.getTipus(),espaiPublic.getTerreny(),espaiPublic.getTipusAcces(),espaiPublic.getLocalitzacio(),espaiPublic.getLongitud(),espaiPublic.getAmplaria(),espaiPublic.getImagen(),espaiPublic.getComentaris(),espaiPublic.getDescripcio());
    }

    /* Esborrem  */
    public void deleteEspaiPublic(String idEspai) {
        jdbcTemplate.update("DELETE from espai_public where id=?",
                idEspai);
    }

    /* Actualitzem els atributs
       (excepte el id, que és la clau primària) */
    public void updateEspaiPublic(EspaiPublic espaiPublic) {
        jdbcTemplate.update("UPDATE espai_public SET id_municipi = ?, nom = ?, tipus = ?, terreny = ?, tipus_acces = ?, localitzacio = ?, longitud = ? , amplaria = ?, imagen = ?, comentaris = ?, descripcio = ? WHERE id = ?",
                espaiPublic.getIdMunicipi(),espaiPublic.getNom(),espaiPublic.getTipus(),espaiPublic.getTerreny(),espaiPublic.getTipusAcces(),espaiPublic.getLocalitzacio(),espaiPublic.getLongitud(),espaiPublic.getAmplaria(),espaiPublic.getImagen(),espaiPublic.getComentaris(),espaiPublic.getDescripcio());;
    }

    /* Obtenim el espai amb el id. Torna null si no existeix. */
    public EspaiPublic getEspaiPublic(String idEspai) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM espai_public WHERE id = ?",
                    new EspaiPublicRowMapper(),
                    idEspai);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    /* Obtenim totes les reserves. Torna una llista buida si no n'hi ha cap. */
    public List<EspaiPublic> getReserves() {
        try {
            return jdbcTemplate.query(
                    "SELECT * FROM espai_public",
                    new EspaiPublicRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<EspaiPublic>();
        }
    }
}

