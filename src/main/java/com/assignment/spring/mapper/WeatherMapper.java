package com.assignment.spring.mapper;

import com.assignment.spring.client.api.WeatherResponse;
import com.assignment.spring.dto.WeatherDto;
import com.assignment.spring.entity.Weather;
import lombok.val;
import org.springframework.stereotype.Component;

@Component
public class WeatherMapper {

    public WeatherDto toWeatherDto(Weather weather) {
        if (weather == null) {
            return null;
        }
        return new WeatherDto(weather.getId(), weather.getCity(), weather.getCountry(), weather.getTemperature());
    }

    public Weather toWeather(WeatherResponse response) {
        if (response == null) {
            return null;
        }
        val weather = new Weather();
        weather.setCity(response.getName());
        weather.setCountry(response.getSys().getCountry());
        weather.setTemperature(response.getMain().getTemp());
        return weather;
    }

}
