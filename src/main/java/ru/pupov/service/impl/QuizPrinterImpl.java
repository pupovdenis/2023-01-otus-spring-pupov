package ru.pupov.service.impl;

import ru.pupov.domain.Question;
import ru.pupov.service.QuizPrinter;

public class QuizPrinterImpl implements QuizPrinter {

    @Override
    public void printQuestion(Question question) {
        System.out.println("\n" + question.getQuestion());
        for (int i = 0; i < question.getAnswers().size(); i++) {
            System.out.print(i + 1 + ") ");
            System.out.println(question.getAnswers().get(i));
        }
        System.out.print("Enter your answer: ");
    }

    @Override
    public void printStart() {
        System.out.println("\n\n" + "Let's start a quiz!");
    }

    @Override
    public void printEnd(int rightAnswersCounter) {
        System.out.println("---------------------------------------------------");
        System.out.println("You got " + rightAnswersCounter + " correct answers");
    }
}
