package be.projetblog.technofuturtic.projetblog.controllers;

import be.projetblog.technofuturtic.projetblog.entities.UserPermissionTrace;
import be.projetblog.technofuturtic.projetblog.services.UserPermissionTraceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-permission-trace")
@CrossOrigin(origins = "http://localhost:4200")
public class UserPermissionTraceController {

    private UserPermissionTraceService userPermissionTraceService;
    @Autowired
    public UserPermissionTraceController(UserPermissionTraceService userPermissionTraceService){
        this.userPermissionTraceService = userPermissionTraceService;
    }

    @PostMapping("/")
    public ResponseEntity<UserPermissionTrace> createUserPermission(@RequestBody UserPermissionTrace UserPermissionTraceRequest) throws Exception {
        userPermissionTraceService.createAction(UserPermissionTraceRequest);
        return ResponseEntity.ok(UserPermissionTraceRequest);
    }
}
