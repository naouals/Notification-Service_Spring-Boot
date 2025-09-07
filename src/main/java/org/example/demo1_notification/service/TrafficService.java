package org.example.demo1_notification.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TrafficService {
    @Value("${google.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getTrafficMessage() {
        String origin = "Oujda,MA";
        String destination = "Meknès,MA";

        String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins="
                + origin + "&destinations=" + destination
                + "&departure_time=now&key=" + apiKey;

        String response = restTemplate.getForObject(url, String.class);
        JSONObject json = new JSONObject(response);

        if (!json.getString("status").equals("OK")) {
            return "- Erreur lors de la récupération du trafic.";
        }

        String originCity = json.getJSONArray("origin_addresses").getString(0);
        String destinationCity = json.getJSONArray("destination_addresses").getString(0);

        JSONObject element = json.getJSONArray("rows")
                .getJSONObject(0)
                .getJSONArray("elements")
                .getJSONObject(0);

        if (element.getString("status").equals("OK")) {
            int durationNormal = element.getJSONObject("duration").getInt("value"); // en secondes
            int durationTraffic = element.getJSONObject("duration_in_traffic").getInt("value");
            int distanceMeters = element.getJSONObject("distance").getInt("value");

            int delay = durationTraffic - durationNormal;
            int delayMinutes = delay / 60;
            int durationMinutes = durationTraffic / 60;
            double distanceKm = distanceMeters / 1000.0;

            String trafficStatus;
            if (delayMinutes > 10) {
                trafficStatus = "Circulation dense / retard estimé: " + delayMinutes + " min";
            } else {
                trafficStatus = "Trafic normal (retard estimé: " + delayMinutes + " min)";
            }

            return "Trajet de " + originCity + " à " + destinationCity +
                    " : " + String.format("%.1f", distanceKm) + " km, durée: " +
                    durationMinutes + " min, état: " + trafficStatus;
        }

        return "- Impossible de récupérer les données de trafic.";
    }
    public int getCurrentDelayMinutes() {
        String origin = "Oujda,MA";
        String destination = "Meknès,MA";

        String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins="
                + origin + "&destinations=" + destination
                + "&departure_time=now&key=" + apiKey;

        String response = restTemplate.getForObject(url, String.class);
        JSONObject json = new JSONObject(response);

        if (!json.getString("status").equals("OK")) {
            return 0; // en cas d’erreur on dit 0 min
        }

        JSONObject element = json.getJSONArray("rows")
                .getJSONObject(0)
                .getJSONArray("elements")
                .getJSONObject(0);

        if (element.getString("status").equals("OK")) {
            int durationNormal = element.getJSONObject("duration").getInt("value"); // secondes
            int durationTraffic = element.getJSONObject("duration_in_traffic").getInt("value");
            return (durationTraffic - durationNormal) / 60;
        }

        return 0;
    }

}
