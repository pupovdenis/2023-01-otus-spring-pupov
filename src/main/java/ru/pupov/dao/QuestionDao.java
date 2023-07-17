package ru.pupov.dao;

import org.springframework.core.io.ClassPathResource;
import ru.pupov.domain.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class QuestionDao {

    private static final String CSV_DELIMITER = ";";
    private static final int MIN_CSV_STRINGS = 2;
    private static final int QUESTION_INDEX = 0;
    private static final int CORRECT_ANSWER_INDEX = 1;

    private final String csvPath;

    public QuestionDao(String csvPath) {
        this.csvPath = csvPath;
    }

    public List<Question> getAll() {
        var classloader = Thread.currentThread().getContextClassLoader();
        var classPathResource = new ClassPathResource(csvPath, classloader);
        List<Question> questions;
        try (var reader = new BufferedReader(new InputStreamReader(classPathResource.getInputStream()))) {
            questions = getQuestions(reader.lines());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return questions == null ? Collections.emptyList() : questions;
    }

    private List<Question> getQuestions(Stream<String> lines) throws IOException {
        return lines
                .map(str -> str.split(CSV_DELIMITER))
                .filter(strings -> strings.length >= MIN_CSV_STRINGS)
                .map(this::getQuestion)
                .filter(Objects::nonNull)
                .collect(toList());
    }

    private Question getQuestion(String[] strings) {
        try {
            var question = strings[QUESTION_INDEX];
            var correctAnswer = strings[CORRECT_ANSWER_INDEX];
            var answers = Arrays.stream(strings).collect(toList());
            answers.remove(QUESTION_INDEX);
            return new Question(question, answers, correctAnswer);
        } catch (Exception e) {
            return null;
        }
    }
}
