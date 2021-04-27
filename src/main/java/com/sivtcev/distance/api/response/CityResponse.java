package com.sivtcev.distance.api.response;

import com.sivtcev.distance.model.City;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityResponse {

    private long id;
    private String name;

    public CityResponse(City city) {
        this.id = city.getCity_id();
        this.name = city.getName();
    }

}
