package org.example.demo1_notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Demo1NotificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(Demo1NotificationApplication.class, args);
    }

}
