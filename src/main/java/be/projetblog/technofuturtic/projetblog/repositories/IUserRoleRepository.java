package be.projetblog.technofuturtic.projetblog.repositories;

import be.projetblog.technofuturtic.projetblog.entities.UserRole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

import java.util.Optional;

@Repository
public interface IUserRoleRepository extends JpaRepository<UserRole, Long> {

    @Query(value = "SELECT ur " +
            "FROM UserRole AS ur " +
            "LEFT JOIN ur.role Role " +
            "LEFT JOIN ur.user User " +
            "WHERE ur.user.id = ?1 AND ur.role.id = ?2 ")
    Optional<UserRole> findByUserAndRoleId(Long id_user, Long id_role);

    @Transactional
    @Modifying
    @Query(value = "DELETE " +
            "FROM UserRole ur " +
            "WHERE ur.user.id = ?1 AND ur.role.id = ?2 ")
    void deleteByUserAndRoleId(Long id_user, Long id_role);
}
