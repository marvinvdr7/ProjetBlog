package be.projetblog.technofuturtic.projetblog.controllers;

import be.projetblog.technofuturtic.projetblog.dto.RoleDTO;
import be.projetblog.technofuturtic.projetblog.entities.Role;
import be.projetblog.technofuturtic.projetblog.exceptions.RoleNotFoundException;
import be.projetblog.technofuturtic.projetblog.mappers.RoleMapper;
import be.projetblog.technofuturtic.projetblog.services.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/roles")
@CrossOrigin("http://localhost:4200")
public class RoleController {

    private RoleMapper roleMapper;
    private RoleService roleService;
    @Autowired
    public RoleController(RoleService roleService, RoleMapper roleMapper){
        this.roleService = roleService;
        this.roleMapper = roleMapper;
    }

    @PreAuthorize("hasAuthority('member:read')")
    @RequestMapping(path = {"", "/"}, method = RequestMethod.GET)
    public ResponseEntity<List<RoleDTO>> getAll(){
        return ResponseEntity.ok(roleService.getAllAction().stream().map(role -> roleMapper.toDto(role)).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('member:read')")
    public ResponseEntity<RoleDTO> getOne(@PathVariable Long id) throws RoleNotFoundException {
        RoleDTO roleDTO = roleMapper.toDto(roleService.getOneAction(id));
        return ResponseEntity.ok(roleDTO);
    }

    @PostMapping
    public ResponseEntity<RoleDTO> create(@RequestBody Role requestRole){
        roleService.createAction(requestRole);
        return ResponseEntity.ok(roleMapper.toDto(requestRole));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('member:write')")
    public ResponseEntity<RoleDTO> update(@RequestBody Role requestRole, @PathVariable Long id) throws RoleNotFoundException {
        roleService.updateAction(requestRole, id);
        return ResponseEntity.ok(roleMapper.toDto(requestRole));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    public ResponseEntity<RoleDTO> delete(@PathVariable Long id) throws RoleNotFoundException {
        RoleDTO roleDTO = roleMapper.toDto(roleService.deleteAction(id));
        return ResponseEntity.ok(roleDTO);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<RoleDTO>> roleByUser(@PathVariable Long id){
        List<RoleDTO> rolesDTO = roleService.getRolesByUser(id).stream().map(role -> roleMapper.toDto(role)).collect(Collectors.toList());
        return ResponseEntity.ok(rolesDTO);
    }
}
