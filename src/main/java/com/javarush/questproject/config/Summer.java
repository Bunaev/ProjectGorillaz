package com.javarush.questproject.config;

import jakarta.servlet.http.HttpSession;

public class Summer {

    public static void removeAttributesInSession(HttpSession session, String... attributes) {
        for (String attribute : attributes) {
            session.removeAttribute(attribute);
        }
    }
}
