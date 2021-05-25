package es.uji.ei102720gmtp.SanaProject.dao;
import es.uji.ei102720gmtp.SanaProject.model.Zona;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class ZonaRowMapper implements RowMapper<Zona> {

    public Zona mapRow(ResultSet rs, int rowNum) throws SQLException {
        Zona zona = new Zona();
        zona.setId(rs.getInt("id"));
        zona.setIdEspai(rs.getInt("id_espai"));
        zona.setCapacitat(rs.getInt("capacitat"));
        zona.setCoordenades(rs.getString("coordenades"));
        return zona;
    }
}
