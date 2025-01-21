package br.com.alura.challenge_forum_hub.record;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record ForumRegistrationData(
        @NotBlank
        String title,
        @NotBlank
        String message,
        Date creationDate
) {}