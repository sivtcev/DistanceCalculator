package com.sivtcev.distance.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DistanceResponse {

    @JsonProperty(value = "city_from")
    private String cityFrom;
    @JsonProperty(value = "city_to")
    private String cityTo;
    @JsonProperty(value = "distance_crow_fright")
    private Double distanceCrowFright;
    @JsonProperty(value = "distance_matrix")
    private Double distanceMatrix;

}
