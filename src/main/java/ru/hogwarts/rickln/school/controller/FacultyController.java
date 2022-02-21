package ru.hogwarts.rickln.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
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
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    /**
     * найти по номеру
     * GET http://localhost:8080/faculty/1
     */
    @GetMapping("{id}")
    public Faculty getFaculty(@PathVariable long id) {
        Faculty faculty = facultyService.getFaculty(id);
        if (faculty == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return faculty;
    }

    /**
     * Добавить фильтрацию факультетов по цвету.
     * GET http://localhost:8080/faculty/?color=green
     */

    @GetMapping
    public Collection<Faculty> getFacultyUseColor(@RequestParam("color") String color) {
        Collection<Faculty> facultyUseColor = facultyService.getFacultyUseColor(color);
        if (facultyUseColor.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return facultyUseColor;
    }

    /**
     * показать всех
     * GET http://localhost:8080//all
     */
    @GetMapping("/all")
    public Collection<Faculty> getAllFaculties() {
        return facultyService.getAllFaculties();
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
    public Faculty updateFaculty(@RequestBody Faculty faculty) {
        Faculty facultyForUpdate = facultyService.setFaculty(faculty);
        if (facultyForUpdate == null) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return facultyForUpdate;
    }

    /**
     * DELETE  http://localhost:8080/faculty/2
     */
    @DeleteMapping("{id}")
    public Faculty deleteFaculty(@PathVariable long id) {
        Faculty facultyForDelete = facultyService.removeFaculty(id);
        if (facultyForDelete == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return facultyForDelete;
    }
}
