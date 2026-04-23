package com.example.Scholar.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record StudentRequestDto(
        @NotBlank String name,
        @Email String email
) {
}
