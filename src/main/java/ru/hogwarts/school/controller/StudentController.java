package ru.hogwarts.school.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.*;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    public Object flag = new Object();

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
    public ResponseEntity<Collection<Student>> findByAgeBetween(@RequestParam int min, @RequestParam int max) {
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

    private void listNamesToConsole( int min, int max){
        List<String> names = studentService.getAllNames();
        for (int i = min; i <= max; i++) {
            System.out.println(names.get(i).toString());
        }
    }

    private void listNamesToConsoleSynchronized(int min, int max){
        synchronized (flag) {
            List<String> names = studentService.getAllNames();
            for (int i = min; i <= max; i++) {
                System.out.println(names.get(i).toString());
            }
        }
    }

    @PostMapping(value = "/getListStudents")
    public void getListStudents() {
        listNamesToConsole(0,1);
        Thread thread = new Thread(() -> {
            listNamesToConsole(1,2);
        });
        thread.start();

        Thread thread2 = new Thread(() -> {
            listNamesToConsole(4,5);
        });
        thread2.start();
    }

    @PostMapping(value = "/getListStudentsSynchronized")
    public void getListStudentsSynchronized() {
        listNamesToConsole(0,1);
        Thread thread = new Thread(() -> {
            listNamesToConsole(1,2);
        });
        thread.start();

        Thread thread2 = new Thread(() -> {
            listNamesToConsole(3,4);
        });
        thread2.start();
    }
}
