package com.advance_academy_project.bookstore.service;

import com.advance_academy_project.bookstore.dto.AuthorDto;
import com.advance_academy_project.bookstore.dto.BookDto;
import com.advance_academy_project.bookstore.dto.GanreDto;
import com.advance_academy_project.bookstore.dto.UserDto;
import com.advance_academy_project.bookstore.exception.DataNotFoundException;
import com.advance_academy_project.bookstore.model.*;
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
    private RoleService roleService;
    private BookService bookService;
    private GanreService ganreService;
    private AuthorService authorService;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleService roleService,
                       BookService bookService,
                       GanreService ganreService,
                       AuthorService authorService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.bookService = bookService;
        this.ganreService = ganreService;
        this.authorService = authorService;
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
        User foundUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new DataNotFoundException(String.format("User with username: %s, is not found!", username)));

        UserDto userDto = new UserDto();

        userDto.setId(foundUser.getId());
        userDto.setEmail(foundUser.getEmail());
        userDto.setUsername(foundUser.getUsername());
        userDto.setFirstName(foundUser.getFirstName());
        userDto.setLastName(foundUser.getLastName());
        userDto.setAge(foundUser.getAge());
        userDto.setRole(foundUser.getRole().getRoleName());
        userDto.setBooks(foundUser.getBooks());

        return userDto;
    }


    public void saveUser(@NonNull UserDto userDto) {
        Role role = roleService.findByRoleName("CLIENT");

        User user = new User();

        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setAge(userDto.getAge());
        user.setRole(role);
        user.setBooks(userDto.getBooks());

        userRepository.save(user);
    }


    public void deleteByUsername(@NonNull String username) {
        userRepository.deleteByUsername(username);
    }


    public void addBookToAccound(@NonNull String username, @NonNull String title) {
        User foundUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new DataNotFoundException(String.format("User with username: %s,  does not exist.", username)));

        BookDto wantedBookDto = bookService.findByTitle(title);

        GanreDto ganreDto = ganreService.findByGanreName(wantedBookDto.getGenre());
        Ganre ganre = new Ganre();
        ganre.setId(ganreDto.getId());
        ganre.setGanreName(ganreDto.getGanreName());

        AuthorDto authorDto = authorService.findByName(wantedBookDto.getAuthor());
        Author author = new Author();
        author.setId(authorDto.getId());
        author.setName(authorDto.getName());
        author.setBooks(authorDto.getBooks());

        Book wantedBook = new Book();
        wantedBook.setId(wantedBookDto.getId());
        wantedBook.setTitle(wantedBookDto.getTitle());
        wantedBook.setGenre(ganre);
        wantedBook.setAuthor(author);

        Set<Book> updatedUsersBooks = foundUser.getBooks();
        updatedUsersBooks.add(wantedBook);

        foundUser.setBooks(updatedUsersBooks);
        userRepository.save(foundUser);
    }

    public void removeBookFmAccount(@NonNull String username, @NonNull String title) {
        User foundUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new DataNotFoundException(String.format("User with username: %s,  does not exist.", username)));

        BookDto wantedBookDto = bookService.findByTitle(title);

        GanreDto ganreDto = ganreService.findByGanreName(wantedBookDto.getGenre());
        Ganre ganre = new Ganre();
        ganre.setId(ganreDto.getId());
        ganre.setGanreName(ganreDto.getGanreName());

        AuthorDto authorDto = authorService.findByName(wantedBookDto.getAuthor());
        Author author = new Author();
        author.setId(authorDto.getId());
        author.setName(authorDto.getName());
        author.setBooks(authorDto.getBooks());

        Book wantedBook = new Book();
        wantedBook.setId(wantedBookDto.getId());
        wantedBook.setTitle(wantedBookDto.getTitle());
        wantedBook.setGenre(ganre);
        wantedBook.setAuthor(author);

        Set<Book> updatedUsersBooks = foundUser.getBooks();
        updatedUsersBooks.remove(wantedBook);

        foundUser.setBooks(updatedUsersBooks);
        userRepository.save(foundUser);
    }


}
