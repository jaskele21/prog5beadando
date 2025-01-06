package beadando.feladat.mapper;

import beadando.feladat.dto.AppointmentDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AppointmentRowMapper implements RowMapper<AppointmentDTO> {
    @Override
    public AppointmentDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new AppointmentDTO(rs.getInt("id"),rs.getDate("appointment"),rs.getString("name"));
    }
}
