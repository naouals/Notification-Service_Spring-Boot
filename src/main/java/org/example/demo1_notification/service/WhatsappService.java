package org.example.demo1_notification.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WhatsappService {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.whatsapp.number}")
    private String fromNumber;

    public void sendWhatsapp(String to, String message) {
        Twilio.init(accountSid, authToken);

        Message msg = Message.creator(
                new PhoneNumber("whatsapp:" + to),  // destinataire
                new PhoneNumber(fromNumber),        // numéro Twilio (sandbox)
                message
        ).create();

        System.out.println("WhatsApp message SID: " + msg.getSid());
    }
}
