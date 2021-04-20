package es.uji.ei102720gmtp.SanaProject.dao;

import es.uji.ei102720gmtp.SanaProject.model.ServeiInstalatEspai;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

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

    public void deleteServeiInstalatEspai(String idEspai, String nomServei) {
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

    public ServeiInstalatEspai getServeiInstalatEspai(String idEspai, String nomServei) {
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
}
