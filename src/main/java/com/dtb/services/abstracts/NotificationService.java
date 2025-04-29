package com.dtb.services.abstracts;

import com.dtb.dto.NotificationEvent;

public interface NotificationService {
    void sendNotification(NotificationEvent event);
}
