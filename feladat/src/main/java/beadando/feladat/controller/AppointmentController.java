package beadando.feladat.controller;

import beadando.feladat.dto.AppointmentDTO;
import beadando.feladat.dto.CreateAppointmentDTO;
import beadando.feladat.dto.UpdateAppointmentDTO;
import beadando.feladat.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/appointment")
@Slf4j
public class AppointmentController {
    private final AppointmentService appointmentService;
    @PostMapping
    public ResponseEntity<Date> createAppointment(@RequestBody CreateAppointmentDTO createAppointmentDTO){
        try{
            var userDetails= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return new ResponseEntity<>(appointmentService.create(createAppointmentDTO,(Integer.parseInt(userDetails.toString()))), HttpStatus.CREATED);
        }catch(Exception e){
            log.error("Controller Hiba");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping
    public ResponseEntity<List<AppointmentDTO>> list(){
        try{
            var userDetails= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return new ResponseEntity<>(appointmentService.list(Integer.parseInt(userDetails.toString())),HttpStatus.OK);
        }catch (Exception e){
            log.error("Controller Hiba");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable("id") int id){
        try{
            var userDetails= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            var delete=appointmentService.delete(id,Integer.parseInt(userDetails.toString()));
            if(delete==0){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            log.error("Controller Hiba");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateAppointment(@PathVariable("id") int id, @RequestBody UpdateAppointmentDTO updateAppointmentDTO){
        try{
            var userDetails= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            var update=appointmentService.alter(id,Integer.parseInt(userDetails.toString()),updateAppointmentDTO.date());
            if(update==0){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            log.error("Controller Hiba");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
