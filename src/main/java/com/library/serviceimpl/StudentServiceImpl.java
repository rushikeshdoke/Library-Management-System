package com.library.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.dto.StudentDTO;
import com.library.exception.ResourceNotFoundException;
import com.library.mapper.StudentMapper;
import com.library.model.Student;
import com.library.repo.StudentRepository;
import com.library.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<StudentDTO> getAllStudents() {
        return studentRepo.findAll().stream()
                .map(studentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO getStudentById(Long id) {
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        return studentMapper.toDTO(student);
    }

    @Override
    public StudentDTO saveStudent(StudentDTO dto) {
        Student student = studentMapper.toEntity(dto);
        return studentMapper.toDTO(studentRepo.save(student));
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepo.deleteById(id);
    }
}