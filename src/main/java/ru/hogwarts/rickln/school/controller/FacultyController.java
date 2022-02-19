package ru.hogwarts.rickln.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.rickln.school.model.Faculty;
import ru.hogwarts.rickln.school.service.FacultyService;

import java.util.Collection;

/**
 * 3. В каждом контроллере реализовать эндпоинты для создания, получения, изменения и удаления сущностей,
 * используя все правила формирования REST-запросов: GET-методы для получения данных, POST— для создания…
 */
@RestController
@RequestMapping("faculty")
public class FacultyController {
    public final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    /**
     * найти по номеру
     * GET http://localhost:8080/faculty/1
     */
    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable long id) {
        Faculty faculty = facultyService.getFaculty(id);
        if (faculty == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(faculty);
    }

    /**
     * Добавить фильтрацию факультетов по цвету.
     * GET http://localhost:8080/faculty/?color=green
     */

    @GetMapping
    public ResponseEntity<Collection<Faculty>> getFacultyUseColor(@RequestParam("color") String color) {
        Collection<Faculty> facultyUseColor = facultyService.getFacultyUseColor(color);
        if (facultyUseColor.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(facultyUseColor);
    }

    /**
     * показать всех
     * GET http://localhost:8080//all
     */
    @GetMapping("/all")
    public ResponseEntity<Collection<Faculty>> getAllFaculties() {
        return ResponseEntity.ok(facultyService.getAllFaculties());
    }

    /**
     * POST http://localhost:8080/faculty
     */
    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.addFaculty(faculty);
    }

    /**
     * PUT http://localhost:8080/faculty
     */
    @PutMapping
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty) {
        Faculty facultyForUpdate = facultyService.setFaculty(faculty);
        if (facultyForUpdate == null) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(facultyForUpdate);
    }

    /**
     * DELETE  http://localhost:8080/faculty/2
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable long id) {
        Faculty facultyForDelete = facultyService.removeFaculty(id);
        if (facultyForDelete == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(facultyForDelete);
    }
}
