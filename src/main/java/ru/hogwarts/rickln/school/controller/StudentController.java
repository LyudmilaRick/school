package ru.hogwarts.rickln.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.rickln.school.model.Student;
import ru.hogwarts.rickln.school.service.StudentService;

import java.util.Collection;

/**
 * 3. В каждом контроллере реализовать эндпоинты для создания, получения, изменения и удаления сущностей,
 * используя все правила формирования REST-запросов: GET-методы для получения данных, POST— для создания…
 */
@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * найти по номеру
     * GET http://localhost:8080/student/1
     */
    @GetMapping("{id}")
    public ResponseEntity<Student> getStudent(@PathVariable long id) {
        Student student = studentService.getStudent(id);
        if (student == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(student);
    }

    /**
     * Добавить фильтрацию студентов по возрасту.
     * GET 'http://localhost:8080/student?age=1'
     */
    @GetMapping
    public ResponseEntity<Collection<Student>> getStudentUseAge(@RequestParam("age") int age) {
        Collection<Student> studentUseAge = studentService.getStudentUseAge(age);
        if (studentUseAge.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(studentUseAge);
    }

    /**
     * показать всех
     * GET http://localhost:8080/student/all
     */
    @GetMapping("/all")
    public ResponseEntity<Collection<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    /**
     * POST http://localhost:8080/student
     */
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    /**
     * PUT http://localhost:8080/student
     */
    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student studentForUpdate = studentService.setStudent(student);
        if (studentForUpdate == null) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(studentForUpdate);
    }

    /**
     * DELETE  http://localhost:8080/student/2
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable long id) {
        Student studentForDelete = studentService.removeStudent(id);
        if (studentForDelete == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(studentForDelete);
    }

}
