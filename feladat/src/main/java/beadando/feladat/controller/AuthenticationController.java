package beadando.feladat.controller;

import beadando.feladat.dto.LoginData;
import beadando.feladat.dto.SignData;
import beadando.feladat.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginData loginData){
        try{
            return new ResponseEntity<>(authenticationService.login(loginData.password(), loginData.phoneNumber()),HttpStatus.CREATED);
        }catch (Exception e){
            switch (e.getMessage()){
                case "WRONG_PASSWORD_OR_PHONE_NUMBER":
                    return new ResponseEntity<>("WRONG_PASSWORD_OR_PHONE_NUMBER", HttpStatus.FORBIDDEN);
                default:
                    return new ResponseEntity<>("SOMETHING_WENT_WRONG",HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
    @PostMapping("/sign")
    public ResponseEntity<Object> sign(@RequestBody SignData signData){
        try{
            return new ResponseEntity<>(authenticationService.sign(signData.password(), signData.name(), signData.phoneNumber()),HttpStatus.CREATED);
        }catch (Exception e){
            log.error("Controller Hiba ",e);
            return new ResponseEntity<>("SOMETHING_WENT_WRONG",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(){
        try{
            var userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            authenticationService.logout(Integer.parseInt(userDetails.toString()));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            log.error("Controller Hiba",e);
            return new ResponseEntity<>("SOMETHING_WENT_WRONG",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/me")
    public ResponseEntity<?> getMe(){
        var userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try{
            return new ResponseEntity<>(authenticationService.getMe(Integer.parseInt(userDetails.toString())),HttpStatus.OK);
        }catch(Exception e){
            if(e.getMessage().equals("CUSTOMER_NOT_FOUND")){
                return new ResponseEntity<>("CUSTOMER_NOT_FOUND", HttpStatus.FORBIDDEN);
            }
            log.error("Controller Hiba",e);
            return new ResponseEntity<>("SOMETHING_WENT_WRONG", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
