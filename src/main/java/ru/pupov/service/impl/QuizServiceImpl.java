package ru.pupov.service.impl;

import ru.pupov.converter.Converter;
import ru.pupov.dao.QuestionDao;
import ru.pupov.domain.Question;
import ru.pupov.service.IOService;
import ru.pupov.service.QuizService;

import java.util.List;

public class QuizServiceImpl implements QuizService {

    private static final String START_MESSAGE = "\n\nLet's start a quiz!";
    private static final String END_MESSAGE = "You got correct answers: ";
    private static final String SEPARATOR_LINE = "---------------------------------------------------";
    private static final String EMPTY_QUESTIONS_MESSAGE = "Failed to get any question";
    private static final String ENTER_YOUR_ANSWER_MESSAGE = "Enter your answer: ";

    private final IOService ioService;
    private final QuestionDao questionDao;

    public QuizServiceImpl(IOService ioService, QuestionDao questionDao) {
        this.ioService = ioService;
        this.questionDao = questionDao;
    }

    public void run() {
        var questionList = questionDao.getAll();
        if (questionList.isEmpty()) {
            ioService.outputString(EMPTY_QUESTIONS_MESSAGE);
            return;
        }
        ioService.outputString(START_MESSAGE);
        int rightAnswersCounter = doQuizAndGetResult(questionList);
        ioService.outputString(SEPARATOR_LINE);
        ioService.outputString(END_MESSAGE + rightAnswersCounter);
    }

    private int doQuizAndGetResult(List<Question> questionList) {
        int rightAnswersCounter = 0;
        for (var question : questionList) {
            ioService.outputString(Converter.toQuizString(question), true);
            var answerInt = ioService.readIntWithPrompt(ENTER_YOUR_ANSWER_MESSAGE);
            if (isCorrectAnswerInput(question, answerInt)) {
                rightAnswersCounter++;
            }
        }
        return rightAnswersCounter;
    }

    private boolean isCorrectAnswerInput(Question question, int answer) {
        int answerIndex = answer - 1;
        return answerIndex >= 0
                && answerIndex < question.getAnswers().size()
                && question.getAnswers().get(answerIndex).isCorrectAnswer();
    }
}
