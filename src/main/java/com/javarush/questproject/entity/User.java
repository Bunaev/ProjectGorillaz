package com.javarush.questproject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

@NoArgsConstructor
@Getter
@Setter
public class User {
    private static final AtomicLong counter = new AtomicLong(0);
    private Long id;
    private String username;
    private String login;
    private String password;
    private Role role;
    private final ArrayList<Game> games = new ArrayList<>();

    public User(String name, String login, String password) {
        this.id = counter.incrementAndGet();
        this.username = name;
        this.login = login;
        this.password = password;
    }

    public Integer getProgress() {
        if (!games.isEmpty() && games.get(games.size() - 1).getProgress() > 0 && games.get(games.size() - 1).getEndGame().equals(false)) {
            return games.get(games.size() - 1).getProgress();
        }
        return null;
    }

    public void createGame() {
        games.add(new Game(false, new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date()), null, false));
    }

    public Integer getCountCompleteGames() {
        int count = 0;
        for (Game game : games) {
            if (game.getEndGame().equals(true)) {
                count++;
            }
        }
        if (count > 0) {
            return count;
        } else {
            return null;
        }
    }
    public void endGame(Boolean status) {
        if (!games.isEmpty() && games.get(games.size() - 1).getEndGame().equals(false)) {
            games.get(games.size() - 1).setStatus(status);
            games.get(games.size() - 1).setEndGame(true);
            games.get(games.size() - 1).setDateFinish(new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date()));
        }
    }
}
