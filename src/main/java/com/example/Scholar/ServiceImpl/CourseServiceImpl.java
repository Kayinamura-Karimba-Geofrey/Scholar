package com.example.Scholar.ServiceImpl;

import com.example.Scholar.DTO.CourseRequestDTO;
import com.example.Scholar.DTO.CourseResponseDTO;
import com.example.Scholar.Model.Course;
import com.example.Scholar.Repository.CourseRepository;
import com.example.Scholar.Exception.ResourceNotFoundException;
import com.example.Scholar.Service.CourseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository repo;

    public CourseServiceImpl(CourseRepository repo) {
        this.repo = repo;
    }

    @Override
    public CourseResponseDTO create(CourseRequestDTO dto) {
        Course c = new Course();
        c.setTitle(dto.title());
        return map(repo.save(c));
    }

    @Override
    public Page<CourseResponseDTO> getAll(Pageable pageable) {
        return repo.findAll(pageable).map(this::map);
    }

    @Override
    public CourseResponseDTO getById(Long id) {
        Course c = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        return map(c);
    }

    @Override
    public CourseResponseDTO update(Long id, CourseRequestDTO dto) {
        Course c = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        c.setTitle(dto.title());
        return map(repo.save(c));
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    private CourseResponseDTO map(Course c) {
        return new CourseResponseDTO(c.getId(), c.getTitle());
    }
}
