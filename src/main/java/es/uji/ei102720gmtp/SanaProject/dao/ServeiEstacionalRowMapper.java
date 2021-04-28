package es.uji.ei102720gmtp.SanaProject.dao;

import es.uji.ei102720gmtp.SanaProject.model.ServeiEstacional;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class ServeiEstacionalRowMapper implements RowMapper<ServeiEstacional> {

    @Override
    public ServeiEstacional mapRow(ResultSet rs, int i) throws SQLException {
        ServeiEstacional serveiEstacional = new ServeiEstacional();
        serveiEstacional.setNom(rs.getString("nom"));
        serveiEstacional.setTipus(rs.getString("tipus"));
        return serveiEstacional;
    }
}