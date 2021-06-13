package es.uji.ei102720gmtp.SanaProject.dao;

import es.uji.ei102720gmtp.SanaProject.model.EspaiPublic;
import es.uji.ei102720gmtp.SanaProject.model.enums.TipusAcces;
import es.uji.ei102720gmtp.SanaProject.model.enums.TipusTerreny;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EspaiPublicRowMapper implements RowMapper<EspaiPublic> {

    public EspaiPublic mapRow(ResultSet rs, int rowNum) throws SQLException {
        EspaiPublic espaiPublic = new EspaiPublic();
        espaiPublic.setId(rs.getInt("id"));
        espaiPublic.setIdMunicipi(rs.getInt("id_municipi"));
        espaiPublic.setNom(rs.getString("nom"));
        espaiPublic.setTipus(rs.getString("tipus"));
        espaiPublic.setTipusTerreny(TipusTerreny.valueOf(rs.getString("terreny")));
        espaiPublic.setTipusAcces(TipusAcces.valueOf(rs.getString("tipus_acces")));
        espaiPublic.setLocalitzacio(rs.getString("localitzacio"));
        espaiPublic.setLongitud(rs.getInt("longitud"));
        espaiPublic.setAmplaria(rs.getInt("amplaria"));
        espaiPublic.setImagen(rs.getString("imagen"));
        espaiPublic.setDescripcio(rs.getString("descripcio"));
        return espaiPublic;
    }
}
