package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.model.AllStudents;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RestController
public class AllStudentsController {

    private final StudentService studentService;

    public AllStudentsController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/count-students")
    public Integer getCountStudents() {
        return studentService.getCountStudents();

    }

    @GetMapping("/middle-age")
    public Integer middle_age() {
        return studentService.getMiddleAge();
    }

    @GetMapping("/all-students")
    public List<AllStudents> getAllStudents() {
        return studentService.getAllStudents();
    }
}
