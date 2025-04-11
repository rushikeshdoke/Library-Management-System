package com.library.service;



import java.util.List;

import com.library.dto.StudentDTO;

public interface StudentService {
    List<StudentDTO> getAllStudents();
    StudentDTO getStudentById(Long id);
    StudentDTO saveStudent(StudentDTO dto);
    void deleteStudent(Long id);
}