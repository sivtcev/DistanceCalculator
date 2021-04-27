package com.sivtcev.distance.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sivtcev.distance.service.emun.CalculationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DistanceRequest {

    @JsonProperty("calculation_type")
    private CalculationType calculationType;
    @JsonProperty("from_city_list")
    private List<String> fromCityList;
    @JsonProperty("to_city_list")
    private List<String> toCityList;
}
