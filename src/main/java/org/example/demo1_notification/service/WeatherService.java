package org.example.demo1_notification.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    @Value("${openweather.api.key}")
    private String apiKey;

    @Value("${openweather.city}")
    private String city;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getWeather() {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric";
        String response = restTemplate.getForObject(url, String.class);

        JSONObject json = new JSONObject(response);
        String weather = json.getJSONArray("weather").getJSONObject(0).getString("main");
        double temp = json.getJSONObject("main").getDouble("temp");

        return "Météo à " + city + ": " + weather + ", Température: " + temp + "°C";
    }

    public boolean isRainExpected() {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey;
        String response = restTemplate.getForObject(url, String.class);
        JSONObject json = new JSONObject(response);
        String weather = json.getJSONArray("weather").getJSONObject(0).getString("main");
        return weather.equalsIgnoreCase("Rain");
    }
}
