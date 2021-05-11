package es.uji.ei102720gmtp.SanaProject.dao;

import es.uji.ei102720gmtp.SanaProject.model.Controla;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ControlaRowMapper implements RowMapper<Controla>
    {

        public Controla mapRow(ResultSet rs, int rowNum) throws SQLException
        {
            Controla controla = new Controla();
            controla.setNifControlador(rs.getString("nif_controlador"));
            controla.setIdEspai(rs.getInt("id_espai"));
            controla.setDataInici(rs.getObject("data_inici", LocalDate.class));
            controla.setDataFinal(rs.getObject("data_final", LocalDate.class));
            return controla;
        }
    }

