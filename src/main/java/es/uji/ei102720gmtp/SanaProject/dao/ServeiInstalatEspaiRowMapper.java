package es.uji.ei102720gmtp.SanaProject.dao;

import es.uji.ei102720gmtp.SanaProject.model.ServeiInstalatEspai;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public final class ServeiInstalatEspaiRowMapper implements RowMapper<ServeiInstalatEspai> {

    @Override
    public ServeiInstalatEspai mapRow(ResultSet rs, int i) throws SQLException {
        ServeiInstalatEspai serveiInstalatEspai = new ServeiInstalatEspai();
        serveiInstalatEspai.setIdEspai(rs.getInt("id_espai"));
        serveiInstalatEspai.setNomServei(rs.getString("nom_servei"));
        serveiInstalatEspai.setDataApertura(rs.getObject("data_apertura", LocalDate.class));
        return serveiInstalatEspai;
    }
}
