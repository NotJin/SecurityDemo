package com.fai.SecurityDemo.service;

import com.fai.SecurityDemo.entity.Class;

import java.util.List;
import java.util.Optional;

public interface IClassService {
    List<Class> getAllClass();
    Optional<Class> getByIdClass(String id);
    void addNewClass(Class aClass);
}
