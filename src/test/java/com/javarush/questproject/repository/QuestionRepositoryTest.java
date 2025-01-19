package com.javarush.questproject.repository;

import com.javarush.questproject.entity.Question;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class QuestionRepositoryTest {
    private static QuestionRepository questionRepository;
    public static Question question;
    public static List<Question> questions;

    @BeforeAll
    static void setUp() {
        questionRepository = spy(QuestionRepository.class);
        question = spy(Question.class);
        questions = questionRepository.getQuestion(1);
    }
    @Test
    void addPageTest() {
        questionRepository.addPage();
        assertEquals(7, questionRepository.getQuestions().size());
    }
    @Test
    void getQuestionTest() {
        assertEquals(questions, questionRepository.getQuestion(1));
    }


    @Test
    void deletePageTest() {
        questionRepository.deletePage(7);
        assertEquals(6, questionRepository.getQuestions().size());
    }


    @Test
    void getIntroTest() {
        when(questionRepository.getIntro()).thenReturn("hello");
        assertEquals("hello", questionRepository.getIntro());
    }

    @Test
    void deleteQuestionTest() {
        questionRepository.deleteQuestion("2", "2");
        assertEquals(2, questionRepository.getQuestions().get(2).size());
    }

    @Test
    void savePageTest() {
        questionRepository.savePage("3", "title", new String[]{"question"}, new String[]{"true"});
        assertEquals(2, questionRepository.getQuestions().get(3).size());
        assertEquals("title", questionRepository.getQuestions().get(3).get(0).getContent());
        assertTrue(questionRepository.getQuestions().get(3).get(0).getStatusQuestion());
    }
}
