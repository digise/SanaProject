package es.uji.ei102720gmtp.SanaProject.dao;

import es.uji.ei102720gmtp.SanaProject.model.Comentaris;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class ComentarisDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /* Afegim el comentari */
    public void addComentari(Comentaris comentari) {
        jdbcTemplate.update("INSERT INTO Comentaris VALUES(?, ?, ?, ?)",
                comentari.getIdEspaiPublic(), comentari.getNifCiutada(), comentari.getContadorComentaris(), comentari.getComentari());
    }

    /* Esborrem el comentari amb el id, nif, contador*/
    public void deleteComentari(String idEspaiPublic, String nifCiutada, long contador) {
        jdbcTemplate.update("DELETE FROM Comentaris WHERE id_espaiPublic=?, nif_ciutada=?, contador_comentaris=?",
                idEspaiPublic, nifCiutada, contador);
    }

    /* Esborrem el comentari */
    public void deleteComentari(Comentaris comentari) {
        jdbcTemplate.update("DELETE FROM Comentaris WHERE id_espaiPublic=?, nif_ciutada=?, contador_comentaris=?",
                comentari.getIdEspaiPublic(), comentari.getNifCiutada(), comentari.getContadorComentaris());
    }

    /* Actualitzem els atributs del comentari
       (excepte el id, nif que és la clau primària) */
    public void updateComentari(Comentaris comentari) {
        jdbcTemplate.update("UPDATE Comentaris SET contador_comentaris = ?, comentari = ? WHERE id_espaiPublic = ?, nif_ciutada=?",
                comentari.getContadorComentaris(), comentari.getComentari(), comentari.getIdEspaiPublic(), comentari.getNifCiutada());
    }

    /* Obtenim el comentari amb el id, nif y contador. Torna null si no existeix. */
    public Comentaris getComentari(String idEspaiPublic, String nif, long contador) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM Comentaris WHERE id_espaiPublic = ?, nif_ciutada=?, contador_comentaris=?",
                    new ComentarisRowMapper(),
                    idEspaiPublic, nif, contador);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    /* Obtenim tots els Comentaris. Torna una llista buida si no n'hi ha cap. */
    public List<Comentaris> getComentaris() {
        try {
            return jdbcTemplate.query(
                    "SELECT * FROM Comentaris",
                    new ComentarisRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<Comentaris>();
        }
    }

    /* Obtenim tots els comentaris de un Espai Public. Torna una llista buida si no n'hi ha cap*/
    public List<Comentaris> getComentarisEspaiPublic(String id){
        try{
            return jdbcTemplate.query(
                    "SELECT * FROM Comentaris WHERE id_espaiPublic=?",
                    new ComentarisRowMapper(),
                    id);
        }catch (EmptyResultDataAccessException ex){
            return new ArrayList<Comentaris>();
        }
    }

    /* Obtenim tots els comentaris de un ciutada. Torna una llista buida si no n'hi ha cap*/
    public List<Comentaris> getComentarisCiutada(String nif){
        try{
            return jdbcTemplate.query(
                    "SELECT * FROM Comentaris WHERE nif_ciutada=?",
                    new ComentarisRowMapper(),
                    nif);
        }catch (EmptyResultDataAccessException ex){
            return new ArrayList<Comentaris>();
        }
    }
}
