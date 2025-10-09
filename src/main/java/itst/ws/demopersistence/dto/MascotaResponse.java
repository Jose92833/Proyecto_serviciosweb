package itst.ws.demopersistence.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MascotaResponse {

   
    Integer idtratamiento;
    String nombre;
    String descripcion;
}