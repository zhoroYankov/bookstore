package com.advance_academy_project.bookstore.converter;

import com.advance_academy_project.bookstore.dto.BookDto;
import com.advance_academy_project.bookstore.dto.UserDto;
import com.advance_academy_project.bookstore.dto.UserSignUpDto;
import com.advance_academy_project.bookstore.model.Book;
import com.advance_academy_project.bookstore.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserFullConverter {

    private final BookConverter bookConverter;

    @Autowired
    public UserFullConverter(BookConverter bookConverter) {
        this.bookConverter = bookConverter;
    }


    public User convertToEntiry(UserDto userDto){
        Set<Book> books = new HashSet<>();
        Set<BookDto> bookDtos = userDto.getBooks();

        for (BookDto bookDto : bookDtos){
            Book book = bookConverter.convertToEntity(bookDto);
            books.add(book);
        }

        User user = User.builder()
                .id(userDto.getId())
                .email(userDto.getEmail())
                .username(userDto.getUsername())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .age(userDto.getAge())
                .books(books)
                .build();

        return user;
    }

    public UserDto convertToDto(User user){
        Set<Book> books = user.getBooks();
        Set<BookDto> bookDtos = new HashSet<>();

        for (Book book : books){
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
                .role(user.getType().name())
                .books(bookDtos)
                .build();

        return userDto;
    }
}
