package service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.dto.RenterDto;
import service.entities.Renter;
import service.repositories.RentersRepository;
import service.services.RentersService;

import java.util.List;
import java.util.Optional;

// TODO down logic to Service layer
@RestController
public class RentersController {
    @Autowired
    private RentersService rentersService;

    // private RentersRepository rentersRepository;

    @GetMapping(path = "/renters", produces = "application/json")
    public List<RenterDto> getRenters(){
        return rentersService.findAll();
    }

    @GetMapping("/renters/{renter-id}")
    public ResponseEntity<RenterDto> getRenterById(@PathVariable("renter-id") Long renterId) throws Exception {
        return ResponseEntity.ok(rentersService.findById(renterId));
    }

    @PostMapping("/renters")
    public ResponseEntity addRenter(@RequestBody RenterDto renterDto){
        rentersService.save(renterDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/renters/{renter-id}")
    public ResponseEntity deleteRenter(@PathVariable("renter-id") Long renterId){
        rentersService.deleteById(renterId);
        return ResponseEntity.ok().build();
    }
}
