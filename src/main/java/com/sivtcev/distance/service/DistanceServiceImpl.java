package com.sivtcev.distance.service;

import com.sivtcev.distance.api.request.DistanceRequest;
import com.sivtcev.distance.api.response.DataResponse;
import com.sivtcev.distance.api.response.DistanceResponse;
import com.sivtcev.distance.model.City;
import com.sivtcev.distance.repository.CityRepository;
import com.sivtcev.distance.repository.DistanceRepository;
import com.sivtcev.distance.service.emun.CalculationType;
import com.sivtcev.distance.exception.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DistanceServiceImpl implements DistanceService {

    private final DistanceRepository distanceRepository;
    private final CityRepository cityRepository;

    @Override
    public DataResponse<DistanceResponse> calculateDistance(DistanceRequest distanceRequest) {
        CalculationType calculationType = distanceRequest.getCalculationType();
        List<String> fromCities = distanceRequest.getFromCityList();
        List<String> toCities = distanceRequest.getToCityList();

        List<DistanceResponse> distanceResponseList;

        if (fromCities.isEmpty() || toCities.isEmpty() || toCities.size() != fromCities.size()) {
            throw new BadRequestException("invalid cities list");
        }

        switch (calculationType) {
            case CROW_FLIGHT:
                distanceResponseList = calculateCrowFlight(fromCities, toCities);
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

        return new DataResponse<>(distanceResponseList);
    }

    private List<DistanceResponse> calculateCrowFlight(List<String> fromCities, List<String> toCities) {
        List<DistanceResponse> distanceResponseList = new ArrayList<>();

        for (int i = 0; i < fromCities.size(); i++) {
            City cityFrom = cityRepository.getFirstByName(fromCities.get(i));
            City cityTo = cityRepository.getFirstByName(toCities.get(i));
            if (cityFrom == null || cityTo == null) {
                throw new ResourceNotFoundException("city not found");
            }
            if (cityFrom.getLatitude() == null || cityFrom.getLongitude() == null
                    || cityTo.getLatitude() == null || cityTo.getLongitude() == null) {
                throw new ResourceNotFoundException("not enough coordinates");
            }
            double distance = coordinateToDistance(
                    cityFrom.getLatitude(),
                    cityFrom.getLongitude(),
                    cityTo.getLatitude(),
                    cityTo.getLongitude());
            distanceResponseList.add(new DistanceResponse(fromCities.get(i), toCities.get(i), distance, null));
        }
        return distanceResponseList;
    }

    private List<DistanceResponse> calculateDistanceMatrix(List<String> fromCities, List<String> toCities) {
        List<DistanceResponse> distanceResponseList = new ArrayList<>();

        for (int i = 0; i < fromCities.size(); i++) {
            String cityFrom = fromCities.get(i);
            String cityTo = toCities.get(i);
            double distance = cityFrom.equals(cityTo) ? 0D : distanceRepository.findMatrixDistanceByCitiesNames(cityFrom, cityTo);
            distanceResponseList.add(new DistanceResponse(cityFrom, cityTo, null, distance));
        }
        System.gc();
        return distanceResponseList;
    }

    private List<DistanceResponse> calculateAll(List<String> fromCities, List<String> toCities) {
        List<DistanceResponse> distanceResponseList = calculateCrowFlight(fromCities, toCities);
        for (DistanceResponse distanceResponse : distanceResponseList) {
            distanceResponse.setDistanceMatrix(distanceRepository.findMatrixDistanceByCitiesNames(
                    distanceResponse.getCityFrom(),
                    distanceResponse.getCityTo()));
        }
        return distanceResponseList;
    }

    private double coordinateToDistance(double latitudeFrom, double longitudeFrom, double latitudeTo, double longitudeTo) {
        final double EARTH_RADIUS = 6_371D;

        // convert from degrees to radians
        latitudeFrom = Math.toRadians(latitudeFrom);
        longitudeFrom = Math.toRadians(longitudeFrom);
        latitudeTo = Math.toRadians(latitudeTo);
        longitudeTo = Math.toRadians(longitudeTo);

        // implement Haversine formula
        double longitudeDiff = longitudeFrom - longitudeTo;
        double latitudeDiff = latitudeFrom - latitudeTo;
        double a = Math.pow(Math.sin(latitudeDiff / 2), 2)
                + Math.cos(latitudeFrom) * Math.cos(latitudeTo)
                * Math.pow(Math.sin(longitudeDiff / 2), 2);
        double c = 2 * Math.asin(Math.sqrt(a));

        // multiply on earth radius
        return c * EARTH_RADIUS;
    }
}
