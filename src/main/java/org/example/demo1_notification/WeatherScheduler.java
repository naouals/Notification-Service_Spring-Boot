package org.example.demo1_notification;

import org.example.demo1_notification.service.SmsService;
import org.example.demo1_notification.service.WeatherService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WeatherScheduler {

    private final WeatherService weatherService;
    private final SmsService smsService;

    // Injection par constructeur
    public WeatherScheduler(WeatherService weatherService, SmsService smsService) {
        this.weatherService = weatherService;
        this.smsService = smsService;
    }

    // Vérifie toutes les 30 minutes
    /***@Scheduled(fixedRate = 30 * 60 * 1000)
    public void checkWeatherAutomatically() {
        System.out.println(" Vérification météo automatique lancée !");


        // Récupérer la météo
        String meteo = weatherService.getWeather();
        System.out.println("Météo reçue : " + meteo);

        // Envoyer par SMS
        smsService.sendSms("+212617244534", meteo);
    }***/
}
