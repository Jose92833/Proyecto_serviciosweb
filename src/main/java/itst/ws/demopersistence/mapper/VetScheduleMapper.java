package itst.ws.demopersistence.mapper;

import itst.ws.demopersistence.dto.VetScheduleRequest;
import itst.ws.demopersistence.dto.VetScheduleResponse;
import itst.ws.demopersistence.model.VetSchedule;
import itst.ws.demopersistence.model.Veterinarians;

public class VetScheduleMapper {

    public static VetScheduleResponse toResponse(VetSchedule vetSchedule) {
        return VetScheduleResponse.builder()
                .schedule_id(vetSchedule.getSchedule_id())
                .weekday(vetSchedule.getWeekday())
                .startTime(vetSchedule.getStartTime())
                .endTime(vetSchedule.getEndTime())
                .veterinarianId(vetSchedule.getVeterinarian() != null ? 
                    vetSchedule.getVeterinarian().getVeterinarian_id() : null)
                .build();
    }

    public static VetSchedule toEntity(VetScheduleRequest request) {
        VetSchedule vetSchedule = new VetSchedule();
        vetSchedule.setWeekday(request.getWeekday());
        vetSchedule.setStartTime(request.getStartTime());
        vetSchedule.setEndTime(request.getEndTime());
        
        // relación con veterinario mediante ID
        if (request.getVeterinarianId() != null) {
            Veterinarians veterinarian = new Veterinarians();
            veterinarian.setVeterinarian_id(request.getVeterinarianId());
            vetSchedule.setVeterinarian(veterinarian);
        }
        
        return vetSchedule;
    }

    public static void updateEntity(VetSchedule vetSchedule, VetScheduleRequest request) {
        vetSchedule.setWeekday(request.getWeekday());
        vetSchedule.setStartTime(request.getStartTime());
        vetSchedule.setEndTime(request.getEndTime());
        
        //relación con veterinario mediante ID
        if (request.getVeterinarianId() != null) {
            Veterinarians veterinarian = new Veterinarians();
            veterinarian.setVeterinarian_id(request.getVeterinarianId());
            vetSchedule.setVeterinarian(veterinarian);
        }
    }
}
