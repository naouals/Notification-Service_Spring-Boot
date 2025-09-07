package org.example.demo1_notification.controller;

import org.example.demo1_notification.service.DelayService;
import org.example.demo1_notification.service.TrafficService;
import org.example.demo1_notification.service.WeatherService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alerts")
public class AlertDisplayController {

    private final WeatherService weatherService;
    private final TrafficService trafficService;
    private final DelayService delayService;

    public AlertDisplayController(WeatherService weatherService, TrafficService trafficService, DelayService delayService) {
        this.weatherService = weatherService;
        this.trafficService = trafficService;
        this.delayService = delayService;
    }

    //Ces endpoints ne font que retourner du texte
    @PostMapping("/weather-alert")
    public String getWeather(@RequestParam String driver) {
        return weatherService.getWeather();
    }

    @PostMapping("/traffic-alert")
    public String getTraffic(@RequestParam String driver) {
        return trafficService.getTrafficMessage();
    }

    @PostMapping("/delay-alert")
    public String getDelay(@RequestParam String driver) {
        return delayService.getDelayMessage();
    }
}

