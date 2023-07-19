package ru.pupov.converter;

import ru.pupov.domain.Question;

public class Converter {

    private Converter() {
    }

    public static String toQuizString(Question question) {
        var answers = question.getAnswers();
        var stringBuilder = new StringBuilder();
        stringBuilder
                .append("\n")
                .append(question)
                .append("\n");
        for (int i = 0; i < answers.size(); i++) {
            stringBuilder
                    .append(i + 1)
                    .append(") ")
                    .append(answers.get(i).getText())
                    .append("\n");
        }
        return stringBuilder.toString();
    }
}
