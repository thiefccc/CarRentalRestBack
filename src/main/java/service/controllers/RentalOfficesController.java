package service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import service.entities.RentalOffice;
import service.repositories.RentalOfficesRepository;

import java.util.List;

// TODO down logic to Service layer
// TODO implement rest part of CRUD
@RestController
public class RentalOfficesController  {
    @Autowired
    RentalOfficesRepository rentalOfficesRepository;

    @GetMapping(path = "/offices", produces = "application/json")
    List<RentalOffice> getRentalOffices(){
        return rentalOfficesRepository.findAll();
    }
}
