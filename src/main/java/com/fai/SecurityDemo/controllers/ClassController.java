package com.fai.SecurityDemo.controllers;

import com.fai.SecurityDemo.entity.Class;
import com.fai.SecurityDemo.service.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ClassController {

    private final IClassService classService;
    @Autowired
    public ClassController(IClassService classService) {
        this.classService = classService;
    }

    @GetMapping("/listClass")
    public String getAllClass(Model model){
        List<Class> classList = classService.getAllClass();
        model.addAttribute("classList", classList);
        return "class/index";
    }
    @PostMapping("saveClass")
    public String addNewClass(@ModelAttribute("Class")Class aClass){
        classService.addNewClass(aClass);
        return "redirect:class/index";
    }
    @GetMapping("/addNewClass")
    public String addNewClass(@RequestParam("ClassId")String id, Model model){
        Optional<Class> classList = classService.getByIdClass(id);
        model.addAttribute("classList",classList);
        return "student/addNew";
    }
}
