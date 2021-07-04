package es.uji.ei102720gmtp.SanaProject.dao;

import es.uji.ei102720gmtp.SanaProject.model.PeriodeServeiEspai;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PeriodeServeiEspaiDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void addPeriodeServeiEspai(PeriodeServeiEspai periodeServeiEspai) {
        jdbcTemplate.update("INSERT INTO PeriodeServeiEspai VALUES(?, ?, ?, ?, ?, ?)",
                periodeServeiEspai.getIdEspai(), periodeServeiEspai.getNomServei(), periodeServeiEspai.getHoraInici(),
                periodeServeiEspai.getHoraFinal(), periodeServeiEspai.getDataInici(), periodeServeiEspai.getDataFinal());
    }

    public void deletePeriodeServeiEspai(int idEspai, String nomServei) {
        jdbcTemplate.update("DELETE from PeriodeServeiEspai where id_espai=? and nom_servei=?",
                idEspai, nomServei);
    }

    public void deletePeriodeServeiEspai(PeriodeServeiEspai periodeServeiEspai) {
        jdbcTemplate.update("DELETE from PeriodeServeiEspai where id_espai=? and nom_servei=?",
                periodeServeiEspai.getIdEspai(), periodeServeiEspai.getNomServei());
    }

    public void updatePeriodeServeiEspai (PeriodeServeiEspai periodeServeiEspai) {
        jdbcTemplate.update("UPDATE PeriodeServeiEspai SET hora_inici=?, hora_final=?, data_inici=?, data_final=? WHERE id_espai=? and nom_servei=?",
                periodeServeiEspai.getHoraInici(), periodeServeiEspai.getHoraFinal(), periodeServeiEspai.getDataInici(),
                periodeServeiEspai.getDataFinal(), periodeServeiEspai.getIdEspai(), periodeServeiEspai.getNomServei());
    }

    public PeriodeServeiEspai getPeriodeServeiEspai(String idEspai, String nomServei) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM PeriodeServeiEspai WHERE id_espai=? and nom_servei=?",
                    new PeriodeServeiEspaiRowMapper(),
                    idEspai, nomServei);
        } catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<PeriodeServeiEspai> getServeisPermanents() {
        try {
            return jdbcTemplate.query(
                    "SELECT * FROM PeriodeServeiEspai",
                    new PeriodeServeiEspaiRowMapper()
            );
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<PeriodeServeiEspai>();
        }
    }

    public List<PeriodeServeiEspai> getServeisEstacionalsFromEspai(int idEspai) {
        try {
            return jdbcTemplate.query(
                    "SELECT * FROM PeriodeServeiEspai WHERE id_espai = ?",
                    new PeriodeServeiEspaiRowMapper(),
                    idEspai);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<PeriodeServeiEspai>();
        }
    }

    public List<PeriodeServeiEspai> getServeisEstacionalsFromNom(String nom) {
        try {
            return jdbcTemplate.query(
                    "SELECT * FROM PeriodeServeiEspai WHERE nom_servei = ?",
                    new PeriodeServeiEspaiRowMapper(),
                    nom);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<PeriodeServeiEspai>();
        }
    }
}
