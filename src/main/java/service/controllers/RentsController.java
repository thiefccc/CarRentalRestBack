package service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.dto.RentDto;
import service.services.RentService;

import java.util.List;

@RestController
public class RentsController {
    @Autowired
    private RentService rentService;

    @GetMapping(path = "/rents", produces = "application/json")
    public ResponseEntity getRents(@RequestParam(name="active", required = false) Boolean active){
        if (active != null) {
            return ResponseEntity.ok( active == true ?
                    rentService.findAllByReturnTimestampIsNull() :
                    rentService.findAllByReturnTimestampIsNotNull());
        }

        return ResponseEntity.ok(rentService.findAll());
    }

    @GetMapping(value = "/rentsByItem/{id}") // , produces = "application/json"
    public ResponseEntity getRentsByItems(@PathVariable Long id){
        return ResponseEntity.ok(rentService.findAllByRentedItem_RentedItemId(id));
    }

    @PostMapping(path = "/rents")
    public ResponseEntity openRent(@RequestBody RentDto rentDto){
        try {
            rentService.OpenRent(rentDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Error", e.getMessage()).build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/rents")
    public ResponseEntity updateRent(@RequestBody RentDto rentDTO){
        try {
            if(rentDTO.getExpirationDate() != null){
                rentService.ExtendRent(rentDTO);
            }
            else {
                rentService.CloseRent(rentDTO);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Error", e.getMessage()).build();
        }
        return ResponseEntity.ok().build();
    }
}
