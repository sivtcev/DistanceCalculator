package com.sivtcev.distance.controller;

import com.sivtcev.distance.api.request.DistanceRequest;
import com.sivtcev.distance.api.response.DataResponse;
import com.sivtcev.distance.api.response.DistanceResponse;
import com.sivtcev.distance.service.DistanceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class DistanceController {

    final private DistanceService distanceService;

    @GetMapping("/distance")
    public ResponseEntity<DataResponse<DistanceResponse>> calculateDistance(@RequestBody DistanceRequest distanceRequest) {
        return ResponseEntity.ok(distanceService.calculateDistance(distanceRequest));
    }
}
