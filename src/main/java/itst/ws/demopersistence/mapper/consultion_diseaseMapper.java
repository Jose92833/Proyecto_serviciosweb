package itst.ws.demopersistence.mapper;

import itst.ws.demopersistence.dto.consultantion_diseaseRequest;
import itst.ws.demopersistence.dto.consultation_diseaseResponse;
import itst.ws.demopersistence.model.consultation_disease;

public class consultion_diseaseMapper {
    
 private consultion_diseaseMapper() {
        // Constructor privado para evitar instanciación
    }

    public static consultation_diseaseResponse toResponse(consultation_disease entity) {
        if (entity == null)
            return null;

        return consultation_diseaseResponse.builder()
                .cdId(entity.getCdId())
                .consultationId(entity.getConsultationId())
                .diseaseId(entity.getDisease_id())
                .build();
    }

    public static consultation_disease toEntity(consultantion_diseaseRequest dto) {
        if (dto == null)
            return null;

        return consultation_disease.builder()
                .consultationId(dto.getConsultationId())
                .disease_id(dto.getDiseaseId())
                .build();
    }

    public static void copyToEntity(consultantion_diseaseRequest dto, consultation_disease entity) {
        if (dto == null || entity == null)
            return;

        entity.setConsultationId(dto.getConsultationId());
        entity.setDisease_id(dto.getDiseaseId());
    }
}
