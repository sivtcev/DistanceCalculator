package com.sivtcev.distance.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sivtcev.distance.ControllerTestConfiguration;
import com.sivtcev.distance.ServiceTestConfiguration;
import com.sivtcev.distance.api.response.CityResponse;
import com.sivtcev.distance.api.response.DataResponse;
import com.sivtcev.distance.service.CityService;
import com.sivtcev.distance.service.DistanceService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ControllerTestConfiguration.class)
@AutoConfigureMockMvc
public class CityControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CityService cityService;

    @Test
    @SneakyThrows
    void getCityListTest(){
        List<CityResponse> responseList = new ArrayList<>();
        responseList.add(new CityResponse(1L, "City"));
        Mockito.when(cityService.getCitiesList()).thenReturn(new DataResponse<>(responseList));

        mockMvc.perform(get("/city"))
                .andExpect(status().isOk());
    }
}
