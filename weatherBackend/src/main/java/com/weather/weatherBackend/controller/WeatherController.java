package com.weather.weatherBackend.controller;


import com.weather.weatherBackend.dto.WeatherResponseDTO;
import com.weather.weatherBackend.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public ResponseEntity<WeatherResponseDTO> getWeather(@RequestParam String city){
        try{
            WeatherResponseDTO weatherData = weatherService.getWeather(city);
            return ResponseEntity.ok(weatherData);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
