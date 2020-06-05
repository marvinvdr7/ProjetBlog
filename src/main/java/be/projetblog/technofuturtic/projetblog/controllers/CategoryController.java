package be.projetblog.technofuturtic.projetblog.controllers;

import be.projetblog.technofuturtic.projetblog.dto.CategoryDTO;
import be.projetblog.technofuturtic.projetblog.entities.Category;
import be.projetblog.technofuturtic.projetblog.exceptions.CategoryNotFoundException;
import be.projetblog.technofuturtic.projetblog.mappers.CategoryMapper;
import be.projetblog.technofuturtic.projetblog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
@CrossOrigin("http://localhost:4200")
public class CategoryController {

    private CategoryMapper categoryMapper;
    private CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService, @Lazy CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @RequestMapping(path = {"", "/"}, method = RequestMethod.GET)
    public ResponseEntity<List<CategoryDTO>> getAll() {
        return ResponseEntity.ok(categoryService.getAllAction().stream().map(category -> categoryMapper.toDto(category)).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getOne(@PathVariable Long id) throws CategoryNotFoundException {
        CategoryDTO category = categoryMapper.toDto(categoryService.getOneAction(id));
        return ResponseEntity.ok(category);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('category:write')")
    public ResponseEntity<CategoryDTO> create(@RequestBody Category requestCategory) {
        categoryService.createAction(requestCategory);
        return ResponseEntity.ok(categoryMapper.toDto(requestCategory));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('category:write')")
    public ResponseEntity<CategoryDTO> update(@RequestBody Category requestCategory, @PathVariable Long id) throws CategoryNotFoundException {
        categoryService.updateAction(requestCategory, id);
        return ResponseEntity.ok(categoryMapper.toDto(requestCategory));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('category:write')")
    public ResponseEntity<CategoryDTO> delete(@PathVariable Long id) throws CategoryNotFoundException {
        CategoryDTO category = categoryMapper.toDto(categoryService.deleteAction(id));
        return ResponseEntity.ok(category);
    }
}
