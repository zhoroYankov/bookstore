package com.advance_academy_project.bookstore.service;

import com.advance_academy_project.bookstore.exception.DataNotFoundException;
import com.advance_academy_project.bookstore.model.Book;
import com.advance_academy_project.bookstore.model.User;
import com.advance_academy_project.bookstore.repository.UserRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class UserService {

    private UserRepository userRepository;
    private BookService bookService;
    private RoleService roleService;

    @Autowired
    public UserService(UserRepository userRepository,  BookService bookService, RoleService roleService) {
        this.userRepository = userRepository;
        this.bookService = bookService;
        this.roleService = roleService;
    }


    public Set<User> findAll() {
        List<User> usersList = userRepository.findAll();
        Set<User> users = usersList.stream().collect(Collectors.toSet());

        return users;
    }


    public User findByUsername(@NonNull String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new DataNotFoundException(String.format("User with username: %s does not exist.", username)));
    }


    public void save(@NonNull User user) {
        user.setRole(roleService.findByName("CLIENT"));
        userRepository.save(user);
    }

    public void updateToVIP(@NonNull String username){
        User foundUser = userRepository.findByUsername(username)
                .orElseThrow(()-> new DataNotFoundException(String.format("User with username: %s does not exist.", username)));
        foundUser.setRole(roleService.findByName("VIP"));
        userRepository.save(foundUser);
    }

    public void updateToADMIN(@NonNull String username){
        User foundUser = userRepository.findByUsername(username)
                .orElseThrow(()-> new DataNotFoundException(String.format("User with username: %s does not exist.", username)));
        foundUser.setRole(roleService.findByName("ADMIN"));
    }


    public void deleteByUsername(@NonNull String username) {
        userRepository.deleteByUsername(username);
    }


    public void addBook(@NonNull String username, @NonNull Book book) {
        User foundUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new DataNotFoundException(String.format("User with username: %s,  does not exist.", username)));

        Book wantedBook = bookService.findByTitle(book.getTitle());

        foundUser.getBooks().add(wantedBook);
        userRepository.save(foundUser);
    }

    public void removeBook(@NonNull String username, @NonNull Book book) {
        User foundUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new DataNotFoundException(String.format("User with username: %s,  does not exist.", username)));
        Book wantedBook = bookService.findByTitle(book.getTitle());
        foundUser.getBooks().remove(wantedBook);
        userRepository.save(foundUser);
    }


}
