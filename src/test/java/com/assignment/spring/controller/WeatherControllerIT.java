package com.assignment.spring.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static com.assignment.spring.controller.WeatherController.WEATHER_PATH;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class WeatherControllerIT {

    protected static final String CITY_REQUEST_PARAM = "city";

    @Autowired
    private MockMvc mvc;

    @Test
    void getWeatherReturnsOk() throws Exception {
        mvc.perform(get(WEATHER_PATH).param(CITY_REQUEST_PARAM, "Vienna"))
                .andExpect(status().isOk());
    }

    @Test
    void getWeatherReturnsBadRequestForEmptyCity() throws Exception {
        mvc.perform(get(WEATHER_PATH).param(CITY_REQUEST_PARAM, ""))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getWeatherReturnsNotFoundForNonExistingCity() throws Exception {
        mvc.perform(get(WEATHER_PATH).param(CITY_REQUEST_PARAM, "xyz"))
                .andExpect(status().isNotFound());
    }

}
