package com.mercadolivre.desafiotesting.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class District {

    String name;
    Double pricePerSquareMeter;

}
