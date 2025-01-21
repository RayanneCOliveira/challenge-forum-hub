package br.com.alura.challenge_forum_hub.record;

import br.com.alura.challenge_forum_hub.model.Forum;

import java.util.Date;

public record ForumListingData(
        Long id,
        String title,
        String message,
        Date creationDate) {

    public ForumListingData(Forum forum){
        this(forum.getId(), forum.getTitle(), forum.getMessage(), forum.getCreationDate());
    }
}