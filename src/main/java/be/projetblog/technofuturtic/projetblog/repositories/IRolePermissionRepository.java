package be.projetblog.technofuturtic.projetblog.repositories;

import be.projetblog.technofuturtic.projetblog.entities.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolePermissionRepository extends JpaRepository<RolePermission, Long> {

}
