package be.projetblog.technofuturtic.projetblog.mappers;

import be.projetblog.technofuturtic.projetblog.dto.UserDTO;
import be.projetblog.technofuturtic.projetblog.entities.User;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;


@Component
public class UserMapper implements IGenericMapper<UserDTO, User>{

    @Override
    public UserDTO toDto(User entity) {
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setLastname(entity.getLastname());
        dto.setUsername(entity.getUsername());
        dto.setFirstname(entity.getFirstname());
        dto.setEmail(entity.getEmail());
        dto.setImage(entity.getImage());
        dto.setAuthorities((AuthorityUtils.authorityListToSet((entity).getAuthorities())));
        dto.setBirthdate(entity.getBirthdate());

        return dto;
    }

    @Override
    public User toEntity(UserDTO dto) {
        User entity = new User();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        entity.setLastname(dto.getLastname());
        entity.setFirstname(dto.getFirstname());
        entity.setEmail(dto.getEmail());
        entity.setImage(dto.getImage());
        entity.setBirthdate(dto.getBirthdate());
        return entity;
    }

}