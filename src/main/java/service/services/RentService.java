package service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.dto.EntityDtoMapper;
import service.dto.RentDto;
import service.entities.Rent;
import service.entities.RentalOffice;
import service.entities.RentedItem;
import service.entities.Renter;
import service.repositories.RentalOfficesRepository;
import service.repositories.RentersRepository;
import service.repositories.RentsRepository;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RentService {
    @Autowired
    EntityDtoMapper mapper;
    @Autowired
    DateParserService dateParserService;

    @Autowired
    RentedItemsService itemsService;

    @Autowired
    RentsRepository rentsRepository;
    @Autowired
    RentersRepository rentersRepository;
    @Autowired
    RentalOfficesRepository officesRepository;

    public List<RentDto> findAll(){
        return mapper.toRentsDto(
                rentsRepository.findAll());
    }

    public List<RentDto> findAllByReturnTimestampIsNotNull() {
        return mapper.toRentsDto(
                rentsRepository.findAllByReturnTimestampIsNotNull());
    }

    public List<RentDto> findAllByReturnTimestampIsNull(){
        return mapper.toRentsDto(
            rentsRepository.findAllByReturnTimestampIsNull());
    }

    public List<RentDto> findAllByRentedItem_RentedItemId(Long id){
        return mapper.toRentsDto(
                rentsRepository.findAllByRentedItem_RentedItemId(id));
    }

    private Rent buildRentForOpen(RentDto rentDTO) throws Exception{
        // TODO Try map whole object, if Exception, find in Repository
        Long renterId = rentDTO.getRenter().getRenterId();
        Long rentedItemId = rentDTO.getRentedItem().getRentedItemId();
        Long getOfficeId = rentDTO.getGetRentalOffice().getRentalOfficeId();
        Date expirationDate;
        Date getTimestamp;
        // TODO ApiException
        try {
            expirationDate = rentDTO.getExpirationDate();
            getTimestamp = dateParserService.nowDateTime();
        }catch (ParseException e){
            throw new Exception("Couldn't parse one of dates.", e);
        }

        // TODO Spring validation
        if(renterId == null || rentedItemId == null || getOfficeId == null || expirationDate == null || getTimestamp == null){
            throw new Exception("Not all necessary properties provided.");
        }

        // TODO throw normal ApiExceptions
        Renter renter = rentersRepository.findById(renterId).orElseThrow(() -> new Exception("Renter not found in JpaRepository."));
        RentedItem rentedItem = mapper.toRentedItem(itemsService.findOneById(rentedItemId));
        RentalOffice getRentalOffice = officesRepository.findById(getOfficeId).orElseThrow(() -> new Exception("Get Rental Office not found in JpaRepository."));

        return Rent.builder()
                .rentId(rentDTO.getRentId()).rentInfo(rentDTO.getRentInfo())
                .renter(renter).rentedItem(rentedItem).getRentalOffice(getRentalOffice)
                .expirationDate(expirationDate).getTimestamp(getTimestamp)
                .build();
    }

    private Rent buildRentForExtend(RentDto rentDTO) throws Exception{
        Long rentId = rentDTO.getRentId();
        Date newExpirationDate = rentDTO.getExpirationDate(); // dateParserService.toDate(rentDTO.getExpirationDate(), "dd-mm-yyyy");

        // TODO Spring validation
        if(rentId == null || newExpirationDate == null){
            throw new Exception("Not all necessary properties provided.");
        }

        // TODO throw normal ApiExceptions
        Optional<Rent> rentCandidate = rentsRepository.findById(rentId);
        Rent rent = rentCandidate.orElseThrow(() -> new Exception("Rent with ID " + rentId + " hasn't been found."));

        if(newExpirationDate.compareTo(rent.getExpirationDate()) <= 0)
            throw new Exception("Couldn't extend for date which less than current.");

        rent.setExpirationDate(newExpirationDate);

        return rent;
    }

    private Rent buildRentForClose(RentDto rentDTO) throws Exception{
        Long rentId = rentDTO.getRentId();
        Long returnOfficeId = rentDTO.getReturnRentalOffice().getRentalOfficeId();
        Date returnTimestamp;

        // TODO ApiException
        try {
            returnTimestamp = dateParserService.nowDateTime();
        }catch (ParseException e){
            throw new Exception("Couldn't parse one of dates.", e);
        }

        // TODO Spring validation
        if(rentId == null || returnOfficeId == null || returnTimestamp == null){
            throw new Exception("Not all necessary properties provided.");
        }

        // TODO throw normal ApiExceptions
        Optional<Rent> rentCandidate = rentsRepository.findById(rentId);
        Rent rent = rentCandidate.orElseThrow(() -> new Exception("Rent with ID " + rentId + " hasn't been found."));
        RentalOffice returnRentalOffice = officesRepository.findById(returnOfficeId).orElseThrow(() -> new Exception("TODO"));
        rent.setReturnRentalOffice(returnRentalOffice);
        rent.setReturnTimestamp(returnTimestamp);

        return rent;
    }

    public void OpenRent(RentDto rentDTO) throws Exception {
        Rent rent = this.buildRentForOpen(rentDTO);
        RentedItem item = rent.getRentedItem();
        if(item.getIsRented() == true){
            // TODO ApiException ItemAlreadyRentedException
            throw new Exception("Item already rented");
        }

        itemsService.makeRented(item);
        rentsRepository.save(rent);
    }

    public void CloseRent(RentDto rentDTO) throws Exception {
        Rent rent = this.buildRentForClose(rentDTO);
        itemsService.makeFree(rent.getRentedItem());
        rentsRepository.save(rent);
    }

    public void ExtendRent(RentDto rentDTO) throws Exception {
        rentsRepository.save(this.buildRentForExtend(rentDTO));
    }
}
