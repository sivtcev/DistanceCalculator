package com.sivtcev.distance.repository;

import com.sivtcev.distance.model.City;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@DataJpaTest
public class CityRepositoryTest {

    @Autowired
    CityRepository cityRepository;

    @Test
    void getCityByNameTest(){
        cityRepository.save(new City(1L, "city", 1D, 1D, null));
        cityRepository.save(new City(2L, "city", 2D, 2D, null));

        City result = cityRepository.getFirstByName("city");

        assertEquals("correct result", 1L, result.getCityId());
    }
}
