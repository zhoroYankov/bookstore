package com.advance_academy_project.bookstore.service;

import com.advance_academy_project.bookstore.dto.UserDto;
import com.advance_academy_project.bookstore.exception.DataNotFoundException;
import com.advance_academy_project.bookstore.model.Role;
import com.advance_academy_project.bookstore.model.User;
import com.advance_academy_project.bookstore.repository.UserRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private UserRepository userRepository;
    private BookService bookService;
    private RoleService roleService;

    @Autowired
    public UserService(UserRepository userRepository, BookService bookService, RoleService roleService) {
        this.userRepository = userRepository;
        this.bookService = bookService;
        this.roleService = roleService;
    }

    public Set<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();

        Set<UserDto> userDtos = new HashSet<>();

        for (User user : users) {
            UserDto userDto = new UserDto();

            userDto.setId(user.getId());
            userDto.setEmail(user.getEmail());
            userDto.setUsername(user.getUsername());
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            userDto.setAge(user.getAge());
            userDto.setRole(user.getRole().getRoleName());
            userDto.setBooks(user.getBooks());

            userDtos.add(userDto);
        }

        return userDtos;
    }


    public UserDto findByUsername(@NonNull String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new DataNotFoundException(String.format("User with username: %s is not found!", username)));

        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setAge(user.getAge());
        userDto.setRole(user.getRole().getRoleName());
        userDto.setBooks(user.getBooks());

        return userDto;
    }


    public void saveUser(@NonNull UserDto userDto) {
        Role role = roleService.f


        User user = new User();

        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setAge(userDto.getAge());
        user.setRole();
        user.setBooks(userDto.getBooks());

        userRepository.save(user);
    }


}
