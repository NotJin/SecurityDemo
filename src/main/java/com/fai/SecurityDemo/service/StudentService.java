package com.fai.SecurityDemo.service;

import com.fai.SecurityDemo.entity.Class;
import com.fai.SecurityDemo.entity.Student;
import com.fai.SecurityDemo.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements IStudentService{
    private final IStudentRepository studentRepostirory;

    @Autowired
    public StudentService(IStudentRepository studentRepostirory) {
        this.studentRepostirory = studentRepostirory;
    }
    @Override
    public List<Student> getAllStudents() {
        return studentRepostirory.findAll();
    }

    @Override
    public Optional<Student> getStudentById(Integer id) {
        return studentRepostirory.findById(id);
    }

    @Override
    public void addNewStudent(Student student) {
        studentRepostirory.save(student);
    }

    @Override
    public void updateStudent() {

    }

    @Override
    public void deleteStudent(Integer id) {
        studentRepostirory.deleteById(id);
    }
}
