package be.projetblog.technofuturtic.projetblog.services;

import be.projetblog.technofuturtic.projetblog.exceptions.*;

import java.util.List;

public interface ICrudService<TENTITY, TID> {
    List<TENTITY> getAllAction();
    TENTITY getOneAction(TID id) throws UserNotFoundException, CategoryNotFoundException, CommentNotFoundException, PermissionNotFoundException, PostNotFoundException, RoleNotFoundException;
    TENTITY createAction(TENTITY entity);
    TENTITY updateAction(TENTITY entity, TID id) throws UserNotFoundException, CategoryNotFoundException, CommentNotFoundException, PermissionNotFoundException, PostNotFoundException, RoleNotFoundException;
    TENTITY deleteAction(TID id) throws UserNotFoundException, CategoryNotFoundException, CommentNotFoundException, PermissionNotFoundException, PostNotFoundException, RoleNotFoundException;
}
