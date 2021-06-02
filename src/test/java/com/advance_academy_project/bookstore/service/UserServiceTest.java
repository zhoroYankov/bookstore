package com.advance_academy_project.bookstore.service;

import com.advance_academy_project.bookstore.exception.DataNotFoundException;
import com.advance_academy_project.bookstore.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private AuthorService authorService;
    private BookService bookService;
    private UserService userService;

    @BeforeEach
    public void setUp(){
        userService = new UserService(userRepository, bookService, authorService);
    }

    @Test
    void verifyFindByUsernameThrowsNotFoundException() {
        String username = "asd";

        assertThrows(DataNotFoundException.class,
                ()-> userService.findByUsername(username));
    }

    @Test
    void verifFindByUsernameThrowsNullPointerException() {
        assertThrows(NullPointerException.class,
                ()-> userService.findByUsername(null));
    }


}