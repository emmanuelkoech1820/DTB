package com.dtb.dto;

import java.time.LocalDateTime;
import java.util.UUID;
import com.dtb.constants.NotificationChannel;

public class NotificationEvent {
    private String eventId;        // Unique ID for the event
    private String topic;          // e.g., "NOTIFICATION_EVENT"
    private String source;         // e.g., "PAYMENT_SERVICE"
    private LocalDateTime createdAt;

    // Payload
    private Long profileId;
    private String email;
    private String phoneNumber;
    private String message;
    private NotificationChannel channel;

    public NotificationEvent() {
        this.eventId = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public String getEventId() {
        return eventId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NotificationChannel getChannel() {
        return channel;
    }

    public void setChannel(NotificationChannel channel) {
        this.channel = channel;
    }
}

