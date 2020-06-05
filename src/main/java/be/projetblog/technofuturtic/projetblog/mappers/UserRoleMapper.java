package be.projetblog.technofuturtic.projetblog.mappers;


import be.projetblog.technofuturtic.projetblog.dto.UserRoleDTO;
import be.projetblog.technofuturtic.projetblog.entities.UserRole;

import org.springframework.stereotype.Component;

@Component
public class UserRoleMapper implements IGenericMapper<UserRoleDTO, UserRole> {

    @Override
    public UserRoleDTO toDto(UserRole entity) {
        UserRoleDTO dto = new UserRoleDTO();
        dto.setId_role(entity.getRole().getId());
        dto.setId_user(entity.getUser().getId());

        return dto;
    }

    @Override
    public UserRole toEntity(UserRoleDTO dto) {
        return null;
    }
}
