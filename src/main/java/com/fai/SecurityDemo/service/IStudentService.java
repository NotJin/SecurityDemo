package com.fai.SecurityDemo.service;

import com.fai.SecurityDemo.entity.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentService {
    List<Student> getAllStudents();
    Optional<Student> getStudentById(Integer id);
    void addNewStudent(Student student);
    void updateStudent();
    void deleteStudent(Integer id);
}
