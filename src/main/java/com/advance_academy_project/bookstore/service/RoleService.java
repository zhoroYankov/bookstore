package com.advance_academy_project.bookstore.service;

import com.advance_academy_project.bookstore.exception.DataNotFoundException;
import com.advance_academy_project.bookstore.model.Role;
import com.advance_academy_project.bookstore.repository.RoleRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findByRoleName(@NonNull String roleName){
       return roleRepository.findByRoleName(roleName)
                .orElseThrow(()->new DataNotFoundException(String.format("Role with this name: %s does not exist.", roleName)));

    }

    public Set<Role> findAllRoles(){
        return new HashSet<>(roleRepository.findAll());
    }

    public Role saveRole(Role role){
        return roleRepository.save(role);
    }


    public void deleteRoleById(Long roleId){
        roleRepository.deleteById(roleId);
    }
}
