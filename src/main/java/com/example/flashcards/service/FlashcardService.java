package com.example.flashcards.service;

import com.example.flashcards.model.Flashcard;
import com.example.flashcards.repository.FlashcardRepository;

import java.time.LocalDateTime;
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

    public void resetFlashcard(Flashcard flashcard) {
        flashcard.setAnsweredAt(null);
        flashcard.setIsCorrect(null);
        flashcardRepository.save(flashcard);
    }

    public void createFlashcard(Long collectionId, String question, String answer) {
        Flashcard flashcard = new Flashcard();
        flashcard.setCollectionId(collectionId);
        flashcard.setQuestion(question);
        flashcard.setAnswer(answer);
        flashcard.setCreatedAt(LocalDateTime.now());
        flashcardRepository.save(flashcard);
    }
}
