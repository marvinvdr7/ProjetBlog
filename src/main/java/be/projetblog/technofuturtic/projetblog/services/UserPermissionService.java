package be.projetblog.technofuturtic.projetblog.services;

import be.projetblog.technofuturtic.projetblog.entities.*;
import be.projetblog.technofuturtic.projetblog.exceptions.PermissionNotFoundException;
import be.projetblog.technofuturtic.projetblog.exceptions.UserNotFoundException;
import be.projetblog.technofuturtic.projetblog.repositories.IPermissionRepository;
import be.projetblog.technofuturtic.projetblog.repositories.IUserPermissionRepository;
import be.projetblog.technofuturtic.projetblog.repositories.IUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserPermissionService {

    private IUserPermissionRepository userPermissionRepository;
    private IUserRepository userRepository;
    private IPermissionRepository permissionRepository;
    @Autowired
    public UserPermissionService(IUserPermissionRepository userPermissionRepository,
                                 IPermissionRepository permissionRepository,
                                 IUserRepository userRepository ) {
        this.userPermissionRepository = userPermissionRepository;
        this.userRepository = userRepository;
        this.permissionRepository = permissionRepository;
    }

    public List<UserPermission> getAllAction(){
        return userPermissionRepository.findAll();
    }

    public List<UserPermission> findByUserId (Long id_user) {
        return userPermissionRepository.findByUserId(id_user);
    }

    public UserPermission createAction(Long id_user, Long id_permission) throws PermissionNotFoundException, UserNotFoundException {
        Optional<User> user = userRepository.findById(id_user);
        Optional<Permission> permission = permissionRepository.findById(id_permission);
        if(user.isPresent()) {
            if(permission.isPresent()) {
                UserPermission userPermission = new UserPermission();
                userPermission.setUser(user.get());
                userPermission.setPermission(permission.get());
                userPermission.setStartDate(LocalDate.now());
                userPermissionRepository.save(userPermission);
                return userPermission;
            } else { throw new PermissionNotFoundException(); }
        } else { throw new UserNotFoundException(); }
    }

    public void deleteByUserAndPermissionAction(Long id_user, Long id_permission) throws Exception {
        Optional<UserPermission> userPermissionFind = userPermissionRepository.findByUserAndPermissionId(id_user, id_permission);
        if(userPermissionFind.isPresent()){
            userPermissionRepository.deleteByUserAndPermissionId(id_user, id_permission);
        } else {
            throw new Exception("Ce userPermission n'existe pas");
        }
    }
}