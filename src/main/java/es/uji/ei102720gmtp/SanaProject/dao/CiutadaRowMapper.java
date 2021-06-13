package es.uji.ei102720gmtp.SanaProject.dao;

import es.uji.ei102720gmtp.SanaProject.model.Ciutada;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class CiutadaRowMapper implements RowMapper<Ciutada> {

    public Ciutada mapRow(ResultSet rs, int rowNum) throws SQLException {
        Ciutada ciutada = new Ciutada();
        ciutada.setNif(rs.getString("nif"));
        ciutada.setNom(rs.getString("nom"));
        ciutada.setCognoms(rs.getString("cognoms"));
        ciutada.setTelefon(rs.getString("telefon"));
        ciutada.setEmail(rs.getString("email"));
        ciutada.setDomicili(rs.getString("domicili"));
        ciutada.setLocalitat(rs.getString("localitat"));
        ciutada.setPais(rs.getString("pais"));
        ciutada.setContrasenya(rs.getString("pin"));
        return ciutada;
    }
}
