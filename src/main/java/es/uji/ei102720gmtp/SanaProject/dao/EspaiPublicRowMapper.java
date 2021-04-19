package es.uji.ei102720gmtp.SanaProject.dao;

import es.uji.ei102720gmtp.SanaProject.model.EspaiPublic;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EspaiPublicRowMapper implements RowMapper<EspaiPublic> {

    public EspaiPublic mapRow(ResultSet rs, int rowNum) throws SQLException {
        EspaiPublic espaiPublic = new EspaiPublic();
        espaiPublic.setId(rs.getString("id"));
        espaiPublic.setIdMunicipi(rs.getString("id_municipi"));
        espaiPublic.setNom(rs.getString("nom"));
        espaiPublic.setTipus(rs.getString("tipus"));
        espaiPublic.setTerreny(rs.getString("terreny"));
        espaiPublic.setTipusAcces(rs.getString("tipus_acces"));
        espaiPublic.setLongitud(rs.getInt("longitud"));
        espaiPublic.setAmplaria(rs.getInt("amplaria"));
        espaiPublic.setImagen(rs.getString("imagen"));
        espaiPublic.setComentaris(rs.getString("comentaris"));
        espaiPublic.setDescripcio(rs.getString("descripcio"));
        return espaiPublic;
    }
}
