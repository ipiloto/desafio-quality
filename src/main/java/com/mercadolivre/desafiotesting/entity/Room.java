package com.mercadolivre.desafiotesting.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@Builder
@AllArgsConstructor
public class Room {

    @NotBlank(message = "O campo não pode estar vazio.")
    @Pattern(regexp = "^[A-Z].*", message = "O nome do cômodo deve começar com uma letra maiúscula." )
    @Size(max = 30, message = "O comprimento do cômodo não pode exceder 30 caracteres.")
    String room_name;

    @NotNull(message = "A largura do cômodo não pode estar vazia.")
    @DecimalMax(value = "25.0", message = "A largura máxima permitida por cômodo é de 25 metros.")
    @DecimalMin(value = "0.01", message = "A largura do cômodo deve ser maior que zero.")
    Double room_width;

    @NotNull(message = "O comprimento do cômodo não pode estar vazio.")
    @DecimalMax(value = "33.0", message = "O comprimento máximo permitido por cômodo é de 33 metros.")
    @DecimalMin(value = "0.01", message = "O comprimento do cômodo deve ser maior que zero.")
    Double room_length;

    public Double getSquareMeters(){
        return this.room_width * this.room_length;
    }

}
