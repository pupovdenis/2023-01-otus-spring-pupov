package ru.pupov.service.impl;

import org.springframework.beans.factory.annotation.Value;
import ru.pupov.domain.Question;
import ru.pupov.domain.Student;
import ru.pupov.service.QuizPrinter;

public class QuizPrinterImpl implements QuizPrinter {

    @Value("${passing-number-of-correct-answers}")
    private int PASSING_NUMBER_OF_CORRECT_ANSWERS;

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
    public void printEnd(Student student, int rightAnswersCounter) {
        System.out.println("---------------------------------------------------");
        System.out.printf("%s, you got %d correct answers. ", student, rightAnswersCounter);
        System.out.printf("You %spassed the test\n\n", rightAnswersCounter < PASSING_NUMBER_OF_CORRECT_ANSWERS ? "did not " : "");
    }
}
