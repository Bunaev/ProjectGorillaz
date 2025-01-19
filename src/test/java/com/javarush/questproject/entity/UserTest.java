package com.javarush.questproject.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class UserTest {
    Game game;
    User user;

    @BeforeEach
    void setUp() {
        game = spy(Game.class);
        user = spy(User.class);
        user.getGames().add(game);
    }

    @Test
    void getIdTest() {
        when(user.getId()).thenReturn(1L);
        assertEquals(1L, user.getId());
    }

    @Test
    void getUsernameTest() {
        when(user.getUsername()).thenReturn("userName");
        assertEquals("userName", user.getUsername());
    }

    @Test
    void getLoginTest() {
        when(user.getLogin()).thenReturn("userLogin");
        assertEquals("userLogin", user.getLogin());
    }

    @Test
    void getPasswordTest() {
        when(user.getPassword()).thenReturn("userPassword");
        assertEquals("userPassword", user.getPassword());
    }

    @Test
    void getRoleTest() {
        when(user.getRole()).thenReturn(Role.USER);
        assertEquals(Role.USER, user.getRole());
    }

    @Test
    void getProgressTest() {
        when(game.getProgress()).thenReturn(2);
        when(game.getEndGame()).thenReturn(false);
        assertEquals(2, user.getProgress());
        when(game.getProgress()).thenReturn(0);
        when(game.getEndGame()).thenReturn(true);
        assertNull(user.getProgress());
        verify(game, times(3)).getProgress();
        verify(game, times(1)).getEndGame();
    }

    @Test
    void createGameTest() {
        assertEquals(1, user.getGames().size());
        user.createGame();
        assertEquals(2, user.getGames().size());
    }

    @Test
    void getCountCompleteGamesTest() {
        when(game.getEndGame()).thenReturn(true);
        assertEquals(1, user.getCountCompleteGames());
        when(game.getEndGame()).thenReturn(false);
        assertNull(user.getCountCompleteGames());
    }

    @Test
    void endGameTest() {
        when(game.getEndGame()).thenReturn(false);
        user.endGame(true);
        verify(game).setStatus(true);
        verify(game).setEndGame(true);
        verify(game).setDateFinish(new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date()));
    }
}
