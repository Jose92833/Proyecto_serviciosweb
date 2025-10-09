package itst.ws.demopersistence.service;

import java.util.List;

import org.springframework.stereotype.Service;

import itst.ws.demopersistence.dto.NotificationRequest;
import itst.ws.demopersistence.dto.NotificationResponse;
import itst.ws.demopersistence.model.Notification;
import itst.ws.demopersistence.model.NotificationMapper;
import itst.ws.demopersistence.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;




@Service
@RequiredArgsConstructor

public class NotificationServiceImpl implements NotificationService {
    
private final NotificationRepository notificationRepository;
    private final SystemUserRepository systemUserRepository;
    private final NotificationMapper mapper;

    @Override
    public NotificationResponse createNotification(NotificationRequest request) {
        SystemUser user = systemUserRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Notification notification = mapper.toEntity(request, user);
        notificationRepository.save(notification);
        return mapper.toResponse(notification);
    }

    @Override
    public List<NotificationResponse> getAllNotifications() {
        return notificationRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public NotificationResponse getNotificationById(Integer id) {
        return notificationRepository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Notificación no encontrada"));
    }

    @Override
    public void deleteNotification(Integer id) {
        notificationRepository.deleteById(id);
    }
}