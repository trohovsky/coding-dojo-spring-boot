package com.assignment.spring.controller;

import com.assignment.spring.dto.WeatherDto;
import com.assignment.spring.mapper.WeatherMapper;
import com.assignment.spring.service.WeatherService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class WeatherController {

    protected static final String WEATHER_PATH = "/weather";

    private final WeatherService weatherService;
    private final WeatherMapper weatherMapper;

    @GetMapping(WEATHER_PATH)
    public WeatherDto getWeather(@RequestParam String city) {
        return weatherMapper.toWeatherDto(weatherService.getAndStoreWeather(city));
    }

}
