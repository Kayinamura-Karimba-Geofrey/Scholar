package com.example.Scholar.DTO;

import jakarta.validation.constraints.NotNull;

public record EnrollmentRequestDTO (
        @NotNull Long studentId,
        @NotNull Long courseId) {}
