package ru.pupov.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DisplayName("QuestionExtractorImpl класс")
class QuestionExtractorImplTest {

    @DisplayName("Корректно извлекает данные")
    @Test
    void shouldExtractCorrectQuestions() {
        var questionExtractor = new QuestionExtractorImpl();
        var list = questionExtractor.extract("data.csv");
        assertEquals(list.size(), 5);
        list.forEach(question -> {
            assertFalse(question.getQuestion().isBlank());
            assertFalse(question.getAnswers().isEmpty());
            assertTrue(question.getCorrectAnswerNum() >= 0);
        });
    }
}