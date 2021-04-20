package es.uji.ei102720gmtp.SanaProject.dao;

import es.uji.ei102720gmtp.SanaProject.model.Ocupa;
import es.uji.ei102720gmtp.SanaProject.model.Zona;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OcupaRowMapper implements RowMapper<Ocupa> {

    public Ocupa mapRow(ResultSet rs, int rowNum) throws SQLException {
        Ocupa ocupa = new Ocupa();
        ocupa.setIdReserva(rs.getString("id_reserva"));
        ocupa.setIdZona(rs.getString("id_zona"));
        return ocupa;
    }


}
