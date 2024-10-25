package com.verticallogistica.desafio.service;

import com.verticallogistica.desafio.model.User;
import com.verticallogistica.desafio.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    public UserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        List<User> users = List.of(new User(1L, "John"));
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertEquals(1, result.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testFindUserById() {
        User user = new User(1L, "John");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> result = userService.findUserById(1L);

        assertTrue(result.isPresent());
        assertEquals("John", result.get().getName());
    }

    @Test
    void testSaveUser() {
        User user = new User(1L, "John");
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.saveUser(user);

        assertEquals("John", result.getName());
        verify(userRepository, times(1)).save(user);
    }
}