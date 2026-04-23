package com.example.Scholar.DTO;

public record  EnrollmentResponseDTO (
        Long id,
        Long studentId,
        String studentName,
        Long  courseId,
        String courseTitle,
         Double grade
)
{}
