package com.fai.SecurityDemo.service;

import com.fai.SecurityDemo.entity.Class;
import com.fai.SecurityDemo.repository.IClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassService implements IClassService{
    private final IClassRepository classRepository;

    @Autowired
    public ClassService(IClassRepository classRepository) {
        this.classRepository = classRepository;
    }


    @Override
    public List<Class> getAllClass() {
        return classRepository.findAll();
    }

    @Override
    public Optional<Class> getByIdClass(String id) {
        return classRepository.findById(id);
    }

    @Override
    public void addNewClass(Class aClass) {
        classRepository.save(aClass);
    }
}
