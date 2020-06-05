package be.projetblog.technofuturtic.projetblog.controllers;

import be.projetblog.technofuturtic.projetblog.dto.UserPermissionDTO;
import be.projetblog.technofuturtic.projetblog.entities.UserPermission;
import be.projetblog.technofuturtic.projetblog.exceptions.PermissionNotFoundException;
import be.projetblog.technofuturtic.projetblog.exceptions.UserNotFoundException;
import be.projetblog.technofuturtic.projetblog.mappers.UserPermissionMapper;
import be.projetblog.technofuturtic.projetblog.services.UserPermissionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user-permission")
@CrossOrigin(origins = "http://localhost:4200")
public class UserPermissionController {

    private UserPermissionMapper userPermissionMapper;
    private UserPermissionService userPermissionService;
    @Autowired
    public UserPermissionController(UserPermissionService userPermissionService,
                                    UserPermissionMapper userPermissionMapper ){
        this.userPermissionMapper = userPermissionMapper;
        this.userPermissionService = userPermissionService;
    }

    @GetMapping("/create/{id_user}-{id_permission}")
    public ResponseEntity<UserPermissionDTO> createUserPermission(@PathVariable Long id_user, @PathVariable Long id_permission) throws PermissionNotFoundException, UserNotFoundException {
        UserPermissionDTO userPermissionDTO = new UserPermissionDTO();
        userPermissionDTO.setUser_id(id_user);
        userPermissionDTO.setPermission_id(id_permission);
        userPermissionService.createAction(id_user, id_permission);
        return ResponseEntity.ok(userPermissionDTO);
    }

    @RequestMapping(path = {"", "/"}, method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('member:read')")
    public ResponseEntity<List<UserPermissionDTO>> getAllUserPermission() {
        return ResponseEntity.ok(userPermissionService.getAllAction().stream().map(userPermission -> userPermissionMapper.toDto(userPermission)).collect(Collectors.toList()));
    }

    @GetMapping("user/{id_user}")
    @PreAuthorize("hasAuthority('member:read')")
    public ResponseEntity<List<UserPermissionDTO>> getOneUserPermission(@PathVariable Long id_user) {
        List<UserPermissionDTO> userPermissions = userPermissionService.findByUserId(id_user).stream().map(userPermission -> userPermissionMapper.toDto(userPermission)).collect(Collectors.toList());
        return ResponseEntity.ok(userPermissions);
    }

    @DeleteMapping("/{id_user}-{id_permission}")
    public ResponseEntity<UserPermissionDTO> deleteUserPermission(@PathVariable Long id_user, @PathVariable Long id_permission) throws Exception {
        userPermissionService.deleteByUserAndPermissionAction(id_user, id_permission);
        UserPermissionDTO userPermissionDTO = new UserPermissionDTO();
        userPermissionDTO.setUser_id(id_user);
        userPermissionDTO.setPermission_id(id_permission);
        return ResponseEntity.ok(userPermissionDTO);
    }
}
