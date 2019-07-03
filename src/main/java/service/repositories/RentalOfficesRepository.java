package service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import service.entities.RentalOffice;

public interface RentalOfficesRepository extends JpaRepository<RentalOffice, Long> { }