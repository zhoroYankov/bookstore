package com.advance_academy_project.bookstore.converter;

import com.advance_academy_project.bookstore.dto.BookDto;
import com.advance_academy_project.bookstore.dto.UserDto;
import com.advance_academy_project.bookstore.model.Book;
import com.advance_academy_project.bookstore.model.Role;
import com.advance_academy_project.bookstore.model.User;
import com.advance_academy_project.bookstore.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserFullConverter {

    private final BookConverter bookConverter;

    private final RoleService roleService;

    @Autowired
    public UserFullConverter(BookConverter bookConverter, RoleService roleService) {
        this.bookConverter = bookConverter;
        this.roleService = roleService;
    }


    public User convertToEntiry(UserDto userDto) {
        Set<Book> books = new HashSet<>();
        Set<BookDto> bookDtos = userDto.getBooks();

        for (BookDto bookDto : bookDtos) {
            Book book = bookConverter.convertToEntity(bookDto);
            books.add(book);
        }

        Role role = roleService.findByName(userDto.getRole());
        User user = User.builder()
                .id(userDto.getId())
                .email(userDto.getEmail())
                .username(userDto.getUsername())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .age(userDto.getAge())
                .role(role)
                .books(books)
                .build();

        return user;
    }

    public UserDto convertToDto(User user) {
        Set<Book> books = user.getBooks();
        Set<BookDto> bookDtos = new HashSet<>();

        for (Book book : books) {
            BookDto bookDto = bookConverter.convertToDto(book);
            bookDtos.add(bookDto);
        }

        UserDto userDto = UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .age(user.getAge())
                .role(user.getRole().getName())
                .books(bookDtos)
                .build();

        return userDto;
    }
}
