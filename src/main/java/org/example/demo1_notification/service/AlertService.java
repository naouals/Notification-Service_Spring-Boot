package org.example.demo1_notification.service;

import org.springframework.stereotype.Service;

@Service
public class AlertService {
    private final WeatherService weatherService;
    private final SmsService smsService;
    private final TrafficService trafficService;
    private final DelayService delayService;
    private final WhatsappService whatsappService;

    public AlertService(WeatherService weatherService, TrafficService trafficService,
                        DelayService delayService, SmsService smsService, WhatsappService whatsappService) {
        this.weatherService = weatherService;
        this.trafficService = trafficService;
        this.delayService = delayService;
        this.smsService = smsService;
        this.whatsappService = whatsappService;
    }

    // Vérification météo
    public void checkWeatherAndAlert(String driverPhone) {
        String message = weatherService.getWeather();
        smsService.sendSms(driverPhone, message);
        System.out.println("SMS météo envoyé: " + message);

    }

    // Envoi d'un SMS test
    public void sendTestAlert(String driverPhone) {
        String message = "- Ceci est un TEST d’alerte automatique !";
        smsService.sendSms(driverPhone, message);
        System.out.println("SMS TEST envoyé à " + driverPhone);
    }

    // Nouvelle méthode pour toutes les alertes
    public void checkAllAlerts(String driverPhone) {
        // Météo
        checkWeatherAndAlert(driverPhone);

        // Trafic
        String trafficMessage = trafficService.getTrafficMessage();
        smsService.sendSms(driverPhone, trafficMessage);
        System.out.println("SMS trafic envoyé: " + trafficMessage);

        // Retard
        String delayMessage = delayService.getDelayMessage();
        smsService.sendSms(driverPhone, delayMessage);
        System.out.println("SMS retard envoyé: " + delayMessage);

    }

    public void checkAllAlertsWhatsapp(String driverPhone) {
        // Construire message combiné stylé
        StringBuilder messageBuilder = new StringBuilder();

        messageBuilder.append("🚨 *Alertes du jour* 🚨\n\n");

        // Météo
        String meteo = weatherService.getWeather();
        messageBuilder.append("🌤️ Météo: ").append(meteo).append("\n\n");

        // Trafic
        String trafficMessage = trafficService.getTrafficMessage();
        messageBuilder.append("🚗 Trafic: ").append(trafficMessage).append("\n\n");

        // Retard
        String delayMessage = delayService.getDelayMessage();
        messageBuilder.append("⏰ Retard: ").append(delayMessage);

        String finalMessage = messageBuilder.toString();

        // SMS (optionnel, si tu veux envoyer aussi par SMS)
        smsService.sendSms(driverPhone, finalMessage);

        // WhatsApp
        whatsappService.sendWhatsapp(driverPhone, finalMessage);

        System.out.println("✅ SMS + WhatsApp combinés envoyés avec style !");
    }

    // Nouvelle méthode pour toutes les alertes combinées
    public void checkAllAlertsCombined(String driverPhone) {
        try {
            // Récupérer les messages
            String weatherMessage = weatherService.getWeather();
            String trafficMessage = trafficService.getTrafficMessage();
            String delayMessage = delayService.getDelayMessage();

            // Construire le message combiné
            String combinedMessage = "🚨 Alertes du jour 🚨\n"
                    + "Météo: " + weatherMessage + "\n"
                    + "Trafic: " + trafficMessage + "\n"
                    + "Retard: " + delayMessage;

            // Log pour debug
            System.out.println("Longueur du message: " + combinedMessage.length());
            System.out.println("Message complet: " + combinedMessage);

            // Envoyer le SMS combiné
            smsService.sendSms(driverPhone, combinedMessage);

            System.out.println("- SMS combiné envoyé avec succès");

        } catch (Exception e) {
            System.err.println("Erreur lors de l'envoi du SMS combiné: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
