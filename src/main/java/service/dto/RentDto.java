package service.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RentDto {
    private Long rentId;
    private Date getTimestamp;
    private Date returnTimestamp;
    private Date expirationDate;
    private String rentInfo;

    private RenterDto renter;
    private RentedItemDto rentedItem;
    private RentalOfficeDto getRentalOffice;
    private RentalOfficeDto returnRentalOffice;
}