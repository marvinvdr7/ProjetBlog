package be.projetblog.technofuturtic.projetblog.controllers;

import be.projetblog.technofuturtic.projetblog.dto.RolePermissionDTO;
import be.projetblog.technofuturtic.projetblog.services.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role-permission")
@CrossOrigin(origins = "http://localhost:4200")
public class RolePermissionController {

    private RolePermissionService rolePermissionService;
    @Autowired
    public RolePermissionController (RolePermissionService rolePermissionService) {
        this.rolePermissionService = rolePermissionService;
    }

    @GetMapping("/create/{id_role}-{id_permission}")
    public ResponseEntity<RolePermissionDTO> createUserPermission(@PathVariable Long id_role, @PathVariable Long id_permission) {
        RolePermissionDTO rolePermissionDTO = new RolePermissionDTO();
        rolePermissionDTO.setRole_id(id_role);
        rolePermissionDTO.setPermission_id(id_permission);
        rolePermissionService.createAction(id_role, id_permission);

        return ResponseEntity.ok(rolePermissionDTO);
    }
}
