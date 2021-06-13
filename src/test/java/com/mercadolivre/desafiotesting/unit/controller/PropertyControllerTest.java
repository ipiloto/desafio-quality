package com.mercadolivre.desafiotesting.unit.controller;

import com.mercadolivre.desafiotesting.controller.PropertyController;
import com.mercadolivre.desafiotesting.dto.RoomListDTO;
import com.mercadolivre.desafiotesting.entity.Room;
import com.mercadolivre.desafiotesting.service.PropertyService;
import com.mercadolivre.desafiotesting.util.PropertyCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class PropertyControllerTest {

    @InjectMocks
    private PropertyController propertyController;

    @Mock
    private PropertyService propertyServiceMock;

    @BeforeEach
    void setup() {

        BDDMockito.when(propertyServiceMock.findPropertyByName(ArgumentMatchers.any()))
                .thenReturn(PropertyCreator.createValidProperty());

        BDDMockito.doNothing().when(propertyServiceMock).newProperty(ArgumentMatchers.any());

        BDDMockito.when(propertyServiceMock.totalSquareMeters(ArgumentMatchers.any()))
                .thenReturn(837.0);

        BDDMockito.when(propertyServiceMock.propertyValue(ArgumentMatchers.any()))
                .thenReturn(4185000.0);

        BDDMockito.when(propertyServiceMock.greatestRoom(ArgumentMatchers.any()))
                .thenReturn(PropertyCreator.createGreatestRoom());

        BDDMockito.when(propertyServiceMock.getPropertyRoomsWithSquareMeters(ArgumentMatchers.any()))
                .thenReturn(new RoomListDTO(PropertyCreator.createRooms()));
    }

    @Test
    @DisplayName("Saves a new Property when successful")
    void newProperty_SavesNewProperty_WhenSuccessful() {
        Assertions.assertThatCode(() -> propertyController.newProperty(PropertyCreator.createValidProperty()))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = propertyController.newProperty(PropertyCreator.createValidProperty());

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("Return the total of Property's square meters when successful")
    void totalSquareMeters_ReturnTotalPropertySquareMeters_WhenSuccessful() {
        ResponseEntity<Double> entity = propertyController.totalSquareMeters("Casa Azul");

        Assertions.assertThat(entity.getBody()).isEqualTo(837.0);
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("Return the Property's value when successful")
    void propertyValue_ReturnThePropertyValue_WhenSuccessful() {
        ResponseEntity<Double> entity = propertyController.propertyValue("Casa Azul");

        Assertions.assertThat(entity.getBody()).isEqualTo(4185000.0);
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("Return the Property's greatest room when successful")
    void greatestRoom_ReturnTheGreatestPropertyRoom_WhenSuccessful() {
        ResponseEntity<Room> entity = propertyController.greatestRoom("Casa Azul");

        Assertions.assertThat(entity.getBody()).isEqualTo(PropertyCreator.createGreatestRoom());
        Assertions.assertThat(entity.getBody()).isNotEqualTo(PropertyCreator.createSmallerRoom());

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("Return the Property's rooms when successful")
    void rooms_ReturnThePropertyRooms_WhenSuccessful() {
        ResponseEntity<RoomListDTO> entity = propertyController.rooms("Casa Azul");

        Assertions.assertThat(entity.getBody()).isNotNull();

        Assertions.assertThat(entity.getBody().getRooms()).isNotNull()
                .isNotEmpty()
                .containsAll(PropertyCreator.createRooms());

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

}