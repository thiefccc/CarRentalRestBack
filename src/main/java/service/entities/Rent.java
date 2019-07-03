package service.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rents")
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date getTimestamp;
    @Temporal(TemporalType.TIMESTAMP)
    private Date returnTimestamp;
    @Temporal(TemporalType.DATE)
    private Date expirationDate;
    private String rentInfo;

    @ManyToOne
    @JoinColumn(name = "renter_id")
    private Renter renter;

    @ManyToOne
    @JoinColumn(name = "rented_item_id")
    private RentedItem rentedItem;

    @ManyToOne
    @JoinColumn(name = "get_rental_office_id")
    private RentalOffice getRentalOffice;

    @ManyToOne
    @JoinColumn(name = "return_rental_office_id")
    private RentalOffice returnRentalOffice;
}

