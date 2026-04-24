package com.example.Scholar.Controller;

import com.example.Scholar.DTO.StudentRequestDto;
import com.example.Scholar.DTO.StudentResponseDto;
import com.example.Scholar.Service.StudentService;
import org.springframework.security.access.prepost.PreAuthorize;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService service;

    public  StudentController(StudentService service){
        this .service = service;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public StudentResponseDto create(@Valid @RequestBody StudentRequestDto dto){
        return service.create (dto);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public Page<StudentResponseDto> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ){
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return service.getAll(pageable);
    }

    @GetMapping("/{id}")
    public StudentResponseDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
     public  StudentResponseDto update(@PathVariable Long id, @Valid @RequestBody StudentRequestDto dto){
        return service.update(id,dto);


    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public  void  delete(@PathVariable Long id ){
        service.delete(id);
    }


    
}
