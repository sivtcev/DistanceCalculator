package com.sivtcev.distance.service.emun;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CalculationType {

    CROWFLIGHT("crowflight"),
    DISTANCE_MATRIX("distance_matrix"),
    ALL("all");

    private final String name;

}
