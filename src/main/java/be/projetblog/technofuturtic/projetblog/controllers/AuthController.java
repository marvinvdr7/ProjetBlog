package be.projetblog.technofuturtic.projetblog.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping
@CrossOrigin("http://localhost:4200")
public class AuthController {

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ResponseEntity<Set<String>> login(Principal user) {
        Set<String> authorities = (AuthorityUtils.authorityListToSet(((Authentication) user)
                .getAuthorities()));
        return ResponseEntity.ok(authorities);
    }

    @RequestMapping(path = "/user-roles", method = RequestMethod.GET)
    public ResponseEntity<Set<String>> getRoles(Principal user) {
        Set<String> authorities = (AuthorityUtils.authorityListToSet(((Authentication) user)
                .getAuthorities()));
        return ResponseEntity.ok(authorities);
    }
}