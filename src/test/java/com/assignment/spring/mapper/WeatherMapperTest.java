package com.assignment.spring.mapper;

import com.assignment.spring.client.api.Main;
import com.assignment.spring.client.api.Sys;
import com.assignment.spring.client.api.WeatherResponse;
import com.assignment.spring.dto.WeatherDto;
import com.assignment.spring.entity.Weather;
import lombok.val;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WeatherMapperTest {

    protected static final Integer ID = 1;
    protected static final String CITY = "Vienna";
    protected static final String COUNTRY = "Austria";
    protected static final double TEMPERATURE = 23.5;

    private final WeatherMapper weatherMapper = new WeatherMapper();

    @Test
    void toWeatherDtoMapsWeatherToWeatherDto() {
        val weather = new Weather();
        weather.setId(ID);
        weather.setCity(CITY);
        weather.setCountry(COUNTRY);
        weather.setTemperature(TEMPERATURE);
        val expectedWeahterDto = new WeatherDto(ID, CITY, COUNTRY, TEMPERATURE);

        val weatherDto = weatherMapper.toWeatherDto(weather);

        assertThat(weatherDto).isEqualTo(expectedWeahterDto);
    }

    @Test
    void toWeatherDtoReturnsNullForNullWeather() {
        val weatherDto = weatherMapper.toWeatherDto(null);

        assertThat(weatherDto).isEqualTo(null);
    }

    @Test
    void toWeatherMapsWeatherResponseToWeather() {
        val weatherResponse = new WeatherResponse();
        weatherResponse.setName(CITY);
        val sys = new Sys();
        sys.setCountry(COUNTRY);
        weatherResponse.setSys(sys);
        val main = new Main();
        main.setTemp(TEMPERATURE);
        weatherResponse.setMain(main);
        val expectedWeather = new Weather();
        expectedWeather.setCity(CITY);
        expectedWeather.setCountry(COUNTRY);
        expectedWeather.setTemperature(TEMPERATURE);

        val weather = weatherMapper.toWeather(weatherResponse);

        assertThat(weather).isEqualTo(expectedWeather);
    }

    @Test
    void toWeatherReturnsNullForNullWeatherResponse() {
        val weather = weatherMapper.toWeather(null);

        assertThat(weather).isEqualTo(null);
    }

}
