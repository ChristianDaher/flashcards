package com.example.flashcards.repository;

import com.example.flashcards.model.Flashcard;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {
    List<Flashcard> findAllByCollectionId(Long collectionId);

    int countByCollectionId(Long collectionId);
}
