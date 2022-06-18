package com.brother.customresponse.controller;

import com.brother.customresponse.payload.Response;
import com.brother.customresponse.payload.StudentDto;
import com.brother.customresponse.service.StudentService;
import com.brother.customresponse.util.ResponseBuilder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("student-management/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/save")
    public Response saveStudent(@Valid @RequestBody StudentDto studentDto, BindingResult result) {
        if (result.hasErrors()) {
            ResponseBuilder.getFailureResponse(result, "");
        }
        return studentService.saveStudent(studentDto);
    }

    @GetMapping("list")
    public Response getAllStudent() {
        return studentService.studentList();
    }
}
