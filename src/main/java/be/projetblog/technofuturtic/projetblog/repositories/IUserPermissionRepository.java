package be.projetblog.technofuturtic.projetblog.repositories;

import be.projetblog.technofuturtic.projetblog.entities.UserPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface IUserPermissionRepository extends JpaRepository<UserPermission, Long> {
    @Query(value = "SELECT up " +
            "FROM UserPermission up " +
            "LEFT JOIN up.user User " +
            "WHERE up.user.id = ?1 ")
    List<UserPermission> findByUserId(Long id_user);

    @Query(value = "SELECT up " +
            "FROM UserPermission AS up " +
            "LEFT JOIN up.permission Permission " +
            "LEFT JOIN up.user User " +
            "WHERE up.user.id = ?1 AND up.permission.id = ?2 ")
    Optional<UserPermission> findByUserAndPermissionId(Long id_user, Long id_permission);

    @Transactional
    @Modifying
    @Query(value = "DELETE " +
            "FROM UserPermission up " +
            "WHERE up.user.id = ?1 AND up.permission.id = ?2 ")
    void deleteByUserAndPermissionId(Long id_user, Long id_permission);
}
