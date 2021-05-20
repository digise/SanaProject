package es.uji.ei102720gmtp.SanaProject.dao;

import es.uji.ei102720gmtp.SanaProject.model.Comentari;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class ComentarisRowMapper implements RowMapper<Comentari> {

    public Comentari mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        Comentari comentari = new Comentari();
        comentari.setIdEspaiPublic(rs.getInt("id_espaiPublic"));
        comentari.setNifCiutada(rs.getString("nif_ciutada"));
        comentari.setContadorComentaris(rs.getInt("contador_comentaris"));
        comentari.setComentari(rs.getString("comentari"));
        return comentari;
    }
}
