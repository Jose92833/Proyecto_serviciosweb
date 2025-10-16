package itst.ws.demopersistence.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VetScheduleRequest {
    private String weekday;
    private LocalTime startTime;
    private LocalTime endTime;
    private Long veterinarianId;
}
