package com.example.flashcards.service;

import com.example.flashcards.model.Flashcard;
import com.example.flashcards.repository.FlashcardRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlashcardService {

    public final FlashcardRepository flashcardRepository;

    @Autowired
    public FlashcardService(FlashcardRepository flashcardRepository) {
        this.flashcardRepository = flashcardRepository;
    }

    public List<Flashcard> getFlashcardsByCollectionId(Long collectionId) {
        return flashcardRepository.findAllByCollectionId(collectionId);
    }

    public List<Flashcard> getUnansweredFlashcards(List<Flashcard> flashcards) {
        return flashcards.stream().filter(flashcard -> flashcard.getAnsweredAt() == null).collect(Collectors.toList());
    }
}
