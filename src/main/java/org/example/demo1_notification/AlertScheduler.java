package org.example.demo1_notification;

import org.example.demo1_notification.service.AlertService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AlertScheduler {
    private final AlertService alertService;

    public AlertScheduler(AlertService alertService) {
        this.alertService = alertService;
    }

    // Toutes les 30 minutes
    @Scheduled(fixedRate = 30 * 60 * 1000)
    public void sendAlertsPeriodically() {
        System.out.println("=== Vérification automatique des alertes ===");
        String driverPhone = "+212617244534"; //le numéro réel
        //alertService.checkWeatherAndAlert(driverPhone);
        //alertService.sendTestAlert(driverPhone); // optionnel pour test
        // plus tard :
        //alertService.checkAllAlerts(driverPhone);
        //alertService.checkAllAlertsWhatsapp(driverPhone);
        //alertService.checkAllAlertsCombined(driverPhone);
    }
}
