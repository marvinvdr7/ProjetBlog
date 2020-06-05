package be.projetblog.technofuturtic.projetblog.services;

import be.projetblog.technofuturtic.projetblog.entities.UserPermission;
import be.projetblog.technofuturtic.projetblog.entities.UserPermissionTrace;
import be.projetblog.technofuturtic.projetblog.repositories.IUserPermissionRepository;
import be.projetblog.technofuturtic.projetblog.repositories.IUserPermissionTraceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserPermissionTraceService {

    private IUserPermissionTraceRepository userPermissionTraceRepository;
    private IUserPermissionRepository userPermissionRepository;
    @Autowired
    public UserPermissionTraceService(IUserPermissionTraceRepository userPermissionTraceRepository,
                                      IUserPermissionRepository userPermissionRepository ) {
        this.userPermissionTraceRepository = userPermissionTraceRepository;
        this.userPermissionRepository = userPermissionRepository;
    }

    public UserPermissionTrace createAction(UserPermissionTrace entity) throws Exception {
        Optional<UserPermission> ur = userPermissionRepository.findByUserAndPermissionId(entity.getUser_id(), entity.getPermission_id());
        if(ur.isPresent()) {
            entity.setStartDate(ur.get().getStartDate());
            entity.setEndDate(LocalDate.now());
            userPermissionTraceRepository.save(entity);
            return entity;
        } else {
            //throw new Exception("Ce userPermissionTrace n'existe pas");
            return null;
        }
    }
}