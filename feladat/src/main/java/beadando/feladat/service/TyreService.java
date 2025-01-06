package beadando.feladat.service;

import beadando.feladat.dto.CreateTyreDTO;
import beadando.feladat.dto.TyreDTO;
import beadando.feladat.repository.TyreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class TyreService {
    private final TyreRepository tyreRepository;

    public List<TyreDTO> list(int customerId) throws Exception{
        try{
            var tyres= tyreRepository.findByUserId(customerId);

            return tyres.stream().map(tyre -> new TyreDTO(tyre.getId(),tyre.getWar(),tyre.getBrand(), tyre.getQuantity())).toList();
        }catch(Exception e) {
            log.error("Hiba ", e);
            throw new Exception("SOMETHING_WENT_WRONG");
        }
    }
    public TyreDTO create(CreateTyreDTO createTyreDTO,int customerId) throws Exception{
        try{
            var id= tyreRepository.addTyres(createTyreDTO.war(),createTyreDTO.brand(),createTyreDTO.quantity(),customerId);
            return new TyreDTO(id,createTyreDTO.war(),createTyreDTO.brand(),createTyreDTO.quantity());
        }catch(Exception e){
            log.error("Hiba ", e);
            throw new Exception("SOMETHING_WENT_WRONG");
        }
    }
}
