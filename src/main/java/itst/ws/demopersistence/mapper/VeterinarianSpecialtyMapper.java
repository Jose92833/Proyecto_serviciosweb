package itst.ws.demopersistence.mapper;

import itst.ws.demopersistence.dto.VeterinarianSpecialtyRequest;
import itst.ws.demopersistence.dto.VeterinarianSpecialtyResponse;
import itst.ws.demopersistence.model.VeterinarianSpecialty;
import itst.ws.demopersistence.model.Veterinarians;
import itst.ws.demopersistence.model.Specialty;

public class VeterinarianSpecialtyMapper {

    public static VeterinarianSpecialtyResponse toResponse(VeterinarianSpecialty veterinarianSpecialty) {
        return VeterinarianSpecialtyResponse.builder()
                .vet_spec_id(veterinarianSpecialty.getVet_spec_id())
                .veterinarianId(veterinarianSpecialty.getVeterinarian() != null ? 
                    veterinarianSpecialty.getVeterinarian().getVeterinarian_id() : null)
                .specialtyId(veterinarianSpecialty.getSpecialty() != null ? 
                    veterinarianSpecialty.getSpecialty().getSpecialty_id() : null)
                .build();
    }

    public static VeterinarianSpecialty toEntity(VeterinarianSpecialtyRequest request) {
        VeterinarianSpecialty veterinarianSpecialty = new VeterinarianSpecialty();
        
        // Establecer relaciones mediante IDs
        if (request.getVeterinarianId() != null) {
            Veterinarians veterinarian = new Veterinarians();
            veterinarian.setVeterinarian_id(request.getVeterinarianId());
            veterinarianSpecialty.setVeterinarian(veterinarian);
        }
        
        if (request.getSpecialtyId() != null) {
            Specialty specialty = new Specialty();
            specialty.setSpecialty_id(request.getSpecialtyId());
            veterinarianSpecialty.setSpecialty(specialty);
        }
        
        return veterinarianSpecialty;
    }

    public static void updateEntity(VeterinarianSpecialty veterinarianSpecialty, VeterinarianSpecialtyRequest request) {
        // Actualizar relaciones mediante IDs
        if (request.getVeterinarianId() != null) {
            Veterinarians veterinarian = new Veterinarians();
            veterinarian.setVeterinarian_id(request.getVeterinarianId());
            veterinarianSpecialty.setVeterinarian(veterinarian);
        }
        
        if (request.getSpecialtyId() != null) {
            Specialty specialty = new Specialty();
            specialty.setSpecialty_id(request.getSpecialtyId());
            veterinarianSpecialty.setSpecialty(specialty);
        }
    }
}
