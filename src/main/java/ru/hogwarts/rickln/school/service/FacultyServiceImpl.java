package ru.hogwarts.rickln.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.rickln.school.model.Faculty;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final Map<Long, Faculty> facultyStore = new HashMap<>();
    private long lastId = 0;

    @Override
    public Faculty addFaculty(Faculty faculty) {
        faculty.setId(++lastId);
        facultyStore.put(lastId, faculty);
        return faculty;
    }

    /**
     * найти по идентификатору
     *
     * @param id long
     * @return Faculty
     */
    @Override
    public Faculty getFaculty(Long id) {
        return facultyStore.get(id);
    }

    /**
     * показать всех
     *
     * @return Collection<Faculty>
     */
    @Override
    public Collection<Faculty> getAllFaculties() {
        return facultyStore.values();
    }

    /**
     * Добавить фильтрацию факультетов по цвету.
     *
     * @param color String
     * @return Collection<Faculty>
     */
    @Override
    public Collection<Faculty> getFacultyUseColor(String color) {
        return facultyStore.values().stream()
                .filter(faculty -> Objects.equals(faculty.getColor(), color))
                .collect(Collectors.toList());
    }

    @Override
    public Faculty setFaculty(Faculty faculty) {
        if (facultyStore.containsKey(faculty.getId())) {
            facultyStore.put(faculty.getId(), faculty);
            return faculty;
        }
        return null;
    }

    @Override
    public Faculty removeFaculty(Long id) {
        return facultyStore.remove(id);
    }
}
