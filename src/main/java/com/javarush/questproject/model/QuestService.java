package com.javarush.questproject.model;

import com.javarush.questproject.entity.Question;
import com.javarush.questproject.entity.Role;
import com.javarush.questproject.entity.User;
import com.javarush.questproject.repository.QRepository;
import com.javarush.questproject.repository.QuestionRepository;
import com.javarush.questproject.repository.URepository;
import com.javarush.questproject.repository.UserRepository;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Александр, не хотелось бы спамить Вам в "личку" всякой ерундой, поэтому: не судите строго. Вся игрушка была написана
// до всех консультаций, даже ДО лекции по Concurrent - идея была в том, чтобы написать что-то полностью свое, вплоть до общей архитектуры, без помощи и "подглядок", дабы оценить свои знания, и уже потом
// "допилить" при необходимости. Но времени не хватает - работа, дом, заботы: на момент написания этого послания Вы уже на 7-й лекции 4-го модуля,
// а я еще консультации посмотрел не все и не написал тесты, поэтому код получился несколько примитивным, насколько я могу судить (на фоне Вашего) и
// весь изначально запланированный функционал не реализован. Буду исправляться и нагонять, готов считать справедливой любую Вашу оценку.

@NoArgsConstructor
public class QuestService implements QRepository, URepository {
    private final URepository userRepository = new UserRepository();
    private final QRepository questionRepository = new QuestionRepository();
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

    @Override
    public List<Question> getQuestion(Integer key) {
        return questionRepository.getQuestion(key);
    }

    public void addPage() {
        questionRepository.addPage();
    }

    @Override
    public void deletePage(Integer index) {
        questionRepository.deletePage(index);
    }

    public void addQuestion(Integer key) {
        questionRepository.getQuestions().get(key).add(new Question());
    }
    public void deleteQuestion(String key, String index) {
        questionRepository.deleteQuestion(key, index);
    }
    public void savePage(String key, String title, String [] questions, String [] status) {
        questionRepository.savePage(key, title, questions, status);
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
