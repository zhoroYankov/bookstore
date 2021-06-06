package com.advance_academy_project.bookstore.controller;

import com.advance_academy_project.bookstore.model.Role;
import com.advance_academy_project.bookstore.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(value = "/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<Set<Role>> findAll(){
       Set<Role> roles = roleService.findAll();
       return ResponseEntity.ok(roles);
    }

    @GetMapping(value = "/name")
    public ResponseEntity<Role> findByName(@PathVariable String name){
         Role role = roleService.findByName(name);
         return ResponseEntity.ok(role);
    }




}
