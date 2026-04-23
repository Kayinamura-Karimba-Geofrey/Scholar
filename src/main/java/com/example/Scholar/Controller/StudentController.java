package com.example.Scholar.Controller;

import com.example.Scholar.DTO.StudentRequestDto;
import com.example.Scholar.DTO.StudentResponseDto;
import com.example.Scholar.Service.StudentService;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public StudentResponseDto create(@RequestBody StudentRequestDto dto){
        return service.create (dto);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public List<StudentResponseDto> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public StudentResponseDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
     public  StudentResponseDto update(@PathVariable Long id, @RequestBody StudentRequestDto dto){
        return service.update(id,dto);


    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public  void  delete(@PathVariable Long id ){
        service.delete(id);
    }


    
}
