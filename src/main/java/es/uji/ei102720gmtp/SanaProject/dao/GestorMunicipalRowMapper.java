package es.uji.ei102720gmtp.SanaProject.dao;

import es.uji.ei102720gmtp.SanaProject.model.GestorMunicipal;
import es.uji.ei102720gmtp.SanaProject.model.Municipi;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class GestorMunicipalRowMapper implements RowMapper<GestorMunicipal> {

    public GestorMunicipal mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        GestorMunicipal gestorMunicipal = new GestorMunicipal();
        gestorMunicipal.setNif(rs.getString("nif"));
        gestorMunicipal.setIdMunicipi(rs.getInt("id_municipi"));
        gestorMunicipal.setNom(rs.getString("nom"));
        gestorMunicipal.setCognoms(rs.getString("cognoms"));
        gestorMunicipal.setEmail(rs.getString("email"));
        gestorMunicipal.setTelefon(rs.getString("telefon"));
        gestorMunicipal.setContrasenya(rs.getString("contrasenya"));
        return gestorMunicipal;
    }
}
