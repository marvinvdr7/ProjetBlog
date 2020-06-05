package be.projetblog.technofuturtic.projetblog.mappers;

import be.projetblog.technofuturtic.projetblog.dto.CategoryDTO;
import be.projetblog.technofuturtic.projetblog.entities.Category;
import be.projetblog.technofuturtic.projetblog.entities.Post;
import be.projetblog.technofuturtic.projetblog.services.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryMapper implements IGenericMapper<CategoryDTO, Category> {


    private PostService postService;
    @Autowired
    public CategoryMapper(@Lazy PostService postService) {
        this.postService = postService;
    }

    @Override
    public CategoryDTO toDto(Category entity) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    @Override
    public Category toEntity(CategoryDTO dto) {
        Category entity = new Category();
        entity.setId(dto.getId());
        entity.setName(dto.getName());

        List<Post> posts = postService.findByCategoryId(dto.getId());
        entity.setPosts(posts);
        return entity;
    }
}
