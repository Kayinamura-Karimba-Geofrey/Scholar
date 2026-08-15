package com.example.Scholar.ServiceImpl;

import com.example.Scholar.Exception.ResourceNotFoundException;

import com.example.Scholar.DTO.StudentRequestDto;
import com.example.Scholar.DTO.StudentResponseDto;
import com.example.Scholar.Model.Student;
import com.example.Scholar.Repository.StudentRepository;
import com.example.Scholar.Service.AuditService;
import com.example.Scholar.Service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository repo;
    private final AuditService auditService;

    public StudentServiceImpl(StudentRepository repo, AuditService auditService) {
        this.repo = repo;
        this.auditService = auditService;
    }

    @Override
    public StudentResponseDto create(StudentRequestDto dto) {
        Student s = new Student();
        s.setName(dto.name());
        s.setEmail(dto.email());
        Student saved = repo.save(s);
        auditService.log("CREATE_STUDENT", "Student", saved.getId().toString(), "Created student: " + saved.getName());
        return map(saved);
    }

    @Override
    public Page<StudentResponseDto> getAll(Pageable pageable) {
        return repo.findAll(pageable).map(this::map);
    }

    @Override
    public StudentResponseDto getById(Long id) {
        Student s = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        return map(s);
    }

    @Override
    public StudentResponseDto update(Long id, StudentRequestDto dto) {
        Student s = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        s.setName(dto.name());
        s.setEmail(dto.email());
        Student updated = repo.save(s);
        auditService.log("UPDATE_STUDENT", "Student", updated.getId().toString(), "Updated student info: " + updated.getName());
        return map(updated);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
        auditService.log("DELETE_STUDENT", "Student", id.toString(), "Deleted student with ID: " + id);
    }

    private StudentResponseDto map(Student s) {
        return new StudentResponseDto(s.getId(), s.getName(), s.getEmail());
    }
}
