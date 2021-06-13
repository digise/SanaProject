package es.uji.ei102720gmtp.SanaProject.dao;

import es.uji.ei102720gmtp.SanaProject.model.Municipi;
import es.uji.ei102720gmtp.SanaProject.model.enums.Provincia;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class MunicipiRowMapper implements RowMapper<Municipi> {

    public Municipi mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        Municipi municipi = new Municipi();
        municipi.setId(rs.getInt("id"));
        municipi.setNom(rs.getString("nom"));
        municipi.setProvincia(Provincia.valueOf(rs.getString("provincia")));
        return municipi;
    }
}
