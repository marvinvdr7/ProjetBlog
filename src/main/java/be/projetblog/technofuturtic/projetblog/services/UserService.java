package be.projetblog.technofuturtic.projetblog.services;

import be.projetblog.technofuturtic.projetblog.entities.Role;
import be.projetblog.technofuturtic.projetblog.entities.User;
import be.projetblog.technofuturtic.projetblog.entities.UserPermission;
import be.projetblog.technofuturtic.projetblog.entities.UserRole;
import be.projetblog.technofuturtic.projetblog.exceptions.UserNotFoundException;
import be.projetblog.technofuturtic.projetblog.repositories.IRoleRepository;
import be.projetblog.technofuturtic.projetblog.repositories.IUserPermissionRepository;
import be.projetblog.technofuturtic.projetblog.repositories.IUserRepository;
import be.projetblog.technofuturtic.projetblog.repositories.IUserRoleRepository;
import be.projetblog.technofuturtic.projetblog.security.PasswordConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class UserService implements UserDetailsService, ICrudService<User, Long> {

    private IUserRepository userRepository;
    private IRoleRepository roleRepository;
    private IUserRoleRepository userRoleRepository;
    private IUserPermissionRepository userPermissionRepository;
    @Autowired
    public UserService(IUserRepository userRepository,
                       IUserRoleRepository userRolesRepository,
                       IRoleRepository roleRepository,
                       IUserPermissionRepository userPermissionRepository ) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRolesRepository;
        this.roleRepository = roleRepository;
        this.userPermissionRepository = userPermissionRepository;
    }

    /**
    * Retourne la liste de tous les utilisateurs de l'application
    * @return la liste de tous les User présent en DB
    */
    @Override
    public List<User> getAllAction() {
        return userRepository.findAll();
    }

    /**
    * Retourne un utilisateur en fonction de son id. Si l'id n'est pas présent on lance une exception
    * @param id est l'id correspondant à celui du User recherché en DB
    * @return un User de la DB
    */
    @Override
    public User getOneAction(Long id) throws UserNotFoundException {
        Optional<User> userFind = userRepository.findById(id);
        if(userFind.isPresent()){
            return userFind.get();
        } else {
            throw new UserNotFoundException();
        }
    }

    /**
    * Insere un nouvel utilisateur en DB en lui assignant le role de membre et retourne l'utilisateur créé
    * @param entity contient les information nécessaire pour la création du nouvel User
    * @return le User venant d'être créé dans la DB
    */
    @Override
    public User createAction(User entity) {
        Set<UserRole> userRoleSet = createListUserRole(entity);
        Set<UserPermission> userPermissionSet = createListUserPermission(entity);

        entity.setUserPermissions(userPermissionSet);
        entity.setPassword(PasswordConfig.passwordencoder().encode(entity.getPassword()));
        entity.setUserRoles(userRoleSet);

        userRepository.save(entity);
        userRoleSet.forEach(userRoleRepository::save);
        userPermissionSet.forEach(userPermissionRepository::save);

        return entity;
    }

    /**
    * Modifie un utilisateur si celui ci est présent en DB. Retourne l'utilisateur modifié ou lance une exception
    * @param entity contient les information modifiées du user
    * @param id est l'id correspondant à celui du User recherché en DB
    * @return le User venant d'être modifié dans la DB
    */
    @Override
    public User updateAction(User entity, Long id) throws UserNotFoundException {
        Optional<User> userFind = userRepository.findById(id);
        if(userFind.isPresent()){
            entity.setId(id);
            entity.setPassword(userFind.get().getPassword());
            entity.setBirthdate(userFind.get().getBirthdate());
            entity.setUserPermissions(userFind.get().getUserPermissions());
            entity.setUserRoles(userFind.get().getUserRoles());
            entity.setImage(userFind.get().getImage());
            userRepository.save(entity);
            return entity;
        } else {
            throw new UserNotFoundException();
        }
    }

    /**
    * Suprimme un utilisateur si celui ci est présent en DB. Retourne l'utilisateur suprimmé ou lance une exception
    * @param id est l'id correspondant à celui du User recherché en DB
    * @return le User venant d'être suprimmé dans la DB
    */
    @Override
    public User deleteAction(Long id) throws UserNotFoundException {
        Optional<User> userFind = userRepository.findById(id);
        if(userFind.isPresent()){
            User entity = userFind.get();
            userRepository.delete(entity);
            return entity;
        } else {
            throw new UserNotFoundException();
        }
    }

    /**
    * Methode venant de la classe parent UserDetailsService, elle est nécessaire pour l'authentification
    * Recherche un utilisateur par son username et le renvoie sinon lance une exception
    * @param username est le pseudo correspondant à celui du User recherché en DB
    * @return le UserDetails venant d'être trouvé
    */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Objects.requireNonNull(username);
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    /**
    * Recherche un utilisateur par son username et le renvoie sinon lance une exception
    * @param username est le pseudo correspondant à celui du User recherché en DB
    * @return le User venant d'être trouvé
    */
    public User findByUsername(String username) throws UsernameNotFoundException {
        Objects.requireNonNull(username);
        Optional<User> userFind = userRepository.findByUsername(username);
        return userFind.orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    /**
    * Crée la liste des roles d'un nouvel utilisateur (juste le role de membre)
    * @param entity correspond au nouvel utilisateur
    * @return la liste des roles du nouvel utilisateur
    */
    public Set<UserRole> createListUserRole(User entity) {
        Set<UserRole> userRoleSet = new HashSet<>();
        UserRole newUserRole = new UserRole();
        Role roleMembre = roleRepository.getOne(1L);
        newUserRole.setUser(entity);
        newUserRole.setRole(roleMembre);
        userRoleSet.add(newUserRole);

        return userRoleSet;
    }

    /**
    * Crée la liste des permission d'un nouvel utilisateur (les droit du role de membre)
    * @param entity correspond au nouvel utilisateur
    * @return la liste des permissions du nouvel utilisateur
    */
    public Set<UserPermission> createListUserPermission(User entity) {
        Role roleMembre = roleRepository.getOne(1L);
        Set<UserPermission> userPermissionSet = new HashSet<>();
        UserPermission newUserPermission = new UserPermission();
        LocalDate lt = LocalDate.now();
        roleRepository.findRolePermissions(roleMembre.getId())
                .forEach(permission -> {
                    newUserPermission.setPermission(permission);
                    newUserPermission.setUser(entity);
                    newUserPermission.setStartDate(lt);

                    userPermissionSet.add(newUserPermission);
                });
        return userPermissionSet;
    }
}