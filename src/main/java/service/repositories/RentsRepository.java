package service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import service.entities.Rent;

import java.util.List;

public interface RentsRepository extends JpaRepository<Rent, Long> {
    List<Rent> findAllByReturnTimestampIsNull();
    List<Rent> findAllByReturnTimestampIsNotNull();
    List<Rent> findAllByRentedItem_RentedItemId(Long rentedItemId);
}
