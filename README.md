
#  Plateforme d’Alertes pour Conducteurs 🚨

##  Description

Cette application en **Spring Boot** permet d’envoyer automatiquement des **alertes aux conducteurs** concernant :

*  la météo,
*  l’état du trafic,
*  les retards potentiels.

Les alertes peuvent être affichées sur un **dashboard web** et envoyées par **SMS/WhatsApp via Twilio**.

---

##  Technologies utilisées

* **Backend** : Java 17, Spring Boot (Spring Web, REST)
* **API externes** :

  * [OpenWeather API](https://openweathermap.org/api) → météo
  * [Google Distance Matrix API](https://developers.google.com/maps/documentation/distance-matrix) → trafic
  * [Twilio API](https://www.twilio.com/) → envoi SMS/WhatsApp
* **Frontend** : HTML, CSS, JavaScript (mise à jour auto toutes les 2 min)

---

##  Fonctionnement général

1. Lorsqu’un conducteur accède au **dashboard**, les données (météo, trafic, retard) sont automatiquement affichées.
2. L’utilisateur peut cliquer sur un bouton pour **envoyer manuellement un SMS d’alerte**.
3. Les alertes sont également mises à jour automatiquement toutes les **2 minutes** (seulement affichage, sans SMS).

---

##  Structure et explication des classes

###  `WeatherService`

* Récupère la **météo actuelle** via l’API OpenWeather.
* Exemple de message :

<img width="378" height="26" alt="image" src="https://github.com/user-attachments/assets/6fbe0f84-59dc-4b77-be29-ea844aab842d" />
  

###  `TrafficService`

* Utilise l’API Google Distance Matrix pour calculer :

  * La distance totale
  * La durée du trajet (normale vs en trafic)
  * Le retard estimé
* Exemple de message :

<img width="877" height="26" alt="image" src="https://github.com/user-attachments/assets/e7c31e26-3e28-43e2-b991-910945272148" />
  

###  `DelayService`

* Interprète le retard en minutes et génère un message clair :
    - `"Pas de retard prévu."`
    - `"Petit retard possible (5 min)."`
    - `"Alerte retard : prévoyez un délai supplémentaire (15 min)."`
* Exemple de message :

<img width="392" height="30" alt="image" src="https://github.com/user-attachments/assets/90bcbf4c-360c-4932-a3f0-a63e27cf5de7" />


###  `SmsService`

* Utilise **Twilio API** pour envoyer les alertes par SMS ou WhatsApp.

<img width="191" height="31" alt="image" src="https://github.com/user-attachments/assets/6d8ecd59-3e45-49a5-9b9d-e7efc5906c03" />

###  `AlertService`

* Combine les différents services (`WeatherService`, `TrafficService`, `DelayService`) pour créer un **message complet**.
* Exemple :

<img width="426" height="285" alt="image" src="https://github.com/user-attachments/assets/76784dff-e9bf-49e3-b5ed-30d95cde8073" />


###  `WeatherAlertController`

* Expose les **endpoints REST** pour :

  * `/alerts/weather-alert` → météo
  * `/alerts/traffic-alert` → trafic
  * `/alerts/delay-alert` → retard
  * `/alerts/test-alert` → SMS test

---

##  Installation et lancement

### 1️ Cloner le projet

```bash
git clone https://github.com/ton-profil/nom-du-repo.git
cd nom-du-repo
```

### 2️ Configurer les clés API

Dans `application.properties` :

```properties
google.api.key=TON_API_KEY_GOOGLE
openweather.api.key=TON_API_KEY_OPENWEATHER
twilio.account.sid=TON_SID
twilio.auth.token=TON_TOKEN
twilio.phone.number=+123456789
```

### 3️ Lancer le projet

```bash
mvn spring-boot:run
```

### 4️ Accéder au dashboard

Ouvrir : [http://localhost:8080](http://localhost:8080)

---

##  Exemple d’utilisation

###  Dashboard (frontend)

Affichage automatique toutes les 2 minutes :

<img width="1917" height="811" alt="image" src="https://github.com/user-attachments/assets/08487daa-219f-4584-a012-d60b6281c4df" />
<img width="574" height="177" alt="Capture d'écran 2025-09-07 160501" src="https://github.com/user-attachments/assets/15a1bd72-1e79-4b52-80b2-339fbb9cda67" />


###  Exemple de SMS reçu (via Twilio)


<img src="https://github.com/user-attachments/assets/37e27250-a3e6-4eb0-a20f-3de2eada7fca" alt="Capture WhatsApp" width="300"/>


---

