package service.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
// @Builder
@Entity
@Table(name = "renters")
public class Renter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long renterId;

    private String renterName;

    private String renterInfo;

    @OneToMany(mappedBy = "renter", fetch = FetchType.LAZY)
    @JsonBackReference("rent_renter")
    private List<Rent> rents;
}
