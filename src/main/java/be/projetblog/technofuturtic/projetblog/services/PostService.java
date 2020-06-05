package be.projetblog.technofuturtic.projetblog.services;

import be.projetblog.technofuturtic.projetblog.entities.Post;
import be.projetblog.technofuturtic.projetblog.exceptions.PostNotFoundException;
import be.projetblog.technofuturtic.projetblog.repositories.IPostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService implements ICrudService<Post, Long> {

    private IPostRepository postRepository;
    @Autowired
    public PostService (IPostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findByCategoryId(Long id) {
        return postRepository.findByCategoryId(id);
    }
    @Override
    public List<Post> getAllAction() {
        return postRepository.findAll();
    }

    @Override
    public Post getOneAction(Long id) throws PostNotFoundException {
        Optional<Post> postFind = postRepository.findById(id);
        if(postFind.isPresent()){
            return postFind.get();
        } else {
            throw new PostNotFoundException();
        }
    }

    @Override
    public Post createAction(Post post) {
        postRepository.save(post);
        return post;
    }

    @Override
    public Post updateAction(Post post, Long id) throws PostNotFoundException {
        Optional<Post> postFind = postRepository.findById(id);
        if(postFind.isPresent()){
            post.setId(id);
            postRepository.save(post);
            return post;
        } else {
            throw new PostNotFoundException();
        }
    }

    @Override
    public Post deleteAction(Long id) throws PostNotFoundException {
        Optional<Post> postFind = postRepository.findById(id);
        if (postFind.isPresent()){
            postRepository.delete(postFind.get());
            return postFind.get();
        } else {
            throw new PostNotFoundException();
        }
    }
}