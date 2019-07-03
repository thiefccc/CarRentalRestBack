package service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.dto.EntityDtoMapper;
import service.dto.RentedItemDto;
import service.dto.RenterDto;
import service.entities.RentedItem;
import service.repositories.RentedItemsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RentedItemsService {
    @Autowired
    EntityDtoMapper mapper;
    @Autowired
    RentedItemsRepository repository;

    public RentedItemDto findOneById(Long id) throws Exception {
        RentedItem rentedItemCandidate = repository.findById(id).orElseThrow(Exception::new);
        return mapper.toRentedItemDto(rentedItemCandidate);
    }

    public List<RentedItemDto> findAllByIsRentedTrue(){
        return mapper.toRentedItemsDto(repository.findAllByIsRentedTrue());
    }

    public List<RentedItemDto> findAll(){
        return mapper.toRentedItemsDto(repository.findAll());
    }

    public void save(RentedItemDto rentedItemDto){
        // TODO Exception handling
        repository.save(mapper.toRentedItem(rentedItemDto));
    }

    public void makeRented(RentedItem item){
        setIsRented(item, true);
    }

    public void makeFree(RentedItem item){
        setIsRented(item, false);
    }

    void setIsRented(RentedItem item, Boolean value){
        item.setIsRented(value);
        repository.save(item);
    }
}
