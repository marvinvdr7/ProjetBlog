package be.projetblog.technofuturtic.projetblog.controllers;

import be.projetblog.technofuturtic.projetblog.dto.UserPermissionDTO;
import be.projetblog.technofuturtic.projetblog.dto.UserRoleDTO;
import be.projetblog.technofuturtic.projetblog.entities.UserRole;
import be.projetblog.technofuturtic.projetblog.exceptions.RoleNotFoundException;
import be.projetblog.technofuturtic.projetblog.exceptions.UserNotFoundException;
import be.projetblog.technofuturtic.projetblog.mappers.UserRoleMapper;
import be.projetblog.technofuturtic.projetblog.services.UserRoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-role")
@CrossOrigin(origins = "http://localhost:4200")
public class UserRoleController {

    private UserRoleService userRoleService;
    @Autowired
    public UserRoleController(UserRoleService userRoleService){
        this.userRoleService = userRoleService;
    }

    @RequestMapping(path = {"", "/"}, method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('member:read')")
    public ResponseEntity<List<UserRole>> getAllUserRole() {
        return ResponseEntity.ok(userRoleService.getAllAction());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('member:read')")
    public ResponseEntity<UserRole> getOneUserRole(@PathVariable Long id) throws Exception {
        UserRole userRole = userRoleService.getOneAction(id);
        return ResponseEntity.ok(userRole);
    }

    @GetMapping("/create/{id_user}-{id_role}")
    public ResponseEntity<UserRoleDTO> createUserRole(@PathVariable Long id_user, @PathVariable Long id_role) throws RoleNotFoundException, UserNotFoundException {
        UserRoleDTO userRoleDTO = new UserRoleDTO();
        userRoleDTO.setId_user(id_user);
        userRoleDTO.setId_role(id_role);
        userRoleService.createAction(id_user, id_role);
        return ResponseEntity.ok(userRoleDTO);
    }

    @PutMapping("/{id}-{id_user}-{id_role}")
    @PreAuthorize("hasAuthority('member:write')")
    public ResponseEntity<String> updateUserRoles(@PathVariable Long id, @PathVariable Long id_user, @PathVariable Long id_role) throws Exception {
        userRoleService.updateAction(id, id_user, id_role);
        return ResponseEntity.ok("Membre update");
    }

    @DeleteMapping("/{id_user}-{id_role}")
    public ResponseEntity<UserRoleDTO> deleteUser(@PathVariable Long id_user, @PathVariable Long id_role) throws Exception {
        userRoleService.deleteByUserAndRoleAction(id_user, id_role);
        UserRoleDTO userRoleDTO = new UserRoleDTO();
        userRoleDTO.setId_user(id_user);
        userRoleDTO.setId_role(id_role);
        return ResponseEntity.ok(userRoleDTO);
    }
}
