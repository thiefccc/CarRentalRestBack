package service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import service.entities.RentedItem;

import java.util.List;

public interface RentedItemsRepository extends JpaRepository<RentedItem, Long> {
    List<RentedItem> findAllByIsRentedTrue();
}
