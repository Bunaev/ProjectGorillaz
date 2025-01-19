package com.javarush.questproject.repository;

import com.javarush.questproject.entity.Role;
import com.javarush.questproject.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class UserRepositoryTest {
    private static UserRepository userRepository;
    public static User user;

    @BeforeAll
    static void setUp() {
        userRepository = spy(UserRepository.class);
        user = spy(User.class);
    }


    @Test
    void addUserTest() {
        userRepository.addUser(user);
        assertEquals(3, userRepository.getUsers().size()); // в текущей конфигурации автоматически создается
                                                                    // 2 пользователя в конструкторе по умолчанию
                                                                    // (admin и user)
    }

    @Test
    void getUserTest() {
        when(user.getId()).thenReturn(3L);
        assertEquals(user, userRepository.getUser(3L));
        verify(user, times(3)).getId();
        assertNull(userRepository.getUser(4L));
    }

    @ParameterizedTest
    @CsvSource({
            "1, User1, user1, user1, BANNED",
            "2, User2, user2, user2, ADMIN"
    })
    void updateUserTest(Long id, String name, String login, String password, Role role) {
        when(user.getId()).thenReturn(id);
        userRepository.updateUser(id, name, login, password, role);
        assertEquals(name, user.getUsername());
        assertEquals(login, user.getLogin());
        assertEquals(password, user.getPassword());
        assertEquals(role, user.getRole());
    }

    @ParameterizedTest
    @CsvSource({
            "userTest, userTest",
            "userTest1, userTest1"
    })
    void getUserTest(String login, String password) {
        when(user.getLogin()).thenReturn(login);
        when(user.getPassword()).thenReturn(password);
        assertEquals(user, userRepository.getUser(login, password));
    }
}
