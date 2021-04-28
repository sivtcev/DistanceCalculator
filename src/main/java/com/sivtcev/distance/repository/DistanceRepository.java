package com.sivtcev.distance.repository;

import com.sivtcev.distance.model.Distance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DistanceRepository extends JpaRepository<Distance, Long> {

    @Query(value = "SELECT distance FROM distance " +
            "WHERE (fromCity.name = :cityFrom AND toCity.name= :cityTo) " +
            "OR (fromCity.name = :cityTo AND toCity.name= :cityFrom)")
    double findMatrixDistanceByCitiesNames(@Param("cityFrom") String cityFrom, @Param("cityTo") String cityTo);

}
