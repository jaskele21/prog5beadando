package beadando.feladat.repository;

import beadando.feladat.entity.Service;
import beadando.feladat.mapper.ServiceRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ServiceRepository {
    private final JdbcTemplate jdbcTemplate;
    private final ServiceRowMapper serviceRowMapper;
    public List<Service> findAll(){
        return jdbcTemplate.query("SELECT * FROM services",serviceRowMapper);
    }
}
