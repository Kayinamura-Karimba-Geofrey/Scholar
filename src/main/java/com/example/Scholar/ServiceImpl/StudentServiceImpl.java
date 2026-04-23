package com.example.Scholar.ServiceImpl;

import com.example.Scholar.DTO.StudentRequestDto;
import com.example.Scholar.DTO.StudentResponseDto;
import com.example.Scholar.Model.Student;
import com.example.Scholar.Repository.StudentRepository;
import com.example.Scholar.Service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository repo;

    public StudentServiceImpl(StudentRepository repo) {
        this.repo = repo;
    }

    @Override
    public StudentResponseDto create(StudentRequestDto dto) {
        Student s = new Student();
        s.setName(dto.name());
        s.setEmail(dto.email());
        return map(repo.save(s));
    }

    @Override
    public List<StudentResponseDto> getAll() {
        return repo.findAll().stream().map(this::map).toList();
    }

    @Override
    public StudentResponseDto getById(Long id) {
        Student s = repo.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        return map(s);
    }

    @Override
    public StudentResponseDto update(Long id, StudentRequestDto dto) {
        Student s = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        s.setName(dto.name());
        s.setEmail(dto.email());
        return map(repo.save(s));
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    private StudentResponseDto map(Student s) {
        return new StudentResponseDto(s.getId(), s.getName(), s.getEmail());
    }
}
