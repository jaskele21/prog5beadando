package beadando.feladat.service;

import beadando.feladat.dto.ServiceDTO;
import beadando.feladat.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ServiceService {
    private final ServiceRepository serviceRepository;
    public List<ServiceDTO> listAll()throws Exception{
        try{
            var services=serviceRepository.findAll();
            return services.stream().map(service -> new ServiceDTO(service.getId(), service.getName(), service.getDescription())).toList();
        }catch(Exception e){
            log.error("Error ",e);
            throw new Exception("SOMETHING_WENT_WRONG");
        }
    }
}
