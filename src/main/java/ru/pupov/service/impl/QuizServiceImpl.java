package ru.pupov.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.pupov.converter.QuestionConverter;
import ru.pupov.dao.QuestionDao;
import ru.pupov.dao.StudentDao;
import ru.pupov.domain.Question;
import ru.pupov.domain.Student;
import ru.pupov.service.IOService;
import ru.pupov.service.QuizService;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

    private static final String START_MESSAGE = "\nLet's start a quiz!";
    private static final String ASK_FIRST_NAME_MESSAGE = "\nEnter your first name: ";
    private static final String ASK_LAST_NAME_MESSAGE = "Enter last name: ";
    private static final String EMPTY_QUESTIONS_MESSAGE = "Failed to get any question";
    private static final String ENTER_YOUR_ANSWER_MESSAGE = "Enter your answer: ";
    private static final String SEPARATOR_LINE = "---------------------------------------------------";
    private static final String END_MESSAGE_FORMAT = "%s, you got %d correct answers. ";
    private static final String RESULT_SUCCESS_MESSAGE = "You passed the test\n";
    private static final String RESULT_FAIL_MESSAGE = "You did not passed the test\n";
    private static final String INCORRECT_INPUT = "Incorrect input. Please, enter number of answer";
    private static final int ANSWER_MIN_VALUE = 1;


    private final IOService ioService;
    private final QuestionDao questionDao;
    private final StudentDao studentDao;
    private final QuestionConverter questionConverter;

    private final int passingNumberOfCorrectAnswers;

    public QuizServiceImpl(IOService ioService,
                           QuestionDao questionDao,
                           StudentDao studentDao,
                           QuestionConverter questionConverter,
                           @Value("${passing-number-of-correct-answers:3}") int passingNumberOfCorrectAnswers) {
        this.ioService = ioService;
        this.questionDao = questionDao;
        this.studentDao = studentDao;
        this.questionConverter = questionConverter;
        this.passingNumberOfCorrectAnswers = passingNumberOfCorrectAnswers;
    }

    public void run() {
        var student = getStudent();
        var questionList = questionDao.getAll();
        if (questionList.isEmpty()) {
            ioService.outputString(EMPTY_QUESTIONS_MESSAGE);
            return;
        }
        ioService.outputString(START_MESSAGE);
        int rightAnswersCounter = doQuizAndGetResult(questionList);
        outputFinish(student, rightAnswersCounter);
    }

    private void outputFinish(Student student, int rightAnswersCounter) {
        ioService.outputString(SEPARATOR_LINE);
        ioService.outputString(String.format(END_MESSAGE_FORMAT, student, rightAnswersCounter));
        if (rightAnswersCounter >= passingNumberOfCorrectAnswers) {
            ioService.outputString(RESULT_SUCCESS_MESSAGE);
        }
        else {
            ioService.outputString(RESULT_FAIL_MESSAGE);
        }
    }

    private Student getStudent() {
        var firstName = ioService.readStringWithPrompt(ASK_FIRST_NAME_MESSAGE, true);
        var lastName = ioService.readStringWithPrompt(ASK_LAST_NAME_MESSAGE, true);
        return studentDao.getStudent(firstName, lastName);
    }

    private int doQuizAndGetResult(List<Question> questionList) {
        int rightAnswersCounter = 0;
        for (var question : questionList) {
            ioService.outputString(questionConverter.toQuizString(question), true);
            while (true) {
                try {
                    var answerInt = ioService.readIntWithPrompt(ENTER_YOUR_ANSWER_MESSAGE);
                    if (answerInt < ANSWER_MIN_VALUE || answerInt > questionList.size()) {
                        throw new NumberFormatException();
                    }
                    if (isCorrectAnswerInput(question, answerInt)) {
                        rightAnswersCounter++;
                    }
                    break;
                } catch (NumberFormatException nfe) {
                    ioService.outputString(INCORRECT_INPUT);
                }
            }
        }
        return rightAnswersCounter;
    }

    private boolean isCorrectAnswerInput(Question question, int answer) {
        int answerIndex = answer - ANSWER_MIN_VALUE;
        return answerIndex >= 0
                && answerIndex < question.getAnswers().size()
                && question.getAnswers().get(answerIndex).isCorrectAnswer();
    }
}
