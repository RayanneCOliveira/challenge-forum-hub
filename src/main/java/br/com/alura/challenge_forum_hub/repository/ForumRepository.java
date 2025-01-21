package br.com.alura.challenge_forum_hub.repository;

import br.com.alura.challenge_forum_hub.model.Forum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumRepository extends JpaRepository<Forum, Long> {
    Page<Forum> findAllByActiveTrue(Pageable pageable);
}
