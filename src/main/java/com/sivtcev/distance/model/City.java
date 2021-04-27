package com.sivtcev.distance.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long city_id;

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(50)")
    private String name;

    @Column(name = "latitude", nullable = false, columnDefinition = "POINT")
    private String latitude;

    @Column(name = "longitude", nullable = false, columnDefinition = "POINT")
    private String longitude;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Distance> distances;

}