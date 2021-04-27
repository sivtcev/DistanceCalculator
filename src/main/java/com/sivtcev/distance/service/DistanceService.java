package com.sivtcev.distance.service;

import com.sivtcev.distance.api.request.DistanceRequest;
import com.sivtcev.distance.api.response.DataResponse;
import com.sivtcev.distance.api.response.DistanceResponse;

public interface DistanceService {

    DataResponse<DistanceResponse> calculateDistance(DistanceRequest distanceRequest);
}
