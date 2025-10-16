package itst.ws.demopersistence.mapper;

import itst.ws.demopersistence.dto.SpeciesRequest;
import itst.ws.demopersistence.dto.SpeciesResponse;
import itst.ws.demopersistence.model.Species;

public class SpeciesMapper {

    public static SpeciesResponse toResponse(Species species) {
        return new SpeciesResponse(
            species.getSpecies_id(),
            species.getSpeciesName()
           
        );
    }

    public static Species toEntity(SpeciesRequest request) {
        Species species = new Species();
        species.setSpeciesName(request.getSpeciesName());
        return species;
    }

    public static void updateEntity(Species species, SpeciesRequest request) {
        species.setSpeciesName(request.getSpeciesName());
    }
}
