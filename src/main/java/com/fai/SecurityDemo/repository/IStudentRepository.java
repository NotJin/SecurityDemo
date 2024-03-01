package com.fai.SecurityDemo.repository;

import com.fai.SecurityDemo.entity.Class;
import com.fai.SecurityDemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStudentRepository extends JpaRepository<Student,Integer> {
}
