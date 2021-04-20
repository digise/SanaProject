package es.uji.ei102720gmtp.SanaProject.dao;

import es.uji.ei102720gmtp.SanaProject.model.EspaiPublic;
import es.uji.ei102720gmtp.SanaProject.model.enums.TipusAcces;
import es.uji.ei102720gmtp.SanaProject.model.enums.TipusEspai;
import es.uji.ei102720gmtp.SanaProject.model.enums.TipusSol;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EspaiPublicRowMapper implements RowMapper<EspaiPublic> {

    public EspaiPublic mapRow(ResultSet rs, int rowNum) throws SQLException {
        EspaiPublic espaiPublic = new EspaiPublic();
        espaiPublic.setId(rs.getString("id"));
        espaiPublic.setIdMunicipi(rs.getString("id_municipi"));
        espaiPublic.setNom(rs.getString("nom"));
        espaiPublic.setTipus(rs.getObject("tipus",  TipusEspai.class));
        espaiPublic.setTerreny(rs.getObject("terreny", TipusSol.class));
        espaiPublic.setTipusAcces(rs.getObject("tipus_acces", TipusAcces.class));
        espaiPublic.setLongitud(rs.getInt("longitud"));
        espaiPublic.setAmplaria(rs.getInt("amplaria"));
        espaiPublic.setImagen(rs.getString("imagen"));
        espaiPublic.setDescripcio(rs.getString("descripcio"));
        return espaiPublic;
    }
}
