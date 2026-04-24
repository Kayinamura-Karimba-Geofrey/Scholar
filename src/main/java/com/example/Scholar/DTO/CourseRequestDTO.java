package com.example.Scholar.DTO;

import jakarta.validation.constraints.NotBlank;

public record CourseRequestDTO(
        @NotBlank(message = "Course title is required")
        String title
) {
}
