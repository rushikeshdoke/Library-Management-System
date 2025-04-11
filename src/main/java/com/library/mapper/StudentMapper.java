package com.library.mapper;



import com.library.dto.StudentDTO;
import com.library.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public StudentDTO toDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setUsername(student.getUsername());
        return dto;
    }

    public Student toEntity(StudentDTO dto) {
        Student student = new Student();
        student.setId(dto.getId());
        student.setUsername(dto.getUsername());
        // Normally, you might set a password here — ensure you handle it securely!
        return student;
    }
}
