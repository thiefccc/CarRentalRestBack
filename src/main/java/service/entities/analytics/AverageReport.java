package service.entities.analytics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AverageReport {
    private BigDecimal avgDuration;
    private String carModel;
    private String rentalOfficeName;
}
