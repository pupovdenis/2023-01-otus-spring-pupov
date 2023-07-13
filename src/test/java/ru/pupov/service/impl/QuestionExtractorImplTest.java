package ru.pupov.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("QuestionExtractorImpl класс")
class QuestionExtractorImplTest {

    @DisplayName("Корректно извлекает данные")
    @Test
    void shouldExtractCorrectQuestions() {
        var questionExtractor = new QuestionExtractorImpl();
        var list = questionExtractor.extract("data.csv");
        assertThat(list)
                .filteredOn(question ->
                        !question.getQuestion().isBlank()
                        && !question.getAnswers().isEmpty()
                        && question.getCorrectAnswerNum() >= 0)
                .hasSize(5);
    }
}