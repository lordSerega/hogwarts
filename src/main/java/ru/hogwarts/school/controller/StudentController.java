package ru.hogwarts.school.controller;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;


    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }


    @GetMapping("{id}")
    public ResponseEntity<Student> readStudent(@PathVariable Long id) {
        Student student = studentService.readStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student foundStudent = studentService.updateStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findAllByFacultyId")
    public ResponseEntity<Collection<Student>> findAllByFacultyId(@RequestParam int id) {
        if (id != -1) {
            return ResponseEntity.ok(studentService.findAllByFacultyId(id));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }


    @GetMapping("/findByAgeBetween")
    public ResponseEntity<Collection<Student>> findByAgeBetween(@RequestParam int min,
                                                                @RequestParam int max) {
        if (min != -1 && max != -1) {
            return ResponseEntity.ok(studentService.findByAgeBetween(min, max));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> findStudentsByAge(@RequestParam int age) {
        if (age > 0) {
            return ResponseEntity.ok(studentService.findStudentsByAge(age));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @PostMapping(value = "/findAll")
    public ResponseEntity<List> findAllStudentsOrderAsc() {
        return ResponseEntity.ok(studentService.findAllStudentsOrderAsc());
    }

    @PostMapping(value = "/averageAge")
    public ResponseEntity<Double> getAverageAge() {
        return ResponseEntity.ok(studentService.getAverageAge());
    }



}
