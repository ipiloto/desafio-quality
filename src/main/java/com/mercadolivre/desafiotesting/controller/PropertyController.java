package com.mercadolivre.desafiotesting.controller;

import com.mercadolivre.desafiotesting.entity.Property;
import com.mercadolivre.desafiotesting.entity.Room;
import com.mercadolivre.desafiotesting.service.PropertyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/property")
public class PropertyController {

    private final PropertyService propertyService;

    @PostMapping
    public void newProperty(@RequestBody @Valid Property propertyDTO){
        this.propertyService.newProperty(propertyDTO);
    }

    @GetMapping
    @RequestMapping("{propertyName}/totalSquareMeters")
    public Double totalSquareMeters(@PathVariable String propertyName) {
        return this.propertyService.totalSquareMeters(propertyName);
    }

    @GetMapping
    @RequestMapping("{propertyName}/propertyValue")
    public Double propertyValue(@PathVariable String propertyName){
        return this.propertyService.propertyValue(propertyName);
    }

    @GetMapping
    @RequestMapping("{propertyName}/greatestRoom")
    public Room greatestRoom(@PathVariable String propertyName){
        return this.propertyService.greatestRoom(propertyName);
    }

    @GetMapping
    @RequestMapping("{propertyName}/rooms")
    public List<Room> rooms(@PathVariable String propertyName){
        return this.propertyService.getPropertyRoomsWithSquareMeters(propertyName);
    }

}
