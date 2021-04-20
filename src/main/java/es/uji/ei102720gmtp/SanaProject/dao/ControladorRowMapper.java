package es.uji.ei102720gmtp.SanaProject.dao;

import es.uji.ei102720gmtp.SanaProject.model.Controlador;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ControladorRowMapper implements RowMapper<Controlador>
{

    public Controlador mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        Controlador controlador = new Controlador();
        controlador.setNif(rs.getString("nif"));
        controlador.setNom(rs.getString("nom"));
        controlador.setCognom(rs.getString("cognoms"));
        controlador.setEspecialitat(rs.getString("especialitat"));
        controlador.setEmail(rs.getString("email"));
        controlador.setTelefon(rs.getString("telefon"));
        return controlador;
    }
}

