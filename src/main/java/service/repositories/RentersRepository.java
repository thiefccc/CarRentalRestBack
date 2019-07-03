package service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import service.entities.Renter;

public interface RentersRepository extends JpaRepository<Renter, Long> { }