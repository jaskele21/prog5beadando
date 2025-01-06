package beadando.feladat.service;


import beadando.feladat.dto.CustomerDTO;
import beadando.feladat.dto.CustomerDataDTO;
import beadando.feladat.dto.KeyResponseDTO;
import beadando.feladat.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationService {
    private final CustomerRepository customerRepository;
    public KeyResponseDTO login(String password, String phoneNumber) throws Exception{
        try{
            var customer = customerRepository.findByPhoneNumber(phoneNumber);
            if(!passwordEncoder().matches(password, customer.getPassword())){
                throw new Exception("WRONG_PASSWORD_OR_PHONE_NUMBER");
            }
            var key = passwordEncoder().encode(customer.toString());
            customerRepository.setKey(customer.getId(),key);
            return new KeyResponseDTO(key);
        }catch(EmptyResultDataAccessException e){
            log.error("Hiba: ",e);
            throw new Exception("WRONG_PASSWORD_OR_PHONE_NUMBER");
        }
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    public KeyResponseDTO sign(String password, String name, String phoneNumber) throws  Exception{
        try{
            var encodedPassword=passwordEncoder().encode(password);
            var id=customerRepository.addCustomer(encodedPassword,name,phoneNumber);
            var customer=new CustomerDTO(id,name,phoneNumber,encodedPassword,null);
            var key = passwordEncoder().encode(customer.toString()+LocalDate.now());
            customerRepository.setKey(customer.id(),key);
            return new KeyResponseDTO(key);
        }catch(EmptyResultDataAccessException e){
            log.error("Hiba ",e);
            throw new Exception("SOMETHING_WENT_WRONG");
        }
    }
    public void logout(int id)throws Exception {
        try {
            customerRepository.setKey(id,null);
        }catch (EmptyResultDataAccessException e) {
            log.error("Hiba ", e);
            throw new Exception("SOMETHING_WENT_WRONG");
        }
    }
    public CustomerDataDTO getMe(int id)throws Exception{
        try{
            var customer=customerRepository.findById(id);
            return new CustomerDataDTO(customer.getId(),customer.getName(),customer.getPhone_number());
        }catch(EmptyResultDataAccessException e){
            log.error("Hiba: ", e);
            throw new Exception("CUSTOMER_NOT_FOUND");
        }
    }
}
