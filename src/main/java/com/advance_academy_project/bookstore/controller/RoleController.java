package com.advance_academy_project.bookstore.controller;

import com.advance_academy_project.bookstore.model.Role;
import com.advance_academy_project.bookstore.service.RoleService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "/roles")
public class RoleController {

    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<Set<Role>> findAllRoles(){
        return ResponseEntity.ok(roleService.findAllRoles());
    }

    @PostMapping
    public ResponseEntity<HttpStatus> saveRole(@NonNull Role role){
        roleService.saveRole(role);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(value = "{roleId}")
    public ResponseEntity<HttpStatus> deleteRoleById(@NonNull Long roleId){
        roleService.deleteRoleById(roleId);
        return ResponseEntity.ok().build();
    }


}
