package com.example.Scholar.ServiceImpl;

import com.example.Scholar.DTO.CourseRequestDTO;
import com.example.Scholar.DTO.CourseResponseDTO;
import com.example.Scholar.Model.Course;
import com.example.Scholar.Repository.CourseRepository;
import com.example.Scholar.Service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<CourseResponseDTO> getAll() {
        return repo.findAll().stream().map(this::map).toList();
    }

    @Override
    public CourseResponseDTO getById(Long id) {
        Course c = repo.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
        return map(c);
    }

    @Override
    public CourseResponseDTO update(Long id, CourseRequestDTO dto) {
        Course c = repo.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
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
