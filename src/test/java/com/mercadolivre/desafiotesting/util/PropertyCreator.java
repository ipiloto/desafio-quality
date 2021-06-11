package com.mercadolivre.desafiotesting.util;

import com.mercadolivre.desafiotesting.entity.Property;
import com.mercadolivre.desafiotesting.entity.Room;

import java.util.ArrayList;
import java.util.List;

public class PropertyCreator {

    public static Property createValidProperty() {
        return Property.builder()
                .prop_name("Casa Azul")
                .prop_district("Centro")
                .rooms(createRooms())
                .build();
    }

    public static List<Room> createRooms() {
        List<Room> rooms = new ArrayList<>();
        rooms.add(createGreatestRoom());
        rooms.add(createSmallerRoom());
        return rooms;
    }

    public static Room createGreatestRoom(){
        return Room.builder().room_name("Sala").room_width(25.0).room_length(33.0).build();
    }

    public static Room createSmallerRoom(){
        return Room.builder().room_name("Quarto").room_width(4.0).room_length(3.0).build();
    }
}
