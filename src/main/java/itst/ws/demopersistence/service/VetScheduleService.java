package itst.ws.demopersistence.service;

import java.util.List;
import itst.ws.demopersistence.dto.VetScheduleRequest;
import itst.ws.demopersistence.dto.VetScheduleResponse;

public interface VetScheduleService {
    List<VetScheduleResponse> findAll();
    VetScheduleResponse findById(Long scheduleId);
    VetScheduleResponse create(VetScheduleRequest request);
    VetScheduleResponse update(Long scheduleId, VetScheduleRequest request);
    void delete(Long scheduleId);
}
