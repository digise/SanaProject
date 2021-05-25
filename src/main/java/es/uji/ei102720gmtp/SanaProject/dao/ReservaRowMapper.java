package es.uji.ei102720gmtp.SanaProject.dao;

import es.uji.ei102720gmtp.SanaProject.model.Reserva;
import es.uji.ei102720gmtp.SanaProject.model.enums.EstatReserva;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public final class ReservaRowMapper implements RowMapper<Reserva> {

    public Reserva mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        Reserva reserva = new Reserva();
        reserva.setId(rs.getInt("id"));
        reserva.setCodiQr(rs.getString("codi_qr"));
        reserva.setNombrePersones(rs.getInt("nombre_persones"));
        reserva.setEstat(rs.getObject("estat", EstatReserva.class));
        reserva.setNifCiutada(rs.getString("nif_ciutada"));
        reserva.setIdFranja(rs.getInt("id_franja"));
        reserva.setIdZona(rs.getInt("id_zona"));
        reserva.setDataReserva(rs.getObject("data_reserva", LocalDate.class));
        return reserva;
    }
}

