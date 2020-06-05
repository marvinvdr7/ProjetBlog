package be.projetblog.technofuturtic.projetblog.controllers;

import be.projetblog.technofuturtic.projetblog.dto.UserDTO;
import be.projetblog.technofuturtic.projetblog.entities.User;
import be.projetblog.technofuturtic.projetblog.exceptions.UserNotFoundException;
import be.projetblog.technofuturtic.projetblog.mappers.UserMapper;
import be.projetblog.technofuturtic.projetblog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private UserMapper userMapper;
    private UserService userService;
    @Autowired
    public UserController(UserService userService,
                          UserMapper userMapper ) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @RequestMapping(path = {"", "/"}, method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('member:read')")
    public ResponseEntity<List<UserDTO>> getAllUser() {
        return ResponseEntity.ok(userService.getAllAction().stream().map(user -> userMapper.toDto(user)).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('member:read')")
    public ResponseEntity<UserDTO> getOneUser(@PathVariable Long id) throws UserNotFoundException {
        UserDTO userDTO = userMapper.toDto(userService.getOneAction(id));
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody User requestUser) {
        userService.createAction(requestUser);
        return ResponseEntity.ok(userMapper.toDto(requestUser));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('member:write')")
    public ResponseEntity<UserDTO> updateUser(@RequestBody User requestUser, @PathVariable Long id) throws UserNotFoundException {
        userService.updateAction(requestUser, id);
        return ResponseEntity.ok(userMapper.toDto(requestUser));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable Long id) throws UserNotFoundException {
        UserDTO userDTO = userMapper.toDto(userService.deleteAction(id));
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/username/{username}")
    @PreAuthorize("hasAuthority('member:read')")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        UserDTO userDTO = userMapper.toDto(userService.findByUsername(username));
        return ResponseEntity.ok(userDTO);
    }

}
