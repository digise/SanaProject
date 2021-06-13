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
        controlador.setCognoms(rs.getString("cognoms"));
        controlador.setEmail(rs.getString("email"));
        controlador.setTelefon(rs.getString("telefon"));
        controlador.setContrasenya(rs.getString("contrasenya"));
        return controlador;
    }
}

