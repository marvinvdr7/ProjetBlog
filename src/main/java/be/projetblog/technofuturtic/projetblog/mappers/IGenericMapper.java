package be.projetblog.technofuturtic.projetblog.mappers;

import be.projetblog.technofuturtic.projetblog.exceptions.CategoryNotFoundException;
import be.projetblog.technofuturtic.projetblog.exceptions.UserNotFoundException;

public interface IGenericMapper<TDTO, TENTITY> {
    TDTO toDto(TENTITY entity) throws UserNotFoundException;
    TENTITY toEntity(TDTO dto) throws CategoryNotFoundException;
}
