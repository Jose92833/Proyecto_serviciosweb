package itst.ws.demopersistence.dto;

import lombok.Data;

@Data


public class NotificationRequest {
    private Integer userId;
    private String notificationMessage;
    private Boolean read;
}
