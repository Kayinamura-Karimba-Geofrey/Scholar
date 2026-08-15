package com.example.Scholar.Service;

import com.example.Scholar.DTO.CourseRequestDTO;
import com.example.Scholar.DTO.CourseResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseService {

    CourseResponseDTO create(CourseRequestDTO dto);
    Page<CourseResponseDTO> getAll(Pageable pageable);

    CourseResponseDTO getById(Long id);
    CourseResponseDTO update(Long id , CourseRequestDTO dto);
    void  delete(Long id );
}
