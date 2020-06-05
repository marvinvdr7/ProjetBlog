package be.projetblog.technofuturtic.projetblog.services;

import be.projetblog.technofuturtic.projetblog.entities.*;
import be.projetblog.technofuturtic.projetblog.exceptions.PermissionNotFoundException;
import be.projetblog.technofuturtic.projetblog.exceptions.UserNotFoundException;
import be.projetblog.technofuturtic.projetblog.repositories.IPermissionRepository;
import be.projetblog.technofuturtic.projetblog.repositories.IRolePermissionRepository;
import be.projetblog.technofuturtic.projetblog.repositories.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RolePermissionService {

    private IRolePermissionRepository rolePermissionRepository;
    private IRoleRepository roleRepository;
    private IPermissionRepository permissionRepository;
    @Autowired
    public RolePermissionService(IRolePermissionRepository rolePermissionRepository,
                                 IRoleRepository roleRepository,
                                 IPermissionRepository permissionRepository ) {
        this.rolePermissionRepository = rolePermissionRepository;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    public RolePermission createAction(Long id_user, Long id_permission) {
        Optional<Role> role = roleRepository.findById(id_user);
        Optional<Permission> permission = permissionRepository.findById(id_permission);
        if(role.isPresent()) {
            if(permission.isPresent()) {
                RolePermission rolePermission = new RolePermission();
                rolePermission.setRole(role.get());
                rolePermission.setPermission(permission.get());
                rolePermissionRepository.save(rolePermission);
                return rolePermission;
            } else { return null; }
        } else { return null; }
    }
}
