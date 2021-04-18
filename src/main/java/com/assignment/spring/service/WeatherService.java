package com.assignment.spring.service;

import com.assignment.spring.client.WeatherClient;
import com.assignment.spring.entity.Weather;
import com.assignment.spring.mapper.WeatherMapper;
import com.assignment.spring.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherService {

    @Value("${openweathermap.app-id}")
    private String appId;
    private final WeatherClient weatherClient;
    private final WeatherMapper weatherMapper;
    private final WeatherRepository weatherRepository;

    public Weather getAndStoreWeather(String city) {
        val response = weatherClient.getWeather(city, appId);
        val weather = weatherMapper.toWeather(response);
        weatherRepository.save(weather);
        return weather;
    }

}
