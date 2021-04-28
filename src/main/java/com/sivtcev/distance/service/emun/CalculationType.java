package com.sivtcev.distance.service.emun;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CalculationType {

    CROW_FLIGHT("crow_flight"),
    DISTANCE_MATRIX("distance_matrix"),
    ALL("all");

    private final String name;

}
