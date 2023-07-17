package ru.pupov.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.pupov.domain.Question;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Класс QuestionDao")
class QuestionDaoTest {

    public static final String CSV_PATH = "data.csv";

    @DisplayName("Корректно извлекает данные")
    @Test
    void shouldExtractCorrectQuestions() {
        var questionDao = new QuestionDao(CSV_PATH);
        var questions = questionDao.getAll();
        assertEquals(questions.size(), 5);
        questions.forEach(question -> {
            assertFalse(question.getQuestion().isBlank());
            assertFalse(question.getAnswers().isEmpty());
            assertFalse(question.getCorrectAnswer().isBlank());
        });
    }
}