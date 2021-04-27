package com.sivtcev.distance.service;

import com.sivtcev.distance.api.response.CityResponse;
import com.sivtcev.distance.api.response.DataResponse;
import com.sivtcev.distance.repository.CityRepository;
import com.sivtcev.distance.exception.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Override
    public DataResponse<CityResponse> getCitiesList() {
        List<CityResponse> cityResponseList = cityRepository.findAll()
                .stream()
                .map(CityResponse::new)
                .collect(Collectors.toList());
        if (cityResponseList.isEmpty()) {
            throw new ResourceNotFoundException("no cities found");
        }
        return new DataResponse<>(cityResponseList);
    }

    @Override
    public void uploadCitiesFromXml() {

    }
}
