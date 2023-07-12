package ru.pupov.service.impl;

import ru.pupov.service.QuestionExtractor;
import ru.pupov.service.QuizPrinter;
import ru.pupov.service.QuizService;

import java.util.Scanner;

public class QuizServiceImpl implements QuizService {

    private final String CSV_PATH;

    private final QuizPrinter quizPrinter;
    private final QuestionExtractor questionExtractor;

    public QuizServiceImpl(QuizPrinter quizPrinter, QuestionExtractor questionExtractor, String csvPath) {
        this.quizPrinter = quizPrinter;
        this.questionExtractor = questionExtractor;
        CSV_PATH = csvPath;
    }

    @Override
    public void run() {
        var questions = questionExtractor.extract(CSV_PATH);
        int rightAnswersCounter = -1;
        var scanner = new Scanner(System.in);
        quizPrinter.printStart();
        for (var q : questions) {
            quizPrinter.printQuestion(q);
            var answer = scanner.nextInt();
            if (answer - 1 == q.getCorrectAnswerNum()) {
                rightAnswersCounter++;
            }
        }
        quizPrinter.printEnd(rightAnswersCounter);
    }
}
