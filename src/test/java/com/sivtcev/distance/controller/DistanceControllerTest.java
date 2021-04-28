package com.sivtcev.distance.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sivtcev.distance.ControllerTestConfiguration;
import com.sivtcev.distance.api.request.DistanceRequest;
import com.sivtcev.distance.api.response.DataResponse;
import com.sivtcev.distance.api.response.DistanceResponse;
import com.sivtcev.distance.service.DistanceService;
import com.sivtcev.distance.service.emun.CalculationType;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ControllerTestConfiguration.class)
@AutoConfigureMockMvc
public class DistanceControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private DistanceService distanceService;

    @Test
    @SneakyThrows
    void calculateDistanceTest() {

        List<String> cityFrom = new ArrayList<>();
        cityFrom.add("cityFrom");

        List<String> cityTo = new ArrayList<>();
        cityTo.add("cityTo");

        DistanceRequest distanceRequest = new DistanceRequest(
                CalculationType.ALL,
                cityFrom,
                cityTo);

        DistanceResponse distanceResponse = new DistanceResponse(
                "cityFrom",
                "cityTo",
                1D,
                2D);

        List<DistanceResponse> distanceResponseList = new ArrayList<>();
        distanceResponseList.add(distanceResponse);

        String distanceRequestJSON = objectMapper.writeValueAsString(distanceRequest);
        Mockito.when(distanceService.calculateDistance(distanceRequest)).
                thenReturn(new DataResponse<>(distanceResponseList));

        mockMvc.perform(post("/distance")
                .contentType(MediaType.APPLICATION_JSON)
                .content(distanceRequestJSON))
                .andExpect(status().isOk());
    }
}
