package service.entities;

// import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "rental_offices")
public class RentalOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentalOfficeId;
    private String rentalOfficeName;
    private String rentalOfficeAddress;

    @OneToMany(mappedBy = "getRentalOffice", fetch = FetchType.LAZY)
    @JsonBackReference("rent_getOffice")
    private List<Rent> getRents;

    @OneToMany(mappedBy = "returnRentalOffice", fetch = FetchType.LAZY)
    @JsonBackReference("rent_returnOffice")
    private List<Rent> returnRents;
}
