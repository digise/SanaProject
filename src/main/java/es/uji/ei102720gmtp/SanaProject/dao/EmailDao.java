package es.uji.ei102720gmtp.SanaProject.dao;


import es.uji.ei102720gmtp.SanaProject.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmailDao
{
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /* Afegim */
    public void addEmail(Email email) {
        jdbcTemplate.update("INSERT INTO email (data_enviament, emisor, receptor, asunto, mensaje, nif_ciutada) VALUES(?, ?, ?, ?, ?, ?)",
                email.getDataEnviament(), email.getEmisor(), email.getReceptor(), email.getAsunto(), email.getMensaje(),
                email.getNifCiutada());
    }

    /* Esborrem  */
    public void deleteEmail(int idEMail) {
        jdbcTemplate.update("DELETE from email where id=?",
                idEMail);
    }

    /* Actualitzem els atributs
       (excepte el id, que és la clau primària) */
    public void updateEmail(Email email) {
        jdbcTemplate.update("UPDATE email SET data_enviament = ?, emisor = ?, receptor = ?, asunto = ?, mensaje = ?, nif_ciutada = ? WHERE id = ?",
                email.getDataEnviament(), email.getEmisor(), email.getReceptor(), email.getAsunto(), email.getMensaje(),
                email.getNifCiutada(), email.getId());
    }

    /*public void updateNomEspaiPublic(String nom, EspaiPublic espaiPublic){
        jdbcTemplate.update("UPDATE espaiPublic SET nom = ? WHERE id = ?",
                nom, espaiPublic.getId());

    }*/


    public Email getEmail(int id) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM email WHERE id = ?",
                    new EmailRowMapper(),
                    id);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Email> getEmails() {
        try {
            return jdbcTemplate.query(
                    "SELECT * FROM email",
                    new EmailRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<Email>();
        }
    }

    public List<Email> getEmailsDeCiutada(String nif) {
        try {
            return jdbcTemplate.query(
                    "SELECT * FROM email WHERE nif_ciutada = ?",
                    new EmailRowMapper(), nif);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<Email>();
        }
    }



}
