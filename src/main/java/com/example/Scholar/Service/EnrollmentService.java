package com.example.Scholar.Service;

import com.example.Scholar.DTO.EnrollmentRequestDTO;
import com.example.Scholar.DTO.EnrollmentResponseDTO;

import java.util.List;

public interface  EnrollmentService {
    EnrollmentResponseDTO enroll(EnrollmentRequestDTO dto);
    List<EnrollmentResponseDTO> getAll();
    List<EnrollmentResponseDTO> getByStudent(Long studentId);
    List<EnrollmentResponseDTO> getByCourse(Long courseId);
    void delete(Long id);
    EnrollmentResponseDTO assignGrade(Long enrollmentId, Double grade);
}
