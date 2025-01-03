package com.javarush.questproject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;
@Getter
@Setter
@NoArgsConstructor
public class Game {
    private Boolean status;
    private String dateStart;
    private String dateFinish;
    private Boolean endGame;
    private final AtomicInteger progress = new AtomicInteger(0);

    public Game(Boolean status, String dateStart, String dateFinish, Boolean endGame) {
        this.status = status;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
        this.endGame = endGame;
    }

    public Integer getProgress() {
        return progress.get();
    }
    public void setProgress() {
        this.progress.incrementAndGet();
    }

    @Override
    public String toString() {
        return "Game{" +
                "status=" + status +
                ", dateStart='" + dateStart + '\'' +
                ", dateFinish='" + dateFinish + '\'' +
                ", endGame=" + endGame +
                ", progress=" + progress +
                '}';
    }
}


