package es.uji.ei102720gmtp.SanaProject.dao;

import es.uji.ei102720gmtp.SanaProject.model.ServeiInstalatEspai;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ServeiInstalatEspaiDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void addServeiInstalatEspai(ServeiInstalatEspai serveiInstalatEspai) {
        jdbcTemplate.update("INSERT INTO ServeiInstalatEspai VALUES(?, ?, ?)",
                serveiInstalatEspai.getIdEspai(), serveiInstalatEspai.getNomServei(), serveiInstalatEspai.getDataApertura());
    }

    public void addServeiInstalatEspai(int id, String nom, LocalDate dataApertura) {
        jdbcTemplate.update("INSERT INTO ServeiInstalatEspai VALUES(?, ?, ?)",
                id, nom, dataApertura);
    }

    public void deleteServeiInstalatEspai(int idEspai, String nomServei) {
        jdbcTemplate.update("DELETE from ServeiInstalatEspai where id_espai=? and nom_servei=?",
                idEspai, nomServei);
    }

    public void deleteServeiInstalatEspai(ServeiInstalatEspai servei) {
        jdbcTemplate.update("DELETE from ServeiInstalatEspai where id_espai=? and nom_servei=?",
                servei.getIdEspai(), servei.getNomServei());
    }

    public void updateServeiInstalatEspai(ServeiInstalatEspai servei) {
        jdbcTemplate.update("UPDATE ServeiInstalatEspai SET data_apertura=? WHERE id_espai=? and nom_servei=?",
                servei.getDataApertura(), servei.getIdEspai(), servei.getNomServei());
    }

    public ServeiInstalatEspai getServeiInstalatEspai(int idEspai, String nomServei) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM ServeiInstalatEspai WHERE id_espai=? and nom_servei=?",
                    new ServeiInstalatEspaiRowMapper(),
                    idEspai, nomServei);
        } catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<ServeiInstalatEspai> getServeisPermanents() {
        try {
            return jdbcTemplate.query(
                    "SELECT * FROM ServeiInstalatEspai",
                    new ServeiInstalatEspaiRowMapper()
            );
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<ServeiInstalatEspai>();
        }
    }

    public List<ServeiInstalatEspai> getServeisInstalatsFromEspai(int idEspai) {
        try {
            return jdbcTemplate.query(
                    "SELECT * FROM ServeiInstalatEspai WHERE id_espai = ?",
                    new ServeiInstalatEspaiRowMapper(),
                    idEspai);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<ServeiInstalatEspai>();
        }
    }

    public List<ServeiInstalatEspai> getServeisInstalatsFromNom(String nom) {
        try {
            return jdbcTemplate.query(
                    "SELECT * FROM ServeiInstalatEspai WHERE nom_servei = ?",
                    new ServeiInstalatEspaiRowMapper(),
                    nom);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<ServeiInstalatEspai>();
        }
    }
}
