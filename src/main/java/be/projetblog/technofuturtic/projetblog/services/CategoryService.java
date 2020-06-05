package be.projetblog.technofuturtic.projetblog.services;

import be.projetblog.technofuturtic.projetblog.entities.Category;
import be.projetblog.technofuturtic.projetblog.exceptions.CategoryNotFoundException;
import be.projetblog.technofuturtic.projetblog.repositories.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICrudService<Category, Long> {

    private ICategoryRepository categoryRepository;
    @Autowired
    public CategoryService (ICategoryRepository categoryRepository ) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllAction() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getOneAction(Long id) throws CategoryNotFoundException {
        Optional<Category> categoryFind = categoryRepository.findById(id);
        if(categoryFind.isPresent()) {
            return categoryFind.get();
        } else {
            throw new CategoryNotFoundException();
        }
    }

    @Override
    public Category createAction(Category entity) {
        categoryRepository.save(entity);
        return entity;
    }

    @Override
    public Category updateAction(Category entity, Long id) throws CategoryNotFoundException {
        Optional<Category> categoryFind = categoryRepository.findById(id);
        if(categoryFind.isPresent()){
            entity.setId(id);
            categoryRepository.save(entity);
            return entity;
        } else {
            throw new CategoryNotFoundException();
        }
    }

    @Override
    public Category deleteAction(Long id) throws CategoryNotFoundException {
        Optional<Category> categoryFind = categoryRepository.findById(id);
        if (categoryFind.isPresent()){
            categoryRepository.delete(categoryFind.get());
            return categoryFind.get();
        } else {
            throw new CategoryNotFoundException();
        }
    }
}