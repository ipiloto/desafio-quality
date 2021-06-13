package com.mercadolivre.desafioquality.dto;

import com.mercadolivre.desafioquality.entity.Room;
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
