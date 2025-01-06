package beadando.feladat.repository;

import beadando.feladat.dto.AppointmentDTO;
import beadando.feladat.entity.Appointment;
import beadando.feladat.mapper.AppointmentRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.Date;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class AppointmentRepository {
    private final JdbcTemplate jdbcTemplate;
    private final AppointmentRowMapper appointmentRowMapper;

    public int addAppointment(int customerId,int serviceId, Date appointment){
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(connection -> {
            PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory("INSERT INTO appointments (customer_id,appointment,service_id) VALUES (?,?,?)", Types.INTEGER,Types.DATE,Types.INTEGER);
            pscf.setReturnGeneratedKeys(true);
            PreparedStatementCreator psc = pscf.newPreparedStatementCreator(List.of(customerId,appointment,serviceId));
            return psc.createPreparedStatement(connection);
        }, generatedKeyHolder);
        return (Integer)generatedKeyHolder.getKeys().get("id");
    }
    public List<AppointmentDTO> findByCustomerId(int customerId){
        return jdbcTemplate.query("SELECT a.id,a.appointment,s.name FROM  appointments a JOIN services s ON a.service_id=s.id WHERE a.customer_id=? ",appointmentRowMapper,customerId);
    }
    public int delete(int appointmentId,int customerId){
        return jdbcTemplate.update("DELETE FROM appointments WHERE id=? AND customer_id=?",appointmentId,customerId);
    }
    public int alterAppointmentDate(int appointmentId,int customerId,Date newDate){
        return jdbcTemplate.update("UPDATE appointments SET appointment=? WHERE customer_Id=? AND id=?",newDate,customerId,appointmentId);
    }
}
