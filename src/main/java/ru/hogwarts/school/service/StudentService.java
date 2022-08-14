package ru.hogwarts.school.service;

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

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service("StudentService")
public class StudentService {




    private final StudentRepository studentRepository;
    private final AvatarRepository avatarRepository;


    public StudentService(StudentRepository studentRepository, AvatarRepository avatarRepository) {
        this.studentRepository = studentRepository;
        this.avatarRepository = avatarRepository;
    }


    public Integer getCountStudents() {
        return studentRepository.getCountStudents();
    }

    public Integer getMiddleAge() {
        return studentRepository.getMiddleAge();
    }


    public List<AllStudents> getAllStudents() {
        return studentRepository.getAllStudents();
    }


    public Student createStudent(Student student) {
        student.setId(-1);
        return studentRepository.save(student);
    }

    public Student readStudent(long id) {
        return studentRepository.findById(id).orElseThrow();
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);

    }

    public Collection<Student>  findAllByFacultyId(int id){
        return studentRepository.findAllByFacultyId(id);
    }

    public Collection<Student>  findByAgeBetween(int min, int max){
        return studentRepository.findByAgeBetween(min,max);
    }

    public Collection<Student> findStudentsByAge(int age) {
        return studentRepository.findAllByAge(age);
    }



}
