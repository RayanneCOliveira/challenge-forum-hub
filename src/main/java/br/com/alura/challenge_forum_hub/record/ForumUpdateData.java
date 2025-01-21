package br.com.alura.challenge_forum_hub.record;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record ForumUpdateData(

        @NotNull
        Long id,
        String title,
        String message,
        Date creationDate
) {}