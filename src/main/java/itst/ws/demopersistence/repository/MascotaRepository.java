package itst.ws.demopersistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import itst.ws.demopersistence.model.Tratamiento;

public interface MascotaRepository extends JpaRepository<Tratamiento, Integer>{
    
}