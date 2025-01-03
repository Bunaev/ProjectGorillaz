package com.javarush.questproject.repository;

import com.javarush.questproject.entity.Role;
import com.javarush.questproject.entity.User;
import lombok.Getter;

import java.util.ArrayList;
@Getter
public class UserRepository implements Repository {

    private static final ArrayList<User> users = new ArrayList<>();

    public UserRepository() {
        users.add(new User("Администратор", "admin", "admin"));
        users.get(0).setRole(Role.ADMIN);
        users.add(new User("Костик", "user", "user"));
        users.get(1).setRole(Role.USER);
    }
    @Override
    public void addUser(User user) {
        users.add(user);
    }
    @Override
    public User getUser(Long id) {
        for (User user : users) {
            if (user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }
    @Override
    public void updateUser(Long id, String name, String login, String password, Role role) {
        for (User user : users) {
            if (user.getId().equals(id)){
                if (password != null) {
                    user.setUsername(name);
                    user.setLogin(login);
                    user.setPassword(password);
                    user.setRole(role);
                } else {
                    user.setUsername(name);
                    user.setLogin(login);
                    user.setRole(role);
                }
            }
        }
    }
    @Override
    public Long getUserId(String login, String password) {
        for (User user : users) {
            if (user.getLogin().equalsIgnoreCase(login) && user.getPassword().equals(password)) {
                return user.getId();
            }
        }
        return null;
    }
    @Override
    public ArrayList<User> getUsers() {
        return users;
    }
}
