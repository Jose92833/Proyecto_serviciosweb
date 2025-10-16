package itst.ws.demopersistence.mapper;

import itst.ws.demopersistence.dto.PetTreatmentRequest;
import itst.ws.demopersistence.dto.PetTreatmentResponse;
import itst.ws.demopersistence.model.PetTreatment;
import itst.ws.demopersistence.model.Pet;
import itst.ws.demopersistence.model.Treatment;
import itst.ws.demopersistence.model.Veterinarians;

public class PetTreatmentMapper {

    public static PetTreatmentResponse toResponse(PetTreatment petTreatment) {
        return PetTreatmentResponse.builder()
                .pet_treatment_id(petTreatment.getPet_treatment_id())
                .applicationDate(petTreatment.getApplicationDate())
                .petId(petTreatment.getPet() != null ? petTreatment.getPet().getPet_id() : null)
                .treatmentId(petTreatment.getTreatment() != null ? petTreatment.getTreatment().getTreatment_id() : null)
                .veterinarianId(petTreatment.getVeterinarian() != null ? petTreatment.getVeterinarian().getVeterinarian_id() : null)
                .build();
    }

    public static PetTreatment toEntity(PetTreatmentRequest request) {
        PetTreatment petTreatment = new PetTreatment();
        petTreatment.setApplicationDate(request.getApplicationDate());
        
        // Establecer relaciones mediante IDs
        if (request.getPetId() != null) {
            Pet pet = new Pet();
            pet.setPet_id(request.getPetId());
            petTreatment.setPet(pet);
        }
        
        if (request.getTreatmentId() != null) {
            Treatment treatment = new Treatment();
            treatment.setTreatment_id(request.getTreatmentId());
            petTreatment.setTreatment(treatment);
        }
        
        if (request.getVeterinarianId() != null) {
            Veterinarians veterinarian = new Veterinarians();
            veterinarian.setVeterinarian_id(request.getVeterinarianId());
            petTreatment.setVeterinarian(veterinarian);
        }
        
        return petTreatment;
    }

    public static void updateEntity(PetTreatment petTreatment, PetTreatmentRequest request) {
        petTreatment.setApplicationDate(request.getApplicationDate());
        
        // Actualizar relaciones mediante IDs
        if (request.getPetId() != null) {
            Pet pet = new Pet();
            pet.setPet_id(request.getPetId());
            petTreatment.setPet(pet);
        }
        
        if (request.getTreatmentId() != null) {
            Treatment treatment = new Treatment();
            treatment.setTreatment_id(request.getTreatmentId());
            petTreatment.setTreatment(treatment);
        }
        
        if (request.getVeterinarianId() != null) {
            Veterinarians veterinarian = new Veterinarians();
            veterinarian.setVeterinarian_id(request.getVeterinarianId());
            petTreatment.setVeterinarian(veterinarian);
        }
    }
}
