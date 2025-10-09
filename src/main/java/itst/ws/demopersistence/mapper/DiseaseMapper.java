package itst.ws.demopersistence.mapper;

import itst.ws.demopersistence.dto.DiseaseRequest;
import itst.ws.demopersistence.dto.DiseaseResponse;
import itst.ws.demopersistence.model.Disease;

public class DiseaseMapper {
    public static DiseaseResponse toResponse(Disease disease) {
        return DiseaseResponse.builder()
                .idDisease(disease.getIdDisease())
                .diseaseName(disease.getDiseaseName())
                .descripcion(disease.getDescripcion())
                .build();
    }

    public static Disease toEntity(DiseaseRequest request) {
        return Disease.builder()
                .diseaseName(request.getDiseaseName())
                .descripcion(request.getDescripcion())
                .build();
    }

    public static void updateEntity(Disease disease, DiseaseRequest request) {
        disease.setDiseaseName(request.getDiseaseName());
        disease.setDescripcion(request.getDescripcion());
    }
}

