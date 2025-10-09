package itst.ws.demopersistence.mapper;
import java.util.List;
import java.util.stream.Collectors;

import itst.ws.demopersistence.dto.ConsultationRequest;
import itst.ws.demopersistence.dto.ConsultationResponse;
import itst.ws.demopersistence.model.Consultation;
import itst.ws.demopersistence.model.Disease;

public class ConsultationMapper {

    public static ConsultationResponse toResponse(Consultation consultation) {
        List<String> diseaseNames = consultation.getDiseases().stream()
               .map(Disease::getDiseaseName)
                .collect(Collectors.toList());

        return ConsultationResponse.builder()
                .consultationId(consultation.getConsultationId())
                .motivo(consultation.getMotivo())
                .diseases(diseaseNames)
                .build();
    }

    public static void updateEntity(Consultation consultation, ConsultationRequest request, List<Disease> diseases) {
        consultation.setMotivo(request.getMotivo());
        consultation.setDiseases(diseases);
    }
}
