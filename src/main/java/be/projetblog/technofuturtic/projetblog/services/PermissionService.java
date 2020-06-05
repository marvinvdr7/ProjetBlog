package be.projetblog.technofuturtic.projetblog.services;

import be.projetblog.technofuturtic.projetblog.entities.Permission;
import be.projetblog.technofuturtic.projetblog.entities.Role;
import be.projetblog.technofuturtic.projetblog.exceptions.PermissionNotFoundException;
import be.projetblog.technofuturtic.projetblog.repositories.IPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionService implements ICrudService<Permission, Long> {

    private IPermissionRepository permissionRepository;
    @Autowired
    public PermissionService(IPermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public List<Permission> getAllAction() {
        return permissionRepository.findAll();
    }

    @Override
    public Permission getOneAction(Long id) throws PermissionNotFoundException {
        Optional<Permission> permissionFind = permissionRepository.findById(id);
        if(permissionFind.isPresent()){
            return permissionFind.get();
        } else {
            throw new PermissionNotFoundException();
        }
    }

    @Override
    public Permission createAction(Permission entity) {
        permissionRepository.save(entity);
        return entity;
    }

    @Override
    public Permission updateAction(Permission entity, Long id) throws PermissionNotFoundException {
        Optional<Permission> permissionFind = permissionRepository.findById(id);
        if(permissionFind.isPresent()){
            entity.setId(id);
            permissionRepository.save(entity);
            return entity;
        } else {
            throw new PermissionNotFoundException();
        }
    }

    @Override
    public Permission deleteAction(Long id) throws PermissionNotFoundException {
        Optional<Permission> permissionFind = permissionRepository.findById(id);
        if (permissionFind.isPresent()){
            permissionRepository.delete(permissionFind.get());
            return permissionFind.get();
        } else {
            throw new PermissionNotFoundException();
        }
    }

    public List<Permission> getPermissionByRole(Long id) {
        return permissionRepository.findPermissionsByRole(id);
    }
}