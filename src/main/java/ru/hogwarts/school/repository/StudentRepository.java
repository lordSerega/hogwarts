package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.AllStudents;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    @Query(value = "SELECT count(*) as count_students FROM student", nativeQuery = true)
    Integer getCountStudents();

    @Query(value = "SELECT AVG(age) as middle_age FROM student", nativeQuery = true)
    Integer getMiddleAge();


    @Query(value = "SELECT * from student ORDER BY id DESC LIMIT 5;", nativeQuery = true)
    List<AllStudents> getAllStudents();



    Collection<Student> findAllByAge(int age);

    Collection<Student> findByAgeBetween(int min, int max);

    Collection<Student> findAllByFacultyId(int facultyId);

    @Query(value = "SELECT name from student", nativeQuery = true)
    List<String> getName();


}
