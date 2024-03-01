package com.fai.SecurityDemo.repository;

import com.fai.SecurityDemo.entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClassRepository extends JpaRepository<Class,String> {
}
