package ru.hogwarts.rickln.school.service;

import ru.hogwarts.rickln.school.model.Faculty;

import java.util.Collection;

public interface FacultyService {
    Faculty addFaculty(Faculty faculty);

    Faculty getFaculty(Long id);

    Collection<Faculty> getAllFaculties();

    Collection<Faculty> getFacultyUseColor(String color);

    Faculty setFaculty(Faculty faculty);

    Faculty removeFaculty(Long id);
}
