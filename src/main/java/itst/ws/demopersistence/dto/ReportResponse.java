package itst.ws.demopersistence.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportResponse {
    private Long report_id;
    private String typeReport;
    private LocalDateTime report_date;
    private Long userId; 
}
