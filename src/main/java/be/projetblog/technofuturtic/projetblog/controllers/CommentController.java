package be.projetblog.technofuturtic.projetblog.controllers;

import be.projetblog.technofuturtic.projetblog.entities.Comment;
import be.projetblog.technofuturtic.projetblog.exceptions.CommentNotFoundException;
import be.projetblog.technofuturtic.projetblog.services.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@CrossOrigin("http://localhost:4200")
public class CommentController {

    private CommentService commentService;
    @Autowired
    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @PreAuthorize("hasAuthority('member:read')")
    @RequestMapping(path = {"", "/"}, method = RequestMethod.GET)
    public ResponseEntity<List<Comment>> getAll(){
        return ResponseEntity.ok(commentService.getAllAction());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('member:read')")
    public ResponseEntity<Comment> getOne(@PathVariable Long id) throws CommentNotFoundException {
        Comment comment = commentService.getOneAction(id);
        return ResponseEntity.ok(comment);
    }

    @PostMapping
    public ResponseEntity<Comment> create(@RequestBody Comment requestComment){
        commentService.createAction(requestComment);
        return ResponseEntity.ok(requestComment);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('member:write')")
    public ResponseEntity<Comment> update(@RequestBody Comment requestComment, @PathVariable Long id) throws CommentNotFoundException {
        commentService.updateAction(requestComment, id);
        return ResponseEntity.ok(requestComment);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    public ResponseEntity<Comment> delete(@PathVariable Long id) throws CommentNotFoundException {
        Comment comment = commentService.deleteAction(id);
        return ResponseEntity.ok(comment);
    }
}
