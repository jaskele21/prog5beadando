package beadando.feladat.service;

import beadando.feladat.dto.AppointmentDTO;
import beadando.feladat.dto.CreateAppointmentDTO;
import beadando.feladat.dto.TyreDTO;
import beadando.feladat.entity.Appointment;
import beadando.feladat.repository.AppointmentRepository;
import beadando.feladat.repository.TyreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final TyreService tyreService;
    public Date create(CreateAppointmentDTO createAppointmentDTO,int customerId) throws Exception{
        try{
            appointmentRepository.addAppointment(customerId, createAppointmentDTO.serviceId(),createAppointmentDTO.appointment());
            tyreService.create(createAppointmentDTO.tyre(),customerId);
            return createAppointmentDTO.appointment();
        }catch (Exception e){
            log.error("Hiba", e);
            throw new Exception("SOMETHING_WENT_WRONG");
        }
    }
    public List<AppointmentDTO> list(int customerId)throws Exception{
        try{
            return appointmentRepository.findByCustomerId(customerId);
        }catch (Exception e){
            log.error("Hiba", e);
            throw new Exception("SOMETHING_WENT_WRONG");
        }
    }
    public int delete(int appointmentId, int customerId)throws Exception {
        try {
            return appointmentRepository.delete(appointmentId, customerId);
        } catch (Exception e) {
            log.error("Hiba", e);
            throw new Exception("SOMETHING_WENT_WRONG");
        }
    }
    public int alter(int appointmentId,int customerId,Date newDate) throws Exception{
        try {
            return appointmentRepository.alterAppointmentDate(appointmentId,customerId,newDate);
        } catch (Exception e) {
            log.error("Hiba", e);
            throw new Exception("SOMETHING_WENT_WRONG");
        }
    }
}
