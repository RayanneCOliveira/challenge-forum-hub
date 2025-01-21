package br.com.alura.challenge_forum_hub.controller;

import br.com.alura.challenge_forum_hub.exception.NotFoundException;
import br.com.alura.challenge_forum_hub.model.Forum;
import br.com.alura.challenge_forum_hub.record.ForumDetailData;
import br.com.alura.challenge_forum_hub.record.ForumListingData;
import br.com.alura.challenge_forum_hub.record.ForumRegistrationData;
import br.com.alura.challenge_forum_hub.record.ForumUpdateData;
import br.com.alura.challenge_forum_hub.repository.ForumRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

@RestController
@RequestMapping("topics")
public class ForumController {

    @Autowired
    private ForumRepository forumRepository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid ForumRegistrationData data, UriComponentsBuilder uriBuilder) {
        var forum = new Forum(data);
        forumRepository.save(forum);

        var uri = uriBuilder.path("/topics/{id}").buildAndExpand(forum.getId()).toUri();

        return ResponseEntity.created(uri).body(new ForumDetailData(forum));
    }

    @GetMapping
    public ResponseEntity<Page<ForumListingData>> list(@PageableDefault(size = 10, sort = {"title"}) Pageable pageable) {
        var page = forumRepository.findAllByActiveTrue(pageable).map(ForumListingData::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping("update")
    @Transactional
    public ResponseEntity update(@RequestBody @Valid ForumUpdateData data) {
        var forum = forumRepository.findById(data.id())
                .orElseThrow(() -> new NotFoundException("Forum not found!"));

        forum.updateInfos(data);

        return ResponseEntity.ok(new ForumDetailData(forum));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        var forum = forumRepository.getReferenceById(id);
        forumRepository.deleteAllById(Collections.singleton(id));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable Long id) {
        var forum = forumRepository.getReferenceById(id);
        return ResponseEntity.ok(new Forum(forum));
    }

}