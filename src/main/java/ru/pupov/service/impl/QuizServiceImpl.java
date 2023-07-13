package ru.pupov.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.pupov.domain.Student;
import ru.pupov.service.QuestionExtractor;
import ru.pupov.service.QuizPrinter;
import ru.pupov.service.QuizService;

import java.util.Scanner;

@Service
public class QuizServiceImpl implements QuizService {

    @Value("${csv-path}")
    private String CSV_PATH;

    private final QuizPrinter quizPrinter;
    private final QuestionExtractor questionExtractor;

    public QuizServiceImpl(QuizPrinter quizPrinter, QuestionExtractor questionExtractor) {
        this.quizPrinter = quizPrinter;
        this.questionExtractor = questionExtractor;
    }

    @Override
    public void run() {
        var questions = questionExtractor.extract(CSV_PATH);
        int rightAnswersCounter = 0;
        var scanner = new Scanner(System.in);
        quizPrinter.printStart();
        var student = getStudent(scanner);
        for (var q : questions) {
            quizPrinter.printQuestion(q);
            var answer = scanner.nextInt();
            if (answer - 1 == q.getCorrectAnswerNum()) {
                rightAnswersCounter++;
            }
        }
        quizPrinter.printEnd(student, rightAnswersCounter);
    }

    private Student getStudent(Scanner scanner) {
        System.out.print("\nEnter first name: ");
        var firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        var lastName = scanner.nextLine();
        return new Student(firstName, lastName);
    }
}
