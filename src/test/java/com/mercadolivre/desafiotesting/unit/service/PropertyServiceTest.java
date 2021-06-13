package com.mercadolivre.desafiotesting.unit.service;

import com.mercadolivre.desafiotesting.entity.Property;
import com.mercadolivre.desafiotesting.entity.Room;
import com.mercadolivre.desafiotesting.exception.DistrictNotFoundException;
import com.mercadolivre.desafiotesting.repository.Repository;
import com.mercadolivre.desafiotesting.service.PropertyService;
import com.mercadolivre.desafiotesting.util.PropertyCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
class PropertyServiceTest {

    @InjectMocks
    private PropertyService propertyService;

    @BeforeAll
    static void Setup() {
        Repository.propertyRepo.add(PropertyCreator.createValidProperty());
    }

    @Test
    @DisplayName("Saves a new Property when successful")
    void newProperty_SavesNewProperty_WhenSuccessful() {
        Property property = PropertyCreator.createValidProperty();

        Assertions.assertThatCode(() -> propertyService.newProperty(property))
                .doesNotThrowAnyException();

        propertyService.newProperty(property);

        Property propertyFound = propertyService.findPropertyByName(property.getProp_name());

        Assertions.assertThat(propertyFound).isNotNull();
    }

    @Test
    @DisplayName("Validate if the District exists when successful")
    void validateDistrict_ValidateIfDistrictExists_WhenSuccessful() {
        Assertions.assertThatCode(() -> propertyService.validateDistrict("Centro"))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Validate if is throwing a exception when the District when does not exists")
    void validateDistrict_ValidateIfDistrictExists_WhenNotExists() {
        Assertions.assertThatExceptionOfType(DistrictNotFoundException.class)
                .isThrownBy(() -> propertyService.validateDistrict("TestNotExistsDistrict"));
    }

    @Test
    @DisplayName("Return the total of Property's square meters when successful")
    void totalSquareMeters_ReturnTotalPropertySquareMeters_WhenSuccessful() {
        Property property = PropertyCreator.createValidProperty();

        Double totalSquareMeters = propertyService.totalSquareMeters(property.getProp_name());

        Assertions.assertThat(totalSquareMeters).isEqualTo(837.0);
    }

    @Test
    @DisplayName("Return the Property's value when successful")
    void propertyValue_ReturnThePropertyValue_WhenSuccessful() {
        Property property = PropertyCreator.createValidProperty();

        Double totalSquareMeters = propertyService.propertyValue(property.getProp_name());

        Assertions.assertThat(totalSquareMeters).isEqualTo(4185000.0);
    }

    @Test
    @DisplayName("Return a Property Found by Name")
    void findPropertyByName_ReturnAPropertyFoundByName_WhenSuccessful() {
        Property property = PropertyCreator.createValidProperty();

        Property propertyFound = propertyService.findPropertyByName(property.getProp_name());

        Assertions.assertThat(propertyFound).isNotNull();
    }

    @Test
    @DisplayName("Return the Property's greatest room when successful")
    void greatestRoom_ReturnTheGreatestPropertyRoom_WhenSuccessful() {
        Property property = PropertyCreator.createValidProperty();

        Room room = propertyService.greatestRoom(property.getProp_name());

        Assertions.assertThat(room).isNotNull().isEqualTo(PropertyCreator.createGreatestRoom());

    }

    @Test
    @DisplayName("Return the Property's rooms when successful")
    void rooms_ReturnThePropertyRooms_WhenSuccessful() {
        Property property = PropertyCreator.createValidProperty();

        List<Room> rooms = propertyService.getPropertyRoomsWithSquareMeters(property.getProp_name()).getRooms();

        Assertions.assertThat(rooms).isNotNull()
                .isNotEmpty()
                .containsAll(PropertyCreator.createRooms());

    }

    @Test
    @DisplayName("Return the Room's total square meters is correct when successful")
    void totalSquareMeters_ReturnTheRoomTotalSquareMeter_WhenSuccessful(){
        Property property = PropertyCreator.createValidProperty();

        List<Room> rooms = propertyService.getPropertyRoomsWithSquareMeters(property.getProp_name()).getRooms();

        Room room = rooms.get(rooms.indexOf(PropertyCreator.createGreatestRoom()));

        Assertions.assertThat(room.getSquareMeters()).isEqualTo(825.0);

    }
}