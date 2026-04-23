package com.example.Scholar.Controller;

import com.example.Scholar.DTO.CourseRequestDTO;
import com.example.Scholar.DTO.CourseResponseDTO;
import com.example.Scholar.Service.CourseService;
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
    public CourseResponseDTO create(@RequestBody CourseRequestDTO dto){
        return  service.create(dto);
    }

    @GetMapping
    public List<CourseResponseDTO> getAll(){
        return  service.getAll();
    }

    @GetMapping("/{id}")
    public  CourseResponseDTO getById(@PathVariable Long id ) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public CourseResponseDTO update(@PathVariable Long id, @RequestBody CourseRequestDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }



    
}
