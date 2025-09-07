package org.example.demo1_notification.controller;

import org.example.demo1_notification.service.AlertService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alerts")
public class AlertNotificationController {

    private final AlertService alertService;

    public AlertNotificationController(AlertService alertService) {
        this.alertService = alertService;
    }

    // SMS Test
    @PostMapping("/test-alert")
    public String sendTestAlert(@RequestParam String driver) {
        alertService.sendTestAlert(driver);
        return "✅ SMS test envoyé à " + driver;
    }

    // Toutes les alertes par SMS
    @PostMapping("/check-all")
    public String sendAllAlerts(@RequestParam String phone) {
        alertService.checkAllAlerts(phone);
        return "✅ Toutes les alertes envoyées par SMS à " + phone;
    }

    // Toutes les alertes par WhatsApp
    @PostMapping("/check-all-whatsapp")
    public String sendAllAlertsWhatsapp(@RequestParam String phone) {
        alertService.checkAllAlertsWhatsapp(phone);
        return "✅ Toutes les alertes envoyées par WhatsApp à " + phone;
    }
}

