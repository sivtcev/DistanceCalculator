package com.sivtcev.distance.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DistanceController {

    @GetMapping("/distance")
    public void calculateDistance(){

    }
}
