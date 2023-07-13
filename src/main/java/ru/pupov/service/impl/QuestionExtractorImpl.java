package ru.pupov.service.impl;

import org.springframework.core.io.ClassPathResource;
import ru.pupov.domain.Question;
import ru.pupov.service.QuestionExtractor;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionExtractorImpl implements QuestionExtractor {

    public static final String CSV_DELIMITER = ";";

    @Override
    public List<Question> extract(String filePath) {
        var classloader = Thread.currentThread().getContextClassLoader();
        var classPathResource = new ClassPathResource(filePath, classloader);
        List<Question> questions;
        try {
            var path = classPathResource.getFile().toPath();
            questions = Files.lines(path)
                    .map(this::parseToQuestion)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return questions;
    }

    private Question parseToQuestion(String line) {
        var strings = line.split(CSV_DELIMITER);
        var question = strings[0];
        var answerList = Arrays.stream(strings).collect(Collectors.toList());
        answerList.remove(0);
        Collections.shuffle(answerList);
        int correctAnswerNum = -1;
        for (int i = 0; i < answerList.size(); i++) {
            if (answerList.get(i).equals(strings[1])) {
                correctAnswerNum = i;
                break;
            }
        }
        return new Question(question, answerList, correctAnswerNum);
    }
}
