package com.sivtcev.distance.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityController {

    @GetMapping("/city")
    public void getCityList(){

    }

    @PutMapping("/city")
    public void uploadCitiesFromXml(){

    }
}

