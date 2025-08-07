package com.weather.weatherBackend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherResponseDTO {
    private String cityName;
    private double temperature;
    private double humidity;
    private String description;
    private String icon;
}
