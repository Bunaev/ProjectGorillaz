package com.javarush.questproject.repository;

import com.javarush.questproject.entity.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface QRepository {
    List<Question> getQuestion (Integer key);
    void addPage();
    void deletePage(Integer index);
    Map<Integer, ArrayList<Question>> getQuestions();
    String getIntro();
    void deleteQuestion(String key, String index);
    void savePage(String key, String title, String [] newQuestions, String [] status);

    }
