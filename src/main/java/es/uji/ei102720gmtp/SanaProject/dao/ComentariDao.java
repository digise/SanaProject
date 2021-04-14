package es.uji.ei102720gmtp.SanaProject.dao;

import es.uji.ei102720gmtp.SanaProject.model.Comentari;
import es.uji.ei102720gmtp.SanaProject.model.Municipi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class ComentariDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /* Afegim el comentari */
    public void addComentari(Comentari comentari) {
        jdbcTemplate.update("INSERT INTO Comentaris VALUES(?, ?, ?, ?)",
                comentari.getIdEspaiPublic(), comentari.getNifCiutada(), comentari.getContadorComentaris(), comentari.getComentari());
    }

    /* Esborrem el comentari amb el id, nif, contador*/
    public void deleteComentari(String idEspaiPublic, String nifCiutada, long contador) {
        jdbcTemplate.update("DELETE FROM Comentaris WHERE id_espaiPublic=?, nif_ciutada=?, contador_comentaris=?",
                idEspaiPublic, nifCiutada, contador);
    }

    /* Esborrem el comentari */
    public void deleteComentari(Comentari comentari) {
        jdbcTemplate.update("DELETE FROM Comentaris WHERE id_espaiPublic=?, nif_ciutada=?, contador_comentaris=?",
                comentari.getIdEspaiPublic(), comentari.getNifCiutada(), comentari.getContadorComentaris());
    }

    /* Actualitzem els atributs del comentari
       (excepte el id, nif que és la clau primària) */
    public void updateComentari(Comentari comentari) {
        jdbcTemplate.update("UPDATE Comentaris SET contador_comentaris = ?, comentari = ? WHERE id_espaiPublic = ?, nif_ciutada=?",
                comentari.getContadorComentaris(), comentari.getComentari(), comentari.getIdEspaiPublic(), comentari.getNifCiutada());
    }

    /* Obtenim el comentari amb el id, nif y contador. Torna null si no existeix. */
    public Comentari getComentari(String idEspaiPublic, String nif, long contador) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM Comentaris WHERE id_espaiPublic = ?, nif_ciutada=?, contador_comentaris=?",
                    new ComentariRowMapper(),
                    idEspaiPublic, nif, contador);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    /* Obtenim tots els Comentaris. Torna una llista buida si no n'hi ha cap. */
    public List<Comentari> getComentaris() {
        try {
            return jdbcTemplate.query(
                    "SELECT * FROM Comentaris",
                    new ComentariRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<Comentari>();
        }
    }

    /* Obtenim tots els comentaris de un Espai Public. Torna una llista buida si no n'hi ha cap*/
    public List<Comentari> getComentarisEspaiPublic(String id){
        try{
            return jdbcTemplate.query(
                    "SELECT * FROM Comentaris WHERE id_espaiPublic=?",
                    new ComentariRowMapper(),
                    id);
        }catch (EmptyResultDataAccessException ex){
            return new ArrayList<Comentari>();
        }
    }

    /* Obtenim tots els comentaris de un ciutada. Torna una llista buida si no n'hi ha cap*/
    public List<Comentari> getComentarisCiutada(String nif){
        try{
            return jdbcTemplate.query(
                    "SELECT * FROM Comentaris WHERE nif_ciutada=?",
                    new ComentariRowMapper(),
                    nif);
        }catch (EmptyResultDataAccessException ex){
            return new ArrayList<Comentari>();
        }
    }
}
