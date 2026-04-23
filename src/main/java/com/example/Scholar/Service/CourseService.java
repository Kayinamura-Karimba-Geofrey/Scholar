package com.example.Scholar.Service;

import com.example.Scholar.DTO.CourseRequestDTO;
import com.example.Scholar.DTO.CourseResponseDTO;

import java.util.List;

public interface CourseService {

    CourseResponseDTO create(CourseRequestDTO dto);
    List<CourseResponseDTO> getAll();

    CourseResponseDTO getById(Long id);
    CourseResponseDTO update(Long id , CourseRequestDTO dto);
     void  delete(Long id );
    
}
