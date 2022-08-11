package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service("StudentService")
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
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
