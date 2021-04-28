package com.sivtcev.distance.service;

import com.sivtcev.distance.ServiceTestConfiguration;
import com.sivtcev.distance.api.request.DistanceRequest;
import com.sivtcev.distance.api.response.DataResponse;
import com.sivtcev.distance.api.response.DistanceResponse;
import com.sivtcev.distance.model.City;
import com.sivtcev.distance.repository.CityRepository;
import com.sivtcev.distance.repository.DistanceRepository;
import com.sivtcev.distance.service.emun.CalculationType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest(classes = ServiceTestConfiguration.class)
public class DistanceServiceTest {

    @Autowired
    DistanceService distanceService;
    @MockBean
    DistanceRepository distanceRepository;
    @MockBean
    CityRepository cityRepository;

    private static DistanceRequest distanceRequest;

    @BeforeAll
    static void init() {

        List<String> cityFromList = new ArrayList<>();
        cityFromList.add("FromOne");
        cityFromList.add("FromTwo");

        List<String> cityToList = new ArrayList<>();
        cityToList.add("ToOne");
        cityToList.add("ToTwo");

        distanceRequest = new DistanceRequest(
                CalculationType.ALL,
                cityFromList,
                cityToList
        );
    }

    @BeforeEach
    void setUp() {

        Mockito.reset(distanceRepository);
        Mockito.reset(cityRepository);

        Mockito.when(cityRepository.getFirstByName("FromOne")).thenReturn(new City(
                1L,
                "FromOne",
                0D,
                1D,
                null
        ));
        Mockito.when(cityRepository.getFirstByName("FromTwo")).thenReturn(new City(
                2L,
                "FromTwo",
                1D,
                2D,
                null
        ));
        Mockito.when(cityRepository.getFirstByName("ToOne")).thenReturn(new City(
                3L,
                "ToOne",
                3D,
                4D,
                null
        ));
        Mockito.when(cityRepository.getFirstByName("ToTwo")).thenReturn(new City(
                4L,
                "ToTwo",
                5D,
                6D,
                null
        ));

        Mockito.when(distanceRepository.findMatrixDistanceByCitiesNames("FromOne", "ToOne")).thenReturn(1D);
        Mockito.when(distanceRepository.findMatrixDistanceByCitiesNames("FromTwo", "ToTwo")).thenReturn(2D);
    }

    @Test
    void calculateCowFlightDistanceTest() {

        distanceRequest.setCalculationType(CalculationType.CROW_FLIGHT);
        DataResponse<DistanceResponse> dataResponse = distanceService.calculateDistance(distanceRequest);

        assertThat(dataResponse.getData())
                .isNotNull()
                .size().isEqualTo(2);

        assertEquals("firs element is correct",
                471.65228849900194D,
                dataResponse.getData().get(0).getDistanceCrowFright());
        assertEquals("second element is correct",
                628.5181684612493D,
                dataResponse.getData().get(1).getDistanceCrowFright());
    }

    @Test
    void calculateMatrixDistanceTest() {
        distanceRequest.setCalculationType(CalculationType.DISTANCE_MATRIX);
        DataResponse<DistanceResponse> dataResponse = distanceService.calculateDistance(distanceRequest);

        assertThat(dataResponse.getData())
                .isNotNull()
                .size().isEqualTo(2);

        assertEquals("firs element is correct",
                1D,
                dataResponse.getData().get(0).getDistanceMatrix());
        assertEquals("second element is correct",
                2D,
                dataResponse.getData().get(1).getDistanceMatrix());
    }

    @Test
    void calculateBothDistanceTest() {
        DataResponse<DistanceResponse> dataResponse = distanceService.calculateDistance(distanceRequest);

        assertThat(dataResponse.getData())
                .isNotNull()
                .size().isEqualTo(2);

        assertEquals("firs element is correct crow fight",
                471.65228849900194D,
                dataResponse.getData().get(0).getDistanceCrowFright());
        assertEquals("second element is correct crow fight",
                628.5181684612493D,
                dataResponse.getData().get(1).getDistanceCrowFright());
        assertEquals("firs element is correct matrix",
                1D,
                dataResponse.getData().get(0).getDistanceMatrix());
        assertEquals("second element is correct matrix",
                2D,
                dataResponse.getData().get(1).getDistanceMatrix());
    }

}
