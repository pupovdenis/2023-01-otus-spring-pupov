package ru.pupov.service;

import ru.pupov.domain.Question;

import java.util.List;

public interface QuestionExtractor {
    List<Question> extract(String filePath);
}
