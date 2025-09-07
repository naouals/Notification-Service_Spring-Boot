package org.example.demo1_notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DelayService {
    @Autowired
    private TrafficService trafficService;

    public String getDelayMessage() {
        // On récupère le retard directement depuis TrafficService
        int delayMinutes = trafficService.getCurrentDelayMinutes();

        if (delayMinutes > 10) {
            return "Prévoyez un délai supplémentaire (" + delayMinutes + " min).";
        } else if (delayMinutes > 0) {
            return "Petit retard possible (" + delayMinutes + " min).";
        } else {
            return "Pas de retard prévu.";
        }
    }
}
