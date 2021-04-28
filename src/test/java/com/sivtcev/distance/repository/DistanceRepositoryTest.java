package com.sivtcev.distance.repository;

import com.sivtcev.distance.model.City;
import com.sivtcev.distance.model.Distance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@DataJpaTest
public class DistanceRepositoryTest {

    @Autowired
    DistanceRepository distanceRepository;

    @Test
    void findMatrixDistanceTest() {
        City cityFrom = new City(1L, "from", 1D, 1D, null);
        City cityTo = new City(2L, "to", 2D, 2D, null);

        distanceRepository.save(new Distance(1L, cityFrom, cityTo, 4D));
        double result = distanceRepository.findMatrixDistanceByCitiesNames("from", "to");

        assertEquals("correct result", 4D, result);
    }
}
