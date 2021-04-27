package com.sivtcev.distance.service;

import com.sivtcev.distance.api.response.CityResponse;
import com.sivtcev.distance.api.response.DataResponse;

public interface CityService {

    DataResponse<CityResponse> getCitiesList();

    void uploadCitiesFromXml();
}
