package com.weather.weatherBackend.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.weather.weatherBackend.dto.WeatherResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class WeatherService {
    @Value("${weather.api.url}")
    private String apiUrl;

    @Value("${weather.api.key}")
    private String apiKey;

    //Rest template is used to make http requests to other services
    private final RestTemplate restTemplate;
    public WeatherService(){
        this.restTemplate = new RestTemplate();
    }

    public WeatherResponseDTO getWeather(String city){
        String url = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("api.openweathermap.org")
                .path("/data/2.5/weather")
                .queryParam("q", city)
                .queryParam("appid", apiKey)
                .queryParam("units", "metric")
                .build()
                .toUriString();
        JsonNode root = restTemplate.getForObject(url, JsonNode.class);
        if(root==null){
            return null;
        }
        String cityName = root.path("name").asText();
        double temperature = root.path("main").path("temp").asDouble();
        double humidity = root.path("main").path("humidity").asDouble();
        String description = root.path("weather").get(0).path("description").asText();
        String icon = root.path("weather").get(0).path("icon").asText();

        return new WeatherResponseDTO(cityName, temperature, humidity, description, icon);
    }
}
