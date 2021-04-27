package com.sivtcev.distance.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "distance")
public class Distance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long distance_id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "from_city", nullable = false, columnDefinition = "BIGINT")
    private City fromCity;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "to_city", nullable = false, columnDefinition = "BIGINT")
    private City toCity;

    @Column(name = "distance", columnDefinition = "DOUBLE")
    private double distance;

}
