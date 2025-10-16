package itst.ws.demopersistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import itst.ws.demopersistence.model.SystemRole;

public interface SystemRoleRepository extends JpaRepository<SystemRole, Long> {
  
}
