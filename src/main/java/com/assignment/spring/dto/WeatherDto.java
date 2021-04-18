package com.assignment.spring.dto;

import lombok.Value;

@Value
public class WeatherDto {

    Integer id;
    String city;
    String country;
    Double temperature;

}
