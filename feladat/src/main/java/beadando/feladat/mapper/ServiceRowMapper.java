package beadando.feladat.mapper;

import beadando.feladat.entity.Service;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ServiceRowMapper implements RowMapper<Service> {
    @Override
    public Service mapRow(ResultSet rs, int rowNum) throws SQLException {
        Service service = new Service();
        service.setId(rs.getInt("id"));
        service.setName(rs.getString("name"));
        service.setDescription(rs.getString("description"));
        return service;
    }
}
