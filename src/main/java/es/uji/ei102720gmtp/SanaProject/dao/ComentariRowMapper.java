package es.uji.ei102720gmtp.SanaProject.dao;

import es.uji.ei102720gmtp.SanaProject.model.Comentari;
import es.uji.ei102720gmtp.SanaProject.model.Municipi;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class ComentariRowMapper implements RowMapper<Comentari> {

    public Comentari mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        Comentari comentari = new Comentari();
        comentari.setIdEspaiPublic(rs.getString("id_espaiPublic"));
        comentari.setNifCiutada(rs.getString("nif_ciutada"));
        comentari.setContadorComentaris(rs.getLong("contador_comentaris"));
        comentari.setComentari(rs.getString("comentari"));
        return comentari;
    }
}
