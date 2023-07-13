package ru.pupov.service;

import ru.pupov.domain.Question;
import ru.pupov.domain.Student;

public interface QuizPrinter {
    void printQuestion(Question question);

    void printStart();

    void printEnd(Student student, int rightAnswersCounter);
}
