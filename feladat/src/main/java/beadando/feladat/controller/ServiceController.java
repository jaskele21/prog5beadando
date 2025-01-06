package beadando.feladat.controller;

import beadando.feladat.dto.ServiceDTO;
import beadando.feladat.service.ServiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/service")
@Slf4j
public class ServiceController {
    private final ServiceService serviceService;
    @GetMapping()
    public ResponseEntity<List<ServiceDTO>> listAll(){
        try{
            return new ResponseEntity<>(serviceService.listAll(), HttpStatus.OK);
        }catch (Exception e){
            log.error("Controller Hiba ",e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
