package com.javarush.questproject.entity;

import lombok.Getter;

@Getter
public class Question {
    private final String content;
    private final Boolean statusQuestion;

    public Question(String content, Boolean statusQuestion) {
        this.content = content;
        this.statusQuestion = statusQuestion;
    }
    public Question () {
        this.content = "";
        this.statusQuestion = true;
    }
}
