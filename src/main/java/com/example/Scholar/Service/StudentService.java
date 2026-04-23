package com.example.Scholar.Service;

import com.example.Scholar.DTO.StudentRequestDto;
import com.example.Scholar.DTO.StudentResponseDto;

import java.util.List;

public interface StudentService {

    StudentResponseDto create(StudentRequestDto requestDto);
    List<StudentResponseDto> getAll();
    StudentResponseDto getById(Long id);
    StudentResponseDto update(Long id, StudentRequestDto dto);
    void delete(Long id);
}
