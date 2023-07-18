package ru.pupov.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.pupov.dao.impl.QuestionDaoImpl;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Класс QuestionDao")
//@ExtendWith(MockitoExtension.class)
class QuestionDaoImplTest {

//    @Mock
//    QuizServiceImpl quizServiceImpl;

    public static final String CSV_PATH = "data.csv";

    @DisplayName("Корректно извлекает данные")
    @Test
    void shouldExtractCorrectQuestions() {
        var questionDao = new QuestionDaoImpl(CSV_PATH);
        var questions = questionDao.getAll();
        assertThat(questions)
                .filteredOn(question ->
                        !question.getQuestion().isBlank()
                                && !question.getAnswers().isEmpty()
                                && !question.getCorrectAnswer().isBlank())
                .hasSize(5);
    }
}