package com.brother.customresponse.service;

import com.brother.customresponse.payload.Response;
import com.brother.customresponse.payload.StudentDto;

import java.util.List;

public interface StudentService {

    Response saveStudent(StudentDto studentDto);

    Response studentList();
}
