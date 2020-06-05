package be.projetblog.technofuturtic.projetblog.services;

import be.projetblog.technofuturtic.projetblog.entities.Role;
import be.projetblog.technofuturtic.projetblog.entities.User;
import be.projetblog.technofuturtic.projetblog.entities.UserRole;
import be.projetblog.technofuturtic.projetblog.exceptions.RoleNotFoundException;
import be.projetblog.technofuturtic.projetblog.exceptions.UserNotFoundException;
import be.projetblog.technofuturtic.projetblog.repositories.IRoleRepository;
import be.projetblog.technofuturtic.projetblog.repositories.IUserRepository;
import be.projetblog.technofuturtic.projetblog.repositories.IUserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRoleService {

    private IUserRoleRepository userRolesRepository;
    private IUserRepository userRepository;
    private IRoleRepository roleRepository;
    @Autowired
    public UserRoleService(IUserRoleRepository userRolesRepository,
                           IUserRepository userRepository,
                           IRoleRepository roleRepository ) {
        this.userRolesRepository = userRolesRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<UserRole> getAllAction() {
        return userRolesRepository.findAll();
    }

    public UserRole getOneAction(Long id) throws Exception {
        Optional<UserRole> userRoleFind = userRolesRepository.findById(id);
        if(userRoleFind.isPresent()){
            return userRoleFind.get();
        } else {
            throw new Exception("ce userRole n'existe pas");
        }
    }

    public UserRole createAction(Long id_user, Long id_role) throws UserNotFoundException, RoleNotFoundException {
        Optional<User> user = userRepository.findById(/*ur.getUser().getId()*/id_user);
        Optional<Role> role = roleRepository.findById(/*ur.getRole().getId()*/id_role);
        if(user.isPresent()){
            if(role.isPresent()) {
                UserRole userRole = new UserRole();
                userRole.setUser(user.get());
                userRole.setRole(role.get());
                Optional<UserRole> userRoleFind = userRolesRepository.findByUserAndRoleId(id_user, id_role);
                // si un role user existe avec cet iduser et cet idrole on set l'id
                userRoleFind.ifPresent(value -> userRole.setId(value.getId()));
                userRolesRepository.save(userRole);
                return userRole;
            } else { throw new RoleNotFoundException(); }
        } else { throw new UserNotFoundException(); }
    }

    public UserRole updateAction(Long id, Long id_user, Long id_role) throws Exception {
        Optional<User> user = userRepository.findById(id_user);
        Optional<Role> role = roleRepository.findById(id_role);
        if(user.isPresent()){
            if(role.isPresent()) {
                UserRole userRole = new UserRole();
                userRole.setId(id);
                userRole.setUser(user.get());
                userRole.setRole(role.get());
                userRolesRepository.save(userRole);
                return userRole;
            } else { throw new RoleNotFoundException(); }
        } else { throw new UserNotFoundException(); }
    }

    public UserRole deleteAction(Long id) throws Exception {
        Optional<UserRole> userRoleFind = userRolesRepository.findById(id);
        if(userRoleFind.isPresent()){
            UserRole entity = userRoleFind.get();
            userRolesRepository.delete(entity);
            return entity;
        } else {
            throw new Exception("ce userRole n'existe pas");
        }
    }

    public void deleteByUserAndRoleAction(Long id_user, Long id_role) throws Exception {
        Optional<UserRole> userRoleFind = userRolesRepository.findByUserAndRoleId(id_user, id_role);
        if(userRoleFind.isPresent()) {
            userRolesRepository.deleteByUserAndRoleId(id_user, id_role);
        } else {
            throw new Exception("ce userRole n'existe pas");
        }
    }
}