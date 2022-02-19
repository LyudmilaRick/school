package ru.hogwarts.rickln.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.rickln.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final Map<Long, Student> studentStore = new HashMap<>();
    private long lastId = 0;

    @Override
    public Student addStudent(Student student) {
        student.setId(++lastId);
        studentStore.put(lastId, student);
        return student;
    }

    @Override
    public Student getStudent(Long id) {
        return studentStore.get(id);
    }

    @Override
    public Collection<Student> getAllStudents() {
        return studentStore.values();
    }

    @Override
    public Collection<Student> getStudentUseAge(int age) {
        return studentStore.values().stream()
                .filter(student -> student.getAge() == age)
                .collect(Collectors.toList());
    }

    @Override
    public Student setStudent(Student student) {
        if (studentStore.containsKey(student.getId())) {
            studentStore.put(student.getId(), student);
            return student;
        }
        return null;
    }

    @Override
    public Student removeStudent(Long id) {
        return studentStore.remove(id);
    }
}
