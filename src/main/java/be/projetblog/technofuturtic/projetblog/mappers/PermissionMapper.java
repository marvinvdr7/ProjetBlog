package be.projetblog.technofuturtic.projetblog.mappers;

import be.projetblog.technofuturtic.projetblog.dto.PermissionDTO;
import be.projetblog.technofuturtic.projetblog.entities.Permission;

import org.springframework.stereotype.Component;

@Component
public class PermissionMapper implements IGenericMapper<PermissionDTO, Permission> {

    @Override
    public PermissionDTO toDto(Permission entity) {
        PermissionDTO permissionDTO = new PermissionDTO();
        permissionDTO.setId(entity.getId());
        permissionDTO.setName(entity.getName());
        return permissionDTO;
    }

    @Override
    public Permission toEntity(PermissionDTO dto) {
        Permission permission = new Permission();
        permission.setId(dto.getId());
        permission.setName(dto.getName());
        return permission;
    }
}
