package es.uji.ei102720gmtp.SanaProject.dao;

import es.uji.ei102720gmtp.SanaProject.model.Email;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class EmailRowMapper implements RowMapper<Email> {

    public Email mapRow(ResultSet rs, int rowNum) throws SQLException {
        Email email = new Email();
        email.setId(rs.getInt("id"));
        email.setDataEnviament(rs.getObject("data_enviament", LocalDate.class));
        email.setEmisor(rs.getString("emisor"));
        email.setReceptor(rs.getString("receptor"));
        email.setAsunto(rs.getString("asunto"));
        email.setMensaje(rs.getString("mensaje"));
        email.setNifCiutada(rs.getString("nif_ciutada"));
        return email;
    }
}
