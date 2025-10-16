package itst.ws.demopersistence.mapper;

import itst.ws.demopersistence.dto.ReportRequest;
import itst.ws.demopersistence.dto.ReportResponse;
import itst.ws.demopersistence.model.Report;
import itst.ws.demopersistence.model.SystemUser;

public class ReportMapper {

    public static ReportResponse toResponse(Report report) {
        return ReportResponse.builder()
                .report_id(report.getReport_id())
                .typeReport(report.getTypeReport())
                .report_date(report.getReport_date())
                .userId(report.getUser() != null ? report.getUser().getUser_id() : null)
                .build();
    }

    public static Report toEntity(ReportRequest request) {
        Report report = new Report();
        report.setTypeReport(request.getTypeReport());
        
        if (request.getUserId() != null) {
            SystemUser user = new SystemUser();
            user.setUser_id(request.getUserId());
            report.setUser(user);
        }
        
        return report;
    }

    public static void updateEntity(Report report, ReportRequest request) {
        report.setTypeReport(request.getTypeReport());
        
        if (request.getUserId() != null) {
            SystemUser user = new SystemUser();
            user.setUser_id(request.getUserId());
            report.setUser(user);
        }
    }
}
