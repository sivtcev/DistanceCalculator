package com.sivtcev.distance.controller;

import com.sivtcev.distance.api.response.CityResponse;
import com.sivtcev.distance.api.response.DataResponse;
import com.sivtcev.distance.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping("/city")
    public ResponseEntity<DataResponse<CityResponse>> getCityList() {
        DataResponse<CityResponse> citiesResponseList = cityService.getCitiesList();
        return ResponseEntity.ok(citiesResponseList);
    }

    @PutMapping(value = "/city", produces = {"application/json", "application/xml"}, consumes = "multipart/form-data")
    public void uploadCitiesFromXml(@RequestParam(name = "file") MultipartFile file) {

    }
}

