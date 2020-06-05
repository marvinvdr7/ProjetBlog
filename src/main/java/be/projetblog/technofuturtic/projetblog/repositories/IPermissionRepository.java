package be.projetblog.technofuturtic.projetblog.repositories;

import be.projetblog.technofuturtic.projetblog.entities.Permission;
import be.projetblog.technofuturtic.projetblog.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPermissionRepository extends JpaRepository<Permission, Long> {
    @Query(value = "SELECT Permission " +
            "FROM RolePermission rp " +
            "LEFT JOIN rp.role Role " +
            "LEFT JOIN rp.permission Permission " +
            "WHERE rp.role.id = ?1")
    List<Permission> findPermissionsByRole(Long id);
}
