package beadando.feladat.controller;

import beadando.feladat.dto.TyreDTO;
import beadando.feladat.service.TyreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tyre")
@Slf4j
public class TyreController {
    private final TyreService tyreService;
    @GetMapping
    public ResponseEntity<List<TyreDTO>> listAll(){
        var userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try{
            return new ResponseEntity<>(tyreService.list(Integer.parseInt(userDetails.toString())),HttpStatus.OK);
        }catch(Exception e){
            log.error("Controller Hiba ",e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
