package be.projetblog.technofuturtic.projetblog.services;

import be.projetblog.technofuturtic.projetblog.entities.Comment;
import be.projetblog.technofuturtic.projetblog.exceptions.CommentNotFoundException;
import be.projetblog.technofuturtic.projetblog.repositories.ICommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService implements ICrudService<Comment, Long> {

    private ICommentRepository commentRepository;
    @Autowired
    public CommentService (ICommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> getAllAction() {
        return commentRepository.findAll();
    }

    @Override
    public Comment getOneAction(Long id) throws CommentNotFoundException {
        Optional<Comment> commentFind = commentRepository.findById(id);
        if(commentFind.isPresent()){
            return commentFind.get();
        } else {
            throw new CommentNotFoundException();
        }
    }

    @Override
    public Comment createAction(Comment com) {
        commentRepository.save(com);
        return com;
    }

    @Override
    public Comment updateAction(Comment entity, Long id) throws CommentNotFoundException {
        Optional<Comment> commentFind = commentRepository.findById(id);
        if(commentFind.isPresent()){
            entity.setId(id);
            commentRepository.save(entity);
            return entity;
        } else {
            throw new CommentNotFoundException();
        }
    }

    @Override
    public Comment deleteAction(Long id) throws CommentNotFoundException {
        Optional<Comment> commentFind = commentRepository.findById(id);
        if (commentFind.isPresent()){
            commentRepository.delete(commentFind.get());
            return commentFind.get();
        } else {
            throw new CommentNotFoundException();
        }
    }

    public List<Comment> findByPostIdAction(Long id) {
        return commentRepository.findByPostId(id);
    }
}