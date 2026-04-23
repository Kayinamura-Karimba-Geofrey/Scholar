package com.example.Scholar.Controller;

import com.example.Scholar.DTO.EnrollmentRequestDTO;
import com.example.Scholar.DTO.EnrollmentResponseDTO;
import com.example.Scholar.Service.EnrollmentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
    private final EnrollmentService service;

    public  EnrollmentController(EnrollmentService service){
        this.service= service;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public EnrollmentResponseDTO enroll(@RequestBody EnrollmentRequestDTO dto){
        return service.enroll(dto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<EnrollmentResponseDTO> getAll(){
        return service.getAll();
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/student/{studentId}")
    public List<EnrollmentResponseDTO> getByStudent(@PathVariable Long studentId) {
        return service.getByStudent(studentId);
    }

    @GetMapping("/course/{courseId}")
    public List<EnrollmentResponseDTO> getByCourse(@PathVariable Long courseId) {
        return service.getByCourse(courseId);
    }
    @PutMapping("/{id}/grade")
    public EnrollmentResponseDTO assignGrade(
            @PathVariable Long id,
            @RequestParam Double grade
    ) {
        return service.assignGrade(id, grade);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }


}
