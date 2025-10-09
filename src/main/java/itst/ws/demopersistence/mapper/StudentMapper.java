package itst.ws.demopersistence.mapper;

import itst.ws.demopersistence.dto.MascotaRequest;
import itst.ws.demopersistence.dto.MascotaResponse;
import itst.ws.demopersistence.model.Tratamiento;

public final class StudentMapper {

     private StudentMapper() {
        
    }

    public static MascotaResponse toResponse(Tratamiento tratamiento) {
        if (tratamiento == null)
            return null;

        return MascotaResponse.builder()
                .idtratamiento(tratamiento.getIdTratamiento())
                .nombre(tratamiento.getNombre())
                .descripcion(tratamiento.getDescripcion())
                .build();
    }

    public static Tratamiento toEntity(MascotaRequest dto) {
        if (dto == null)
            return null;

        return Tratamiento.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .build();
    }

    public static void copyToEntity(MascotaRequest dto, Tratamiento entity) {
        if (dto == null || entity == null)
            return;

        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
    }
}