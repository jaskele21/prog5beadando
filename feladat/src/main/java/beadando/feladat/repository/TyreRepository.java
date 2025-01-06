package beadando.feladat.repository;

import beadando.feladat.entity.Tyre;
import beadando.feladat.mapper.TyreRowMapper;
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
public class TyreRepository {
    private final JdbcTemplate jdbcTemplate;
    private final TyreRowMapper tyreRowMapper;
    public List<Tyre> findByUserId(int customerId){
        return jdbcTemplate.query("SELECT * FROM tyres WHERE customer_id=?",tyreRowMapper,customerId);
    }
    public int addTyres(String war,String brand,Short quantity,int customerId){
        GeneratedKeyHolder generatedKeyHolder= new GeneratedKeyHolder();
        this.jdbcTemplate.update(connection-> {
            PreparedStatementCreatorFactory pscf= new PreparedStatementCreatorFactory("INSERT INTO tyres(war,brand,quantity,customer_id) VALUES (?,?,?,?)", Types.VARCHAR,Types.VARCHAR,Types.SMALLINT,Types.INTEGER);
            pscf.setReturnGeneratedKeys(true);
            PreparedStatementCreator psc=pscf.newPreparedStatementCreator(List.of(war,brand,quantity,customerId));
            return psc.createPreparedStatement(connection);
        },generatedKeyHolder);
        return (Integer)generatedKeyHolder.getKeys().get("id");
    }
}
