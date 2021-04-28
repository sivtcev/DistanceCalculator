package com.sivtcev.distance.service;

import com.sivtcev.distance.ServiceTestConfiguration;
import com.sivtcev.distance.api.response.CityResponse;
import com.sivtcev.distance.api.response.DataResponse;
import com.sivtcev.distance.model.City;
import com.sivtcev.distance.repository.CityRepository;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.fail;

@SpringBootTest(classes = ServiceTestConfiguration.class)
public class CityServiceTest {

    @Autowired
    private CityService cityService;
    @MockBean
    private CityRepository cityRepository;

    @Test
    void getCitiesListTest() {
        List<City> cityList = new ArrayList<>(3);
        cityList.add(new City(1L, "first", 1D, 1D, null));
        cityList.add(new City(2L, "second", 2D, 2D, null));
        cityList.add(new City(3L, "third", 3D, 3D, null));
        Mockito.when(cityRepository.findAll()).thenReturn(cityList);

        DataResponse<CityResponse> cityResponseList = cityService.getCitiesList();

        assertThat(cityResponseList.getData())
                .isNotNull()
                .size().isEqualTo(3);

        assertEquals("firs element is correct",
                new CityResponse(cityList.get(0)).getName(),
                cityResponseList.getData().get(0).getName());
        assertEquals("second element is correct",
                new CityResponse(cityList.get(1)).getName(),
                cityResponseList.getData().get(1).getName());
        assertEquals("third element is correct",
                new CityResponse(cityList.get(2)).getName(),
                cityResponseList.getData().get(2).getName());
    }

    @Test
    void uploadCitiesFromXMLTest() {
        try {
            File file = new File("src/test/resources/file.xml");
            FileInputStream input = new FileInputStream(file);
            MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "text/plain", IOUtils.toByteArray(input));
            cityService.uploadCitiesFromXml(multipartFile);
        } catch (Exception e){
            fail("Should not have thrown any exception, but: " + e.getLocalizedMessage());
        }
    }
}
