package com.advance_academy_project.bookstore.service;

import com.advance_academy_project.bookstore.exception.DataNotFoundException;
import com.advance_academy_project.bookstore.model.Role;
import com.advance_academy_project.bookstore.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new DataNotFoundException(String.format("Role %s does not exist.", name)));
    }

    public Role findById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(String.format("Role with id %d does not exist.", id)));
    }

    public Set<Role> findAll() {
        List<Role> rolesList = roleRepository.findAll();
        Set<Role> roles = new HashSet<>(rolesList);
        return roles;
    }

    public void deleteByName(String name) {
        roleRepository.deleteByName(name);
    }

    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }
}
