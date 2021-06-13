package com.mercadolivre.desafioquality.controller;

import com.mercadolivre.desafioquality.dto.RoomListDTO;
import com.mercadolivre.desafioquality.entity.Property;
import com.mercadolivre.desafioquality.entity.Room;
import com.mercadolivre.desafioquality.service.PropertyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/property")
public class PropertyController {

    private final PropertyService propertyService;

    @PostMapping
    public ResponseEntity<Void> newProperty(@RequestBody @Valid Property propertyDTO){
        propertyService.newProperty(propertyDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    @RequestMapping("{propertyName}/totalSquareMeters")
    public ResponseEntity<Double> totalSquareMeters(@PathVariable String propertyName) {
        return new ResponseEntity<>(propertyService.totalSquareMeters(propertyName), HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("{propertyName}/propertyValue")
    public ResponseEntity<Double> propertyValue(@PathVariable String propertyName){
        return new ResponseEntity<>(propertyService.propertyValue(propertyName), HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("{propertyName}/greatestRoom")
    public ResponseEntity<Room> greatestRoom(@PathVariable String propertyName){
        return new ResponseEntity<>(propertyService.greatestRoom(propertyName), HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("{propertyName}/rooms")
    public ResponseEntity<RoomListDTO> rooms(@PathVariable String propertyName){
        return new ResponseEntity<>(propertyService.getPropertyRoomsWithSquareMeters(propertyName), HttpStatus.OK);
    }

}
