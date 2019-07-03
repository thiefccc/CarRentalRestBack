package service.dto;

import lombok.Data;

@Data
public class RentedItemDto {
    private Long rentedItemId;
    private String rentedItemName;
    private String rentedItemType;
    private String carModel;
    private String carPlateNumber;
    private Boolean isRented;
}


