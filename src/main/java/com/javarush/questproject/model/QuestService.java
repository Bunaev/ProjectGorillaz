package com.javarush.questproject.model;

import com.javarush.questproject.entity.Question;
import com.javarush.questproject.entity.Role;
import com.javarush.questproject.entity.User;
import com.javarush.questproject.repository.QuestionRepository;
import com.javarush.questproject.repository.Repository;
import com.javarush.questproject.repository.UserRepository;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
    public User getUser(String login, String password) {
        return userRepository.getUser(login, password);
    }
    @Override

    public void updateUser(Long userId, String name, String login, String password, Role role) {
        userRepository.updateUser(userId, name, login, password, role);
    }

    public Map<Integer, ArrayList<Question>> getQuestions() {
        return questionRepository.getQuestions();
    }
    public void deletePage(String key) {
        Integer index = Integer.parseInt(key);
        questionRepository.deletePage(index);
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
    public String getTitle(Object key) {
        Integer index = Integer.parseInt(String.valueOf(key));
        return questionRepository.getQuestion(index).get(0).getContent();
    }
    public List<Question> getContentInPage(Object key) {
        Integer index = Integer.parseInt(String.valueOf(key));
        Integer sizeQuestionContent = questionRepository.getQuestion(index).size();
        return questionRepository.getQuestion(index).subList(1, sizeQuestionContent);
    }
}
