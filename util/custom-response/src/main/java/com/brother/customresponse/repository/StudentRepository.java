package com.brother.customresponse.repository;

import com.brother.customresponse.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("from Student s where s.activeStatus = :activeStatus")
    Optional<List<Student>> getStudentByActiveStatus(@Param("activeStatus") Integer activeStatus);
}
