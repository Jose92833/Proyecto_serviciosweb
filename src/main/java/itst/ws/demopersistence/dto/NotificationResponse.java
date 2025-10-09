package itst.ws.demopersistence.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationResponse {
     private Integer notificationId;
    private Integer userId;
    private String username;
    private String notificationMessage;
    private LocalDateTime sentDate;
    private Boolean read;
}
