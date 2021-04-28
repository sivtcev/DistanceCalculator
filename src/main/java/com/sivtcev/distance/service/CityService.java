package com.sivtcev.distance.service;

import com.sivtcev.distance.api.response.CityResponse;
import com.sivtcev.distance.api.response.DataResponse;
import org.springframework.web.multipart.MultipartFile;

public interface CityService {

    DataResponse<CityResponse> getCitiesList();

    void uploadCitiesFromXml(MultipartFile file);
}
