package com.sivtcev.distance.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sivtcev.distance.model.Distance;
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
    @JsonProperty(value = "distance_crowfright")
    private double distanceCrowfright;
    @JsonProperty(value = "distance_distance_matrix")
    private double distanceDistanceMatrix;

    public DistanceResponse(Distance distance) {
        this.cityFrom = distance.getFromCity().getName();
        this.cityTo = distance.getToCity().getName();
        this.distanceDistanceMatrix = distance.getDistance();
    }

}
