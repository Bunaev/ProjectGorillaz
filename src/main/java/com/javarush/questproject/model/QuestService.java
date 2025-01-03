package com.javarush.questproject.model;

import com.javarush.questproject.entity.Question;
import com.javarush.questproject.entity.Role;
import com.javarush.questproject.entity.User;
import com.javarush.questproject.repository.QuestionRepository;
import com.javarush.questproject.repository.Repository;
import com.javarush.questproject.repository.UserRepository;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Map;

@NoArgsConstructor
public class QuestService implements Repository {
    private final UserRepository userRepository = new UserRepository();
    private final QuestionRepository questionRepository = new QuestionRepository();
    private static QuestService questService;

    public static QuestService getInstance() {
        if (questService == null) {
            questService = new QuestService();
        }
        return questService;
    }

    public Boolean authorize(String login, String password) {
        for (User user : userRepository.getUsers()) {
            if (user.getLogin().equalsIgnoreCase(login) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
@Override
    public ArrayList<User> getUsers() {
        return userRepository.getUsers();
    }
@Override
    public void addUser(User user) {
        userRepository.addUser(user);
    }
    @Override

    public User getUser(Long userId) {
        return userRepository.getUser(userId);
    }
    @Override

    public void updateUser(Long userId, String name, String login, String password, Role role) {
        userRepository.updateUser(userId, name, login, password, role);
    }
    @Override

    public Long getUserId(String login, String password) {
        return userRepository.getUserId(login, password);
    }
    public Map<Integer, ArrayList<Question>> getQuestions() {
        return questionRepository.getQuestions();
    }
    public void deletePage(Integer key) {
        questionRepository.deletePage(key);
    }
    public void addPage() {
        questionRepository.addPage();
    }
    public void addQuestion(Integer key) {
        questionRepository.getQuestions().get(key).add(new Question());
    }
    public void deleteQuestion(String key, String index) {
        questionRepository.getQuestions().get(Integer.parseInt(key)).remove(Integer.parseInt(index));
    }
    public void savePage(String key, String title, String [] questions, String [] status) {
        ArrayList<Question> page = new ArrayList<>();
        page.add(new Question(title, true));
        if (questions != null) {
            for (int i = 0; i < questions.length; i++) {
                page.add(new Question(questions[i], Boolean.parseBoolean(status[i])));
            }
        }
        questionRepository.getQuestions().put(Integer.parseInt(key), page);
    }
    public String getIntro() {
        return questionRepository.getIntro();
    }
}
