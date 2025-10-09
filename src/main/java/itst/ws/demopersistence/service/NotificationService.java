package itst.ws.demopersistence.service;
import java.util.List;

import itst.ws.demopersistence.dto.NotificationRequest;
import itst.ws.demopersistence.dto.NotificationResponse;

public interface NotificationService {
    NotificationResponse createNotification(NotificationRequest request);
    List<NotificationResponse> getAllNotifications();
    NotificationResponse getNotificationById(Integer id);
    void deleteNotification(Integer id);
}
