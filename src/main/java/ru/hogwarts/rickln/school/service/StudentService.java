package ru.hogwarts.rickln.school.service;

import ru.hogwarts.rickln.school.model.Student;

import java.util.Collection;

public interface StudentService {
    Student addStudent(Student student);

    Student getStudent(Long id);

    Collection<Student> getAllStudents();

    Collection<Student> getStudentUseAge(int age);

    Student setStudent(Student student);

    Student removeStudent(Long id);
}
