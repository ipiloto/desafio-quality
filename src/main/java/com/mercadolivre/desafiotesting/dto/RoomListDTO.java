package com.mercadolivre.desafiotesting.dto;

import com.mercadolivre.desafiotesting.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomListDTO {

    List<Room> rooms;

}
