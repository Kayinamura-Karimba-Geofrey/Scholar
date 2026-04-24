package com.example.Scholar.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record StudentRequestDto(
        @NotBlank(message = "Student name is required")
        String name,

        @NotBlank(message = "Student email is required")
        @Email(message = "Invalid email format")
        String email
) {
}
