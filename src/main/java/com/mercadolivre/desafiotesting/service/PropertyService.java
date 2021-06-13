package com.mercadolivre.desafiotesting.service;

import com.mercadolivre.desafiotesting.dto.RoomListDTO;
import com.mercadolivre.desafiotesting.entity.District;
import com.mercadolivre.desafiotesting.entity.Property;
import com.mercadolivre.desafiotesting.entity.Room;
import com.mercadolivre.desafiotesting.exception.DistrictNotFoundException;
import com.mercadolivre.desafiotesting.exception.PropertyNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import static com.mercadolivre.desafiotesting.repository.Repository.*;


@Service
public class PropertyService {

    public void newProperty(Property propertyDTO) {
        validateDistrict(propertyDTO.getProp_district());
        propertyRepo.add(propertyDTO);
    }

    public void validateDistrict(String districtName) {
        if(DISTRICT_REPO.get(districtName) == null) throw new DistrictNotFoundException();
    }

    public Double totalSquareMeters(String propertyName) {
        Property property = findPropertyByName(propertyName);
        return property.getRooms().stream().mapToDouble(Room::getSquareMeters).sum();
    }

    public Double propertyValue(String propertyName) {
        Property property = findPropertyByName(propertyName);
        District district = DISTRICT_REPO.get(property.getProp_district());
        return totalSquareMeters(propertyName) * district.getPricePerSquareMeter();
    }

    public Property findPropertyByName(String propertyName) {
        Property propertyToFind = Property.builder().prop_name(propertyName).build();
        int index = propertyRepo.indexOf(propertyToFind);
        if(index < 0) throw new PropertyNotFoundException();
        return propertyRepo.get(index);
    }

    public Room greatestRoom(String propertyName) {
        Property property = findPropertyByName(propertyName);
        return property.getRooms().stream().max(Comparator.comparing(Room::getSquareMeters)).orElseThrow(NoSuchElementException::new);
    }

    public RoomListDTO getPropertyRoomsWithSquareMeters(String propertyName){
        return new RoomListDTO(findPropertyByName(propertyName).getRooms());
    }
}
