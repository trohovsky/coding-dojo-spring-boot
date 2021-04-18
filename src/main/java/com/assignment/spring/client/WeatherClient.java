package com.assignment.spring.client;

import com.assignment.spring.client.api.WeatherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "weather", url = "${openweathermap.url}")
public interface WeatherClient {

    @RequestMapping(method = RequestMethod.GET, value = "/weather?q={city}&APPID={appId}")
    WeatherResponse getWeather(@RequestParam("city") String city, @RequestParam("appId") String appId);

}
