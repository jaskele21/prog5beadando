package beadando.feladat.repository;

import beadando.feladat.entity.Customer;
import beadando.feladat.mapper.CustomerRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CustomerRepository {

    private final JdbcTemplate jdbcTemplate;
    private final CustomerRowMapper customerRowMapper;
    public Customer findByKey(String key){
        return jdbcTemplate.queryForObject("SELECT * FROM customers WHERE key LIKE ? ",customerRowMapper,key);
    }
    public Customer findById(int id){
        return jdbcTemplate.queryForObject("SELECT * FROM customers WHERE id=?",customerRowMapper,id);
    }
    public Customer findByPhoneNumber(String phoneNumber){
        return jdbcTemplate.queryForObject("SELECT * FROM customers WHERE phone_number LIKE ?",customerRowMapper,phoneNumber);
    }
    public int setKey(int id, String key){
        return jdbcTemplate.update("UPDATE customers SET key=? WHERE id=?",key,id);
    }
    public int addCustomer(String password, String name, String phoneNumber){
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(connection -> {
            PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory("INSERT INTO customers (password,name,phone_number) VALUES (?,?,?)", Types.VARCHAR,Types.VARCHAR,Types.VARCHAR);
            pscf.setReturnGeneratedKeys(true);
            PreparedStatementCreator psc = pscf.newPreparedStatementCreator(List.of(password,name,phoneNumber));
            return psc.createPreparedStatement(connection);
        }, generatedKeyHolder);
        return (Integer)generatedKeyHolder.getKeys().get("id");
    }
}
