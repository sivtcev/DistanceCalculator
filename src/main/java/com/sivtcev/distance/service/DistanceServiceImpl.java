package com.sivtcev.distance.service;

import com.sivtcev.distance.api.request.DistanceRequest;
import com.sivtcev.distance.api.response.DataResponse;
import com.sivtcev.distance.api.response.DistanceResponse;
import com.sivtcev.distance.repository.DistanceRepository;
import com.sivtcev.distance.service.emun.CalculationType;
import com.sivtcev.distance.exception.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DistanceServiceImpl implements DistanceService{

    private final DistanceRepository distanceRepository;

    @Override
    public DataResponse<DistanceResponse> calculateDistance(DistanceRequest distanceRequest) {
        CalculationType calculationType = distanceRequest.getCalculationType();
        List<String> fromCities = distanceRequest.getFromCityList();
        List<String> toCities = distanceRequest.getToCityList();

        List<DistanceResponse> distanceResponseList;

        if(fromCities.isEmpty() || toCities.isEmpty() || toCities.size() != fromCities.size()){
            throw new BadRequestException("invalid cities list");
        }

        switch (calculationType) {
            case CROWFLIGHT:
                distanceResponseList = calculateCrowflight(fromCities, toCities);
                break;
            case DISTANCE_MATRIX:
                distanceResponseList = calculateDistanceMatrix(fromCities, toCities);
                break;
            case ALL:
                distanceResponseList = calculateAll(fromCities, toCities);
                break;
            default:
                throw new BadRequestException("calculation type incorrect");
        }

        return null;
    }

    private List<DistanceResponse> calculateCrowflight(List<String> fromCities, List<String> toCities){
        return null;
    }

    private List<DistanceResponse> calculateDistanceMatrix(List<String> fromCities, List<String> toCities){
        return null;
    }

    private List<DistanceResponse> calculateAll(List<String> fromCities, List<String> toCities){
        return null;
    }
}
