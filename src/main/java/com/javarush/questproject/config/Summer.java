package com.javarush.questproject.config;

import jakarta.servlet.http.HttpSession;

public class Summer {

    public static void removeAttributesInSession(HttpSession session, String... attributes) {
        for (String attribute : attributes) {
            session.removeAttribute(attribute);
        }
    }
    // Хотел написать аналог Winter - самостоятельно изучил Reflection API,
    // разобрался что откуда берется и т.д., но решил все таки оставить на Singelton'нах и Servlet'ах.
    // С учетом текущего функционала (без паттерна Command) мне показалось, что писать аналогичный
    // метод чисто для создания единственного объекта QuestService не рационально.
}
