package itst.ws.demopersistence.model;

import itst.ws.demopersistence.dto.NotificationRequest;
import itst.ws.demopersistence.dto.NotificationResponse;

public class NotificationMapper {
    public Notification toEntity(NotificationRequest request, SystemUser user) {
        return Notification.builder()
                .user(user)
                .notificationMessage(request.getNotificationMessage())
                .read(request.getRead() != null ? request.getRead() : false)
                .sentDate(java.time.LocalDateTime.now())
                .build();
    }

    public NotificationResponse toResponse(Notification notification) {
        return NotificationResponse.builder()
                .notificationId(notification.getNotificationId())
                .userId(notification.getUser().getUserId())
                .username(notification.getUser().getUsername())
                .notificationMessage(notification.getNotificationMessage())
                .sentDate(notification.getSentDate())
                .read(notification.getRead())
                .build();
    }
}

