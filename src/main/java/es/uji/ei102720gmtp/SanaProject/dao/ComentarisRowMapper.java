package es.uji.ei102720gmtp.SanaProject.dao;

import es.uji.ei102720gmtp.SanaProject.model.Comentaris;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class ComentarisRowMapper implements RowMapper<Comentaris> {

    public Comentaris mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        Comentaris comentari = new Comentaris();
        comentari.setIdEspaiPublic(rs.getString("id_espaiPublic"));
        comentari.setNifCiutada(rs.getString("nif_ciutada"));
        comentari.setContadorComentaris(rs.getLong("contador_comentaris"));
        comentari.setComentari(rs.getString("comentari"));
        return comentari;
    }
}
