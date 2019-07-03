package service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.dto.EntityDtoMapper;
import service.dto.RenterDto;
import service.entities.Renter;
import service.exceptions.NoEntityFoundException;
import service.repositories.RentersRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RentersService {
    @Autowired
    private EntityDtoMapper mapper;

    @Autowired
    private RentersRepository rentersRepository;

    public List<RenterDto> findAll(){
        return mapper.toRentersDto(
                rentersRepository.findAll());
    }

    public RenterDto findById(Long id) throws Exception {
        Optional<Renter> renterCandidate = rentersRepository.findById(id);
        Renter renter = renterCandidate.orElseThrow(NoEntityFoundException::new);
        return mapper.toRenterDto(renter);
    }

    public void save(RenterDto renterDto){
        // TODO Exception handling
        rentersRepository.save(mapper.toRenter(renterDto));
    }

    public void deleteById(Long id){
        // TODO Exception handling
        rentersRepository.deleteById(id);
    }
}
