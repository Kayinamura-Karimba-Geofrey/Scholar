package com.example.Scholar.Controller;

import com.example.Scholar.DTO.CourseRequestDTO;
import com.example.Scholar.DTO.CourseResponseDTO;
import com.example.Scholar.Service.CourseService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")

public class CourseController {
    private  final CourseService service;

    public CourseController (CourseService service){
        this.service= service;
    }

    @PostMapping
    public CourseResponseDTO create(@Valid @RequestBody CourseRequestDTO dto){
        return  service.create(dto);
    }

    @GetMapping
    public Page<CourseResponseDTO> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ){
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return  service.getAll(pageable);
    }

    @GetMapping("/{id}")
    public  CourseResponseDTO getById(@PathVariable Long id ) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public CourseResponseDTO update(@PathVariable Long id, @Valid @RequestBody CourseRequestDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }



    
}
