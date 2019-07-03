package service.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "rented_items")
public class RentedItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentedItemId;
    private String rentedItemName;
    private String rentedItemType;
    private String carModel;
    private String carPlateNumber;
    @NotNull
    private Boolean isRented;

    @OneToMany(mappedBy = "rentedItem", fetch = FetchType.LAZY)
    @JsonBackReference("rent_item")
    private List<Rent> rents;
}


