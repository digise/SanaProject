package es.uji.ei102720gmtp.SanaProject.dao;

import es.uji.ei102720gmtp.SanaProject.model.PeriodeServeiEspai;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public final class PeriodeServeiEspaiRowMapper implements RowMapper<PeriodeServeiEspai> {

    @Override
    public PeriodeServeiEspai mapRow(ResultSet rs, int i) throws SQLException {
        PeriodeServeiEspai periodeServeiEspai = new PeriodeServeiEspai();
        periodeServeiEspai.setIdEspai(rs.getInt("id_espai"));
        periodeServeiEspai.setNomServei(rs.getString("nom_servei"));
        periodeServeiEspai.setHoraInici(rs.getObject("hora_inici", LocalTime.class));
        periodeServeiEspai.setHoraFinal(rs.getObject("hora_final", LocalTime.class));
        periodeServeiEspai.setDataInici(rs.getObject("data_inici", LocalDate.class));
        periodeServeiEspai.setDataFinal(rs.getObject("data_final", LocalDate.class));
        return periodeServeiEspai;
    }
}
