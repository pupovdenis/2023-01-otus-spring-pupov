package ru.pupov.service;

import ru.pupov.domain.Question;

public interface QuizPrinter {
    void printQuestion(Question question);

    void printStart();

    void printEnd(int rightAnswersCounter);
}
