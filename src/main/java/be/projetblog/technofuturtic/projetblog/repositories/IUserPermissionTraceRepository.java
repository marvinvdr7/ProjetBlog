package be.projetblog.technofuturtic.projetblog.repositories;

import be.projetblog.technofuturtic.projetblog.entities.UserPermissionTrace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserPermissionTraceRepository extends JpaRepository<UserPermissionTrace, Long> {
}
