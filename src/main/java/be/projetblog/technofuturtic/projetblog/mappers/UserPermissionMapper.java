package be.projetblog.technofuturtic.projetblog.mappers;

import be.projetblog.technofuturtic.projetblog.dto.UserPermissionDTO;
import be.projetblog.technofuturtic.projetblog.entities.UserPermission;

import org.springframework.stereotype.Component;

@Component
public class UserPermissionMapper implements IGenericMapper<UserPermissionDTO, UserPermission> {

    @Override
    public UserPermissionDTO toDto(UserPermission entity) {
        UserPermissionDTO dto = new UserPermissionDTO();

        dto.setUser_id(entity.getUser().getId());
        dto.setPermission_id(entity.getPermission().getId());
        dto.setStart_date(entity.getStartDate());

        return dto;
    }

    @Override
    public UserPermission toEntity(UserPermissionDTO dto) {
        return null;
    }
}
