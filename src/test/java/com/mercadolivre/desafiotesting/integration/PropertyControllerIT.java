package com.mercadolivre.desafiotesting.integration;

import com.mercadolivre.desafiotesting.dto.RoomListDTO;
import com.mercadolivre.desafiotesting.entity.Room;
import com.mercadolivre.desafiotesting.service.PropertyService;
import com.mercadolivre.desafiotesting.util.PropertyCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureTestDatabase                                                                 //Talvez seja util um dia
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)             //Talvez seja util um dia
class PropertyControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private PropertyService propertyService;

    @Test
    @DisplayName("Saves a new Property when successful")
    void newProperty_SavesNewProperty_WhenSuccessful() {
        ResponseEntity<Void> entity = testRestTemplate.postForEntity("/property", PropertyCreator.createValidProperty(), Void.class);

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("Return the total of Property's square meters when successful")
    void totalSquareMeters_ReturnTotalPropertySquareMeters_WhenSuccessful() {
        propertyService.newProperty(PropertyCreator.createValidProperty());

        String url = String.format("/property/%s/totalSquareMeters", "Casa Azul");

        ResponseEntity<Double> entity = testRestTemplate.getForEntity(url, Double.class);

        Assertions.assertThat(entity.getBody()).isEqualTo(837.0);
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("Return the Property's value when successful")
    void propertyValue_ReturnThePropertyValue_WhenSuccessful() {
        propertyService.newProperty(PropertyCreator.createValidProperty());

        String url = String.format("/property/%s/propertyValue", "Casa Azul");

        ResponseEntity<Double> entity = testRestTemplate.getForEntity(url, Double.class);

        Assertions.assertThat(entity.getBody()).isEqualTo(4185000.0);
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("Return the Property's greatest room when successful")
    void greatestRoom_ReturnTheGreatestPropertyRoom_WhenSuccessful() {
        propertyService.newProperty(PropertyCreator.createValidProperty());

        String url = String.format("/property/%s/greatestRoom", "Casa Azul");

        ResponseEntity<Room> entity = testRestTemplate.getForEntity(url, Room.class);

        Assertions.assertThat(entity.getBody()).isEqualTo(PropertyCreator.createGreatestRoom());
        Assertions.assertThat(entity.getBody()).isNotEqualTo(PropertyCreator.createSmallerRoom());

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("Return the Property's rooms when successful")
    void rooms_ReturnThePropertyRooms_WhenSuccessful() {
        propertyService.newProperty(PropertyCreator.createValidProperty());

        String url = String.format("/property/%s/rooms", "Casa Azul");

        ResponseEntity<RoomListDTO> entity = testRestTemplate.getForEntity(url, RoomListDTO.class);

        Assertions.assertThat(entity.getBody()).isNotNull();

        Assertions.assertThat(entity.getBody().getRooms()).isNotNull()
                .isNotEmpty()
                .containsAll(PropertyCreator.createRooms());

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

}
