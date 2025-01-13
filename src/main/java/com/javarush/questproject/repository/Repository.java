package com.javarush.questproject.repository;


import com.javarush.questproject.entity.Role;
import com.javarush.questproject.entity.User;

import java.util.ArrayList;

public interface Repository {
    ArrayList<User> getUsers();

    void addUser(User user);

    User getUser(Long userId);

    void updateUser(Long userId, String name, String login, String password, Role role);

    User getUser(String login, String password);

}
