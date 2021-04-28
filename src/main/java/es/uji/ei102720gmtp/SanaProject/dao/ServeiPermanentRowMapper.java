package es.uji.ei102720gmtp.SanaProject.dao;

import es.uji.ei102720gmtp.SanaProject.model.ServeiPermanent;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class ServeiPermanentRowMapper implements RowMapper<ServeiPermanent> {

    @Override
    public ServeiPermanent mapRow(ResultSet rs, int i) throws SQLException {
        ServeiPermanent serveiPermanent = new ServeiPermanent();
        serveiPermanent.setNom(rs.getString("nom"));
        serveiPermanent.setTipus(rs.getString("tipus"));
        return serveiPermanent;
    }
}
