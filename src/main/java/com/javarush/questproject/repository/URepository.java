package com.javarush.questproject.repository;

import com.javarush.questproject.entity.Role;
import com.javarush.questproject.entity.User;

import java.util.ArrayList;

public interface URepository {
    void addUser(User user);
    User getUser(Long id);
    void updateUser(Long id, String name, String login, String password, Role role);
    User getUser(String login, String password);
    ArrayList<User> getUsers();
}
