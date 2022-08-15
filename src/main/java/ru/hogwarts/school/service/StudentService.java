package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.AllStudents;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service("StudentService")
public class StudentService {

    private final StudentRepository studentRepository;
    private final AvatarRepository avatarRepository;

    Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository, AvatarRepository avatarRepository) {
        this.studentRepository = studentRepository;
        this.avatarRepository = avatarRepository;
    }


    public Integer getCountStudents() {
        logger.info("Was invoked method for count students");
        return studentRepository.getCountStudents();
    }

    public Integer getMiddleAge() {
        logger.info("Was invoked method for getting meddle age");
        return studentRepository.getMiddleAge();
    }


    public List<AllStudents> getAllStudents() {
        logger.info("Was invoked method for getting list of all students");
        return studentRepository.getAllStudents();
    }


    public Student createStudent(Student student) {
        logger.info("Was invoked method for create student");
        student.setId(-1);
        return studentRepository.save(student);
    }

    public Student readStudent(long id) {
        logger.info("Was invoked method for find student by ID {}", id);
        return studentRepository.findById(id).orElseThrow();
    }

    public Student updateStudent(Student student) {
        logger.info("Was invoked method for update student");
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        logger.info("Was invoked method for delete student by ID {}", id);
        studentRepository.deleteById(id);

    }

    public Collection<Student> findAllByFacultyId(int id) {
        logger.info("Was invoked method for find all student in faculty by ID {}", id);
        return studentRepository.findAllByFacultyId(id);
    }

    public Collection<Student> findByAgeBetween(int min, int max) {
        logger.info("Was invoked method for find student between {} and {}", min, max);
        return studentRepository.findByAgeBetween(min, max);
    }

    public Collection<Student> findStudentsByAge(int age) {
        logger.info("Was invoked method for find all student where age =  {}", age);
        return studentRepository.findAllByAge(age);
    }


    public List findAllStudentsOrderAsc() {
        return studentRepository.findAll().stream().map(user -> user.getName())
                .filter(s -> s.startsWith("a"))
                .sorted((s1, s2) -> s1.compareTo(s2))
                .map(s -> s.toUpperCase())
                .collect(Collectors.toList());

                }

    public Double getAverageAge() {
        return studentRepository.findAll().stream().mapToDouble(user -> user.getAge())
                .average()
                .getAsDouble();
    }
}
