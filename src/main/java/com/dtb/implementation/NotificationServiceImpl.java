package com.dtb.implementation;

import com.dtb.dto.NotificationEvent;
import com.dtb.services.abstracts.NotificationService;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Override
    public void sendNotification(NotificationEvent event) {
        // Simulate sending Email
        if (event.getEmail() != null && !event.getEmail().isEmpty()) {
            System.out.println("Sending EMAIL to " + event.getEmail());
            System.out.println("Message: " + event.getMessage());
        }

        // Simulate sending SMS
        if (event.getPhoneNumber() != null && !event.getPhoneNumber().isEmpty()) {
            System.out.println("Sending SMS to " + event.getPhoneNumber());
            System.out.println("Message: " + event.getMessage());
        }
    }
}