package service.dto;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import service.entities.Rent;
import service.entities.RentalOffice;
import service.entities.RentedItem;
import service.entities.Renter;

import java.util.List;


@Mapper(componentModel = "spring", collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface EntityDtoMapper {
    Renter toRenter(RenterDto renterDto);
    RenterDto toRenterDto(Renter renter);
    List<Renter> toRenters(List<RenterDto> rentersDto);
    List<RenterDto> toRentersDto(List<Renter> renters);

    RentedItem toRentedItem(RentedItemDto RentedItemDto);
    RentedItemDto toRentedItemDto(RentedItem RentedItem);
    List<RentedItem> toRentedItems(List<RentedItemDto> RentedItemsDto);
    List<RentedItemDto> toRentedItemsDto(List<RentedItem> RentedItems);

    RentalOffice toRentalOffice(RentalOfficeDto RentalOfficeDto);
    RentalOfficeDto toRentalOfficeDto(RentalOffice RentalOffice);
    List<RentalOffice> toRentalOffices(List<RentalOfficeDto> RentalOfficesDto);
    List<RentalOfficeDto> toRentalOfficesDto(List<RentalOffice> RentalOffices);

    @Mapping(target = "expirationDate", source = "expirationDate", dateFormat = "dd.mm.yyyy")
    RentDto toRentDto(Rent Rent);
    Rent toRent(RentDto RentDto);
    List<Rent> toRents(List<RentDto> RentsDto);
    List<RentDto> toRentsDto(List<Rent> Rents);
}