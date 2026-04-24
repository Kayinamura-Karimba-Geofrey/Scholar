package com.example.Scholar.Service;

import com.example.Scholar.DTO.StudentRequestDto;
import com.example.Scholar.DTO.StudentResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentService {

    StudentResponseDto create(StudentRequestDto requestDto);
    Page<StudentResponseDto> getAll(Pageable pageable);
    StudentResponseDto getById(Long id);
    StudentResponseDto update(Long id, StudentRequestDto dto);
    void delete(Long id);
}
