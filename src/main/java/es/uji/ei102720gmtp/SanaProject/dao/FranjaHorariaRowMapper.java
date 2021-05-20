package es.uji.ei102720gmtp.SanaProject.dao;

import es.uji.ei102720gmtp.SanaProject.model.FranjaHoraria;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class FranjaHorariaRowMapper implements RowMapper<FranjaHoraria>
{

    public FranjaHoraria mapRow(ResultSet rs, int rowNum) throws SQLException {
        FranjaHoraria franjaHoraria = new FranjaHoraria();
        franjaHoraria.setId(rs.getInt("id"));
        franjaHoraria.setIdEspai(rs.getInt("id_espai"));
        franjaHoraria.setDescripcio(rs.getString("descripcio"));
        franjaHoraria.setHoraInici(rs.getObject("hora_inici", LocalTime.class));
        franjaHoraria.setHoraFinal(rs.getObject("hora_final", LocalTime.class));
        franjaHoraria.setDataInici(rs.getObject("data_inici", LocalDate.class));
        franjaHoraria.setDataFinal(rs.getObject("data_final", LocalDate.class));
        return franjaHoraria;
    }
}
