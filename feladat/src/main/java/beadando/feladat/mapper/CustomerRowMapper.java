package beadando.feladat.mapper;

import beadando.feladat.entity.Customer;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CustomerRowMapper  implements RowMapper<Customer> {

    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer();
        customer.setId(rs.getInt("id"));
        customer.setKey(rs.getString("key"));
        customer.setName(rs.getString("name"));
        customer.setPhone_number(rs.getString("phone_number"));
        customer.setPassword(rs.getString("password"));
        return customer;
    }

}
