package ru.pupov.dao.impl;

import ru.pupov.dao.StudentDao;
import ru.pupov.domain.Student;

public class StudentDaoImpl implements StudentDao {

    public Student getStudent(String firstName, String lastName) {
        return new Student(firstName, lastName);
    }
}
