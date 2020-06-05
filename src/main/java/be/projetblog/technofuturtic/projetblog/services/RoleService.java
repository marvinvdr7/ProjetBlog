package be.projetblog.technofuturtic.projetblog.services;

import be.projetblog.technofuturtic.projetblog.entities.Permission;
import be.projetblog.technofuturtic.projetblog.entities.Role;
import be.projetblog.technofuturtic.projetblog.exceptions.RoleNotFoundException;
import be.projetblog.technofuturtic.projetblog.repositories.IRoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements ICrudService<Role, Long> {

    private IRoleRepository roleRepository;
    @Autowired
    public RoleService(IRoleRepository roleRepository ) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getRolesByUser(Long id) {
        return roleRepository.findRolesByUser(id);
    }

    public List<Permission> getRolesPermissions(Long id) {
        return roleRepository.findRolePermissions(id);
    }

    @Override
    public List<Role> getAllAction() {
        return roleRepository.findAll();
    }

    @Override
    public Role getOneAction(Long id) throws RoleNotFoundException {
        Optional<Role> roleFind = roleRepository.findById(id);
        if(roleFind.isPresent()){
            return roleFind.get();
        } else {
            throw new RoleNotFoundException();
        }
    }

    @Override
    public Role createAction(Role entity) {
        roleRepository.save(entity);
        return entity;
    }

    @Override
    public Role updateAction(Role entity, Long id) throws RoleNotFoundException {
        Optional<Role> roleFind = roleRepository.findById(id);
        if(roleFind.isPresent()){
            entity.setId(id);
            roleRepository.save(entity);
            return entity;
        } else {
            throw new RoleNotFoundException();
        }
    }

    @Override
    public Role deleteAction(Long id) throws RoleNotFoundException {
        Optional<Role> roleFind = roleRepository.findById(id);
        if (roleFind.isPresent()){
            roleRepository.delete(roleFind.get());
            return roleFind.get();
        } else {
            throw new RoleNotFoundException();
        }
    }
}