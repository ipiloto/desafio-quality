package com.mercadolivre.desafiotesting.entity;

import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Data
@Builder
public class Property {

    @NotBlank(message = "O nome da propriedade não pode estar vazio.")
    @Pattern(regexp = "^[A-Z].*", message = "O nome da propriedade deve começar com uma letra maiúscula." )
    @Size(max = 30, message = "O comprimento do nome não pode exceder 30 caracters.")
    String prop_name;

    @NotBlank(message = "O bairro não pode estar vazio.")
    @Size(max = 45, message = "O comprimento do bairro não pode exceder 45 caracteres.")
    String prop_district;

    @Valid
    @NotNull(message = "O campo rooms não pode estar vazio.")
    List<Room> rooms;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property that = (Property) o;
        return Objects.equals(prop_name, that.prop_name);
    }

}
