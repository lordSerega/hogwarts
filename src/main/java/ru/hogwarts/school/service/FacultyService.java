package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.*;

@Service("FacultyService")
public class FacultyService {

    private FacultyRepository facultyRepository;
    Logger logger = LoggerFactory.getLogger(StudentService.class);

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        logger.info("Was invoked method for create faculty");
        faculty.setId(-1);
        return facultyRepository.save(faculty);
    }

    public Faculty readFaculty(long id) {
        logger.info("Was invoked method for find faculty by ID {}", id);
        return facultyRepository.findById(id).orElseThrow();
    }

    public Faculty updateFaculty(Faculty faculty) {
        logger.info("Was invoked method for update faculty");
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        logger.info("Was invoked method for delete faculty by ID {}", id);
        facultyRepository.deleteById(id);
    }


    public Collection<Faculty> findAllByColorOrNameIgnoreCase(String color, String name) {
        logger.info("Was invoked method for find faculties by color or name with ignore case");
        logger.debug("color = {}",color);
        logger.debug("name = {}",name);
        return facultyRepository.findAllByColorIgnoreCaseOrNameIgnoreCase(color, name);
    }

    public Collection<Faculty> getFacultiesByColor(String color) {
        logger.info("Was invoked method for find faculties by color");
        return facultyRepository.findAllByColor(color);
    }

    public Optional<String> getLargestName() {
        return facultyRepository.findAll().stream()
                .map(user -> user.getName())
                .max(Comparator.comparingInt(String::length));

    }
}
