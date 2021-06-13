package com.mercadolivre.desafioquality.repository;

import com.mercadolivre.desafioquality.entity.District;
import com.mercadolivre.desafioquality.entity.Property;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Repository {
    public static List<Property> propertyRepo = new ArrayList<>();

    public static final Map<String, District> DISTRICT_REPO = Map.of(
            "Centro", new District("Centro", 5000.0),
            "Ingleses", new District("Ingleses", 4000.0)
    );
}
