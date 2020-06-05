package be.projetblog.technofuturtic.projetblog.mappers;

import be.projetblog.technofuturtic.projetblog.dto.PostDTO;
import be.projetblog.technofuturtic.projetblog.dto.UserDTO;
import be.projetblog.technofuturtic.projetblog.entities.Comment;
import be.projetblog.technofuturtic.projetblog.entities.Post;
import be.projetblog.technofuturtic.projetblog.entities.User;
import be.projetblog.technofuturtic.projetblog.exceptions.CategoryNotFoundException;
import be.projetblog.technofuturtic.projetblog.exceptions.UserNotFoundException;
import be.projetblog.technofuturtic.projetblog.services.CategoryService;
import be.projetblog.technofuturtic.projetblog.services.CommentService;
import be.projetblog.technofuturtic.projetblog.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostMapper implements IGenericMapper<PostDTO, Post>{

    private UserMapper userMapper;
    private UserService userService;
    private CommentService commentService;
    private CategoryService categoryService;
    @Autowired
    public PostMapper(UserMapper userMapper,
                      UserService userService,
                      CommentService commentService,
                      CategoryService categoryService ){
        this.userMapper = userMapper;
        this.userService = userService;
        this.commentService = commentService;
        this.categoryService = categoryService;
    }

    @Override
    public PostDTO toDto(Post entity) throws UserNotFoundException {
        PostDTO dto = new PostDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setDate(entity.getDate());
        dto.setImage(entity.getImage());
        dto.setCategory_id(entity.getCategory().getId());

        UserDTO user = userMapper.toDto(userService.getOneAction(entity.getUser().getId()));
        dto.setAuthor(user.getUsername());

        return dto;
    }

    @Override
    public Post toEntity(PostDTO dto) throws CategoryNotFoundException {
        Post entity = new Post();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setDate(dto.getDate());
        entity.setImage(dto.getImage());
        entity.setCategory(categoryService.getOneAction(dto.getCategory_id()));

        User user = userService.findByUsername(dto.getAuthor());
        entity.setUser(user);

        List<Comment> comments = commentService.findByPostIdAction(dto.getId());
        entity.setComments(comments);

        return entity;
    }
}
