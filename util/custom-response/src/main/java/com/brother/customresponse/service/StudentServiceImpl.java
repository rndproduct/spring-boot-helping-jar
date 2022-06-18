package com.brother.customresponse.service;

import com.brother.customresponse.entity.Student;
import com.brother.customresponse.enums.ActiveStatus;
import com.brother.customresponse.payload.Response;
import com.brother.customresponse.payload.StudentDto;
import com.brother.customresponse.repository.StudentRepository;
import com.brother.customresponse.util.ResponseBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public StudentServiceImpl(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Response saveStudent(StudentDto studentDto) {
        Student student = modelMapper.map(studentDto, Student.class);
        Student finalStudent = student;
        student.getAddress().forEach(address -> address.setStudent(finalStudent));
        student = studentRepository.save(student);
        if (student != null) {
            return ResponseBuilder.getSuccessResponse(HttpStatus.CREATED, "Student Has Been Saved", student.getId());
        }
        return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
    }

    @Override
    public Response studentList() {
        Optional<List<Student>> studentList = studentRepository.getStudentByActiveStatus(ActiveStatus.ACTIVE.getValue());
        if (studentList.isEmpty()) {
            return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, "No Student is available");
        }
        return ResponseBuilder.getSuccessResponse(HttpStatus.OK, "Student List retrieved successfully",
                getStudentList(studentList.get()));
    }

    private List<StudentDto> getStudentList(List<Student> studentList) {
        return studentList.stream().map(student -> modelMapper.map(student, StudentDto.class)).
                collect(Collectors.toList());
    }
}
