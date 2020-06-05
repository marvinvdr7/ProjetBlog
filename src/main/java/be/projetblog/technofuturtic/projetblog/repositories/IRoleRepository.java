package be.projetblog.technofuturtic.projetblog.repositories;

import be.projetblog.technofuturtic.projetblog.entities.Permission;
import be.projetblog.technofuturtic.projetblog.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
   @Query(value = "SELECT Permission " +
            "FROM RolePermission rp " +
            "LEFT JOIN rp.permission Permission " +
            "LEFT JOIN rp.role Role " +
            "WHERE rp.role.id = ?1")
    List<Permission> findRolePermissions(Long id);

    @Query(value = "SELECT Role " +
            "FROM UserRole ur " +
            "LEFT JOIN ur.role Role " +
            "LEFT JOIN ur.user User " +
            "WHERE ur.user.id = ?1")
    List<Role> findRolesByUser(Long id);
}
