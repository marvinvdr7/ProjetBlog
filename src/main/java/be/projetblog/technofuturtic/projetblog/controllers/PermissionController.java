package be.projetblog.technofuturtic.projetblog.controllers;

import be.projetblog.technofuturtic.projetblog.dto.PermissionDTO;
import be.projetblog.technofuturtic.projetblog.mappers.PermissionMapper;
import be.projetblog.technofuturtic.projetblog.services.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/permissions")
@CrossOrigin("http://localhost:4200")
public class PermissionController {

    private PermissionService permissionService;
    private PermissionMapper permissionMapper;
    @Autowired
    public PermissionController (PermissionService permissionService, PermissionMapper permissionMapper) {
        this.permissionService = permissionService;
        this.permissionMapper = permissionMapper;
    }

    @GetMapping("/role/{id}")
    public ResponseEntity<List<PermissionDTO>> permissionByRole(@PathVariable Long id){
        List<PermissionDTO> permissionsDTO = permissionService.getPermissionByRole(id).stream().map(permission -> permissionMapper.toDto(permission)).collect(Collectors.toList());
        return ResponseEntity.ok(permissionsDTO);
    }
}
