package beadando.feladat.dto;
import java.util.Date;

public record CreateAppointmentDTO(CreateTyreDTO tyre, Date appointment, int serviceId){
}
