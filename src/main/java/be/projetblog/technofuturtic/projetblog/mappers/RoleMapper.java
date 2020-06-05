package be.projetblog.technofuturtic.projetblog.mappers;

import be.projetblog.technofuturtic.projetblog.dto.RoleDTO;
import be.projetblog.technofuturtic.projetblog.entities.Role;
import be.projetblog.technofuturtic.projetblog.services.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RoleMapper implements IGenericMapper<RoleDTO, Role>{

    private RoleService roleService;
    private PermissionMapper permissionMapper;
    @Autowired
    public RoleMapper (RoleService roleService,
                       PermissionMapper permissionMapper){
        this.roleService = roleService;
        this.permissionMapper = permissionMapper;
    }

    @Override
    public RoleDTO toDto(Role entity) {
        RoleDTO dto = new RoleDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPermissions(roleService.getRolesPermissions(entity.getId()).stream().map(permission -> permissionMapper.toDto(permission)).collect(Collectors.toList()));
        return dto;
    }

    @Override
    public Role toEntity(RoleDTO dto) {
        Role entity = new Role();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }
}
