package com.assignment.spring.service;

import com.assignment.spring.client.WeatherClient;
import com.assignment.spring.client.api.WeatherResponse;
import com.assignment.spring.entity.Weather;
import com.assignment.spring.mapper.WeatherMapper;
import com.assignment.spring.repository.WeatherRepository;
import lombok.val;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class WeatherServiceTest {

    private static final String CITY = "Vienna";

    private final WeatherClient weatherClient = mock(WeatherClient.class);
    private final WeatherMapper weatherMapper = mock(WeatherMapper.class);
    private final WeatherRepository weatherRepository = mock(WeatherRepository.class);
    private final WeatherService weatherService = new WeatherService(weatherClient, weatherMapper, weatherRepository);

    @Test
    void getAndStoreWeatherGetsWeatherAndStoresItInDB() {
        val weatherResponse = new WeatherResponse();
        when(weatherClient.getWeather(eq(CITY), any())).thenReturn(weatherResponse);
        val expectedWeather = new Weather();
        when(weatherMapper.toWeather(weatherResponse)).thenReturn(expectedWeather);

        val weather = weatherService.getAndStoreWeather(CITY);

        assertThat(weather).isEqualTo(expectedWeather);
        verify(weatherRepository).save(expectedWeather);
    }

}
