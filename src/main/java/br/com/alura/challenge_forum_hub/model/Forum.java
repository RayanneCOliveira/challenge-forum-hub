package br.com.alura.challenge_forum_hub.model;

import br.com.alura.challenge_forum_hub.record.ForumRegistrationData;
import br.com.alura.challenge_forum_hub.record.ForumUpdateData;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "forum")
@Entity(name = "forum")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Forum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private Date creationDate = new Date();
    private boolean active = true;

    public Forum(Forum forum) {
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Forum(@Valid ForumRegistrationData data) {
        this.title = data.title();
        this.message = data.message();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    public Date getCreationDate() {
        return creationDate;
    }

    public void updateInfos(@Valid ForumUpdateData data) {
        this.title = data.title();
        this.message = data.message();
    }

}