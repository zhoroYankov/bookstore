package com.advance_academy_project.bookstore.converter;

import com.advance_academy_project.bookstore.dto.RoleDto;
import com.advance_academy_project.bookstore.model.Role;

public class RoleConverter {

    public Role convertToEntity(RoleDto roleDto){
        Role role = Role.builder()
                .id(roleDto.getId())
                .name(roleDto.getName())
                .build();
        return role;
    }

    public RoleDto convertToDto(Role role){
        RoleDto roleDto = RoleDto.builder()
                .id(role.getId())
                .name(role.getName())
                .build();

        return roleDto;
    }
}
