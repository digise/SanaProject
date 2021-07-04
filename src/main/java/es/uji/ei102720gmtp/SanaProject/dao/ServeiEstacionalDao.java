package es.uji.ei102720gmtp.SanaProject.dao;

import es.uji.ei102720gmtp.SanaProject.model.ServeiEstacional;
import es.uji.ei102720gmtp.SanaProject.model.ServeiPermanent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ServeiEstacionalDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void addServeiEstacional(ServeiEstacional serveiEstacional) {
        jdbcTemplate.update("INSERT INTO ServeiEstacional VALUES(?, ?)",
                serveiEstacional.getNom(), serveiEstacional.getTipus());
    }

    public void deleteServeiEstacional(String nom) {
        jdbcTemplate.update("DELETE from ServeiEstacional where nom=?",
                nom);
    }

    public void deleteServeiEstacional(ServeiEstacional servei) {
        jdbcTemplate.update("DELETE from ServeiEstacional where nom=?",
                servei.getNom());
    }

    public void updateServeiEstacional(ServeiEstacional servei) {
        jdbcTemplate.update("UPDATE ServeiEstacional SET tipus=? WHERE nom=?",
                servei.getTipus(), servei.getNom());
    }

    public ServeiEstacional getServeiEstacional(String nom) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM ServeiEstacional WHERE nom=?",
                    new ServeiEstacionalRowMapper(),
                    nom);
        } catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<ServeiEstacional> getServeisEstacionals() {
        try {
            return jdbcTemplate.query(
                    "SELECT * FROM ServeiEstacional",
                    new ServeiEstacionalRowMapper()
            );
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<ServeiEstacional>();
        }
    }

    public List<ServeiEstacional> getServeisEstacionalsFromEspai(int idEspai) {
        try {
            return jdbcTemplate.query(
                    "SELECT * FROM ServeiEstacional WHERE nom IN (SELECT nom_servei from periodeserveiespai WHERE id_espai = ?)",
                    new ServeiEstacionalRowMapper(),
                    idEspai);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<ServeiEstacional>();
        }
    }
}
