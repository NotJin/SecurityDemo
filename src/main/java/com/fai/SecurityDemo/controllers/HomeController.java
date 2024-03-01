package com.fai.SecurityDemo.controllers;

import com.fai.SecurityDemo.entity.Class;
import com.fai.SecurityDemo.entity.Student;
import com.fai.SecurityDemo.service.StudentService;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.logout.DelegatingServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.SecurityContextServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.WebSessionServerLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomeController {

    final StudentService studentService;

    public HomeController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping("/list")
    public String StudentService(Model model){
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "index";
    }

    @PostMapping("/save")
    public String SaveStudent(@ModelAttribute("student") Student student) {
        studentService.addNewStudent(student);
        return "redirect:/index";
    }

    @GetMapping("formUpdate")
    public String ShowFormUpdate(@RequestParam("studentId") Integer id, Model model) {
        Optional<Student> student = studentService.getStudentById(id);
        model.addAttribute("student",student);
        return "students/addNew";
    }
    @GetMapping("delete")
    public String DeleteStudent(@RequestParam("studentId") Integer id,Model model){
        studentService.deleteStudent(id);
        return "redirect:/index";
    }
    @GetMapping("/Pricing")
    public String Pricing(){
        return "pricing";
    }
    public SecurityWebFilterChain http(ServerHttpSecurity http) throws Exception {
        DelegatingServerLogoutHandler logoutHandler = new DelegatingServerLogoutHandler(
                new WebSessionServerLogoutHandler(), new SecurityContextServerLogoutHandler()
        );
        http
                .authorizeExchange((exchange)-> exchange.anyExchange().authenticated())
                .logout((logout)->logout.logoutHandler(logoutHandler));
        return http.build();
    }
}
