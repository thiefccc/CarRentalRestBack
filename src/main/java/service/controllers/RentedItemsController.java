package service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.dto.RentedItemDto;
import service.services.RentedItemsService;

import java.util.List;

@RestController
@RequestMapping("/items")
public class RentedItemsController {
    @Autowired
    RentedItemsService itemsService;

    @GetMapping
    ResponseEntity getRentedItems(@RequestParam(name = "onlyFree", required = false) Boolean onlyFree){
        List<RentedItemDto> rentedItemDtos;
        if(onlyFree != null && onlyFree == true){
            rentedItemDtos = itemsService.findAllByIsRentedTrue();
        }
        else{
            rentedItemDtos = itemsService.findAll();
        }

        return ResponseEntity.ok(rentedItemDtos);
    }

    @PostMapping
    ResponseEntity addRentedItem(@RequestBody RentedItemDto rentedItemDto) {
        itemsService.save(rentedItemDto);
        return ResponseEntity.ok().build();
    }
}
