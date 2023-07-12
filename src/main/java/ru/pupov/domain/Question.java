package ru.pupov.domain;

import java.util.List;

public class Question {

    private final String question;
    private final List<String> answers;
    private final int correctAnswerNum;

    public Question(String question, List<String> answers, int correctAnswerNum) {
        this.question = question;
        this.answers = answers;
        this.correctAnswerNum = correctAnswerNum;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public int getCorrectAnswerNum() {
        return correctAnswerNum;
    }
}
