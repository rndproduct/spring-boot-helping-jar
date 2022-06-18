package com.brother.customresponse.service;

import com.brother.customresponse.payload.Response;
import com.brother.customresponse.payload.StudentDto;

public interface StudentService {

    Response saveStudent(StudentDto studentDto);

    Response studentList();
}
