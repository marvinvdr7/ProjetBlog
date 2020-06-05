package be.projetblog.technofuturtic.projetblog.controllers;

import be.projetblog.technofuturtic.projetblog.dto.PostDTO;
import be.projetblog.technofuturtic.projetblog.exceptions.CategoryNotFoundException;
import be.projetblog.technofuturtic.projetblog.exceptions.PostNotFoundException;
import be.projetblog.technofuturtic.projetblog.exceptions.UserNotFoundException;
import be.projetblog.technofuturtic.projetblog.mappers.PostMapper;
import be.projetblog.technofuturtic.projetblog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
@CrossOrigin("http://localhost:4200")
public class PostController {

    private PostMapper postMapper;
    private PostService postService;
    @Autowired
    public PostController(@Lazy PostService postService, PostMapper postMapper){
        this.postMapper = postMapper;
        this.postService = postService;
    }

    @RequestMapping(path = {"", "/"}, method = RequestMethod.GET)
    public ResponseEntity<List<PostDTO>> getAll() {
        return ResponseEntity.ok(postService.getAllAction().stream().map(post -> {
            try {
                return postMapper.toDto(post);
            } catch (UserNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }).collect(Collectors.toList()));
    }

    @RequestMapping(path = {"/category/{id}"}, method = RequestMethod.GET)
    public ResponseEntity<List<PostDTO>> getByCatId(@PathVariable Long id){
        return ResponseEntity.ok(postService.findByCategoryId(id).stream().map(post -> {
            try {
                return postMapper.toDto(post);
            } catch (UserNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getOne(@PathVariable Long id) throws PostNotFoundException, UserNotFoundException {
        PostDTO post = postMapper.toDto(postService.getOneAction(id));
        return ResponseEntity.ok(post);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('post:write')")
    public ResponseEntity<PostDTO> create(@RequestBody PostDTO requestPost) throws CategoryNotFoundException {
        postService.createAction(postMapper.toEntity(requestPost));
        return ResponseEntity.ok(requestPost);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('post:write')")
    public ResponseEntity<PostDTO> update(@RequestBody PostDTO requestPost, @PathVariable Long id) throws PostNotFoundException, UserNotFoundException, CategoryNotFoundException {
        postService.updateAction(postMapper.toEntity(requestPost), id);
        return ResponseEntity.ok(requestPost);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('post:write')")
    public ResponseEntity<PostDTO> delete(@PathVariable Long id) throws PostNotFoundException, UserNotFoundException {
        PostDTO post = postMapper.toDto(postService.deleteAction(id));
        return ResponseEntity.ok(post);
    }
}
