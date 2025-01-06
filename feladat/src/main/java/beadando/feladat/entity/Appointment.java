package beadando.feladat.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Appointment {
    private int id,customerId,serviceId;
    private Date appointment;
}
