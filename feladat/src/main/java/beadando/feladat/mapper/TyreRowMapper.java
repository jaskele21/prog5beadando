package beadando.feladat.mapper;

import beadando.feladat.entity.Tyre;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TyreRowMapper implements RowMapper<Tyre> {
    public Tyre mapRow(ResultSet rs, int rowNum) throws SQLException {
        Tyre tyre = new Tyre();
        tyre.setId(rs.getInt("id"));
        tyre.setCustomer_id(rs.getInt("customer_id"));
        tyre.setBrand(rs.getString("brand"));
        tyre.setWar(rs.getString("war"));
        tyre.setQuantity(rs.getShort("quantity"));
        return tyre;
    }
}
