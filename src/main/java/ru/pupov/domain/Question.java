package ru.pupov.domain;

import java.util.List;

public class Question {

    private final String question;
    private final List<String> answers;
    private final String correctAnswer;

    public Question(String question, List<String> answers, String correctAnswer) {
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String toQuizString() {
        var stringBuilder = new StringBuilder();
        stringBuilder
                .append("\n")
                .append(question)
                .append("\n");
        for (int i = 0; i < answers.size(); i++) {
            stringBuilder
                    .append(i + 1)
                    .append(") ")
                    .append(answers.get(i))
                    .append("\n");
        }
        return stringBuilder.toString();
    }
}
