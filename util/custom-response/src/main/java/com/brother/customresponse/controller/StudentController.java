package com.brother.customresponse.controller;

import com.brother.customresponse.payload.Response;
import com.brother.customresponse.payload.StudentDto;
import com.brother.customresponse.service.StudentService;
import com.brother.customresponse.util.ResponseBuilder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/student-save")
    public Response saveStudent(@Valid @RequestBody StudentDto studentDto, BindingResult result) {
        if (result.hasErrors()) {
            ResponseBuilder.getFailureResponse(result, "");
        }
        return studentService.saveStudent(studentDto);
    }

    @GetMapping("student-list")
    public Response getAllStudent() {
        return studentService.studentList();
    }
}
