package es.uji.ei102720gmtp.SanaProject.dao;

import es.uji.ei102720gmtp.SanaProject.model.ServeiPermanent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ServeiPermanentDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void addServeiPermanent(ServeiPermanent serveiPermanent) {
        jdbcTemplate.update("INSERT INTO ServeiPermanent VALUES(?, ?)",
                serveiPermanent.getNom(), serveiPermanent.getTipus());
    }

    public void deleteServeiPermanent(String nom) {
        jdbcTemplate.update("DELETE from ServeiPermanent where nom=?",
                nom);
    }

    public void deleteServeiPermanent(ServeiPermanent servei) {
        jdbcTemplate.update("DELETE from ServeiPermanent where nom=?",
                servei.getNom());
    }

    public void updateServeiPermanent(ServeiPermanent servei) {
        jdbcTemplate.update("UPDATE ServeiPermanent SET tipus=? WHERE nom = ?",
                servei.getTipus(), servei.getNom());
    }

    public ServeiPermanent getServeiPermanent(String nom) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM ServeiPermanent WHERE nom=?",
                    new ServeiPermanentRowMapper(),
                    nom);
        } catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<ServeiPermanent> getServeisPermanents() {
        try {
            return jdbcTemplate.query(
                    "SELECT * FROM ServeiPermanent",
                    new ServeiPermanentRowMapper()
            );
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<ServeiPermanent>();
        }
    }

    public List<ServeiPermanent> getServeisPermanentsFromEspai(int idEspai) {
        try {
            return jdbcTemplate.query(
                    "SELECT * FROM ServeiPermanent WHERE nom IN (SELECT nom_servei from serveiinstalatespai WHERE id_espai = ?)",
                    new ServeiPermanentRowMapper(),
                    idEspai);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<ServeiPermanent>();
        }
    }
}
