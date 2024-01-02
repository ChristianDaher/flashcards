package com.example.flashcards.service;

import com.example.flashcards.model.Flashcard;
import com.example.flashcards.repository.FlashcardRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// This handles all the business logic related to flashcards
@Service
public class FlashcardService {

    public final FlashcardRepository flashcardRepository;

    @Autowired
    public FlashcardService(FlashcardRepository flashcardRepository) {
        this.flashcardRepository = flashcardRepository;
    }

    // This method will return all the flashcards of a collection
    public List<Flashcard> getFlashcardsByCollectionId(Long collectionId) {
        return flashcardRepository.findAllByCollectionId(collectionId);
    }

    // This method will return all the unanswered flashcards of a collection
    public List<Flashcard> getUnansweredFlashcards(List<Flashcard> flashcards) {
        return flashcards.stream().filter(flashcard -> flashcard.getAnsweredAt() == null).collect(Collectors.toList());
    }

    // This method will reset all the flashcards of a collection
    public void resetFlashcard(Flashcard flashcard) {
        flashcard.setAnsweredAt(null);
        flashcard.setIsCorrect(null);
        flashcardRepository.save(flashcard);
    }

    // This method will create a flashcard
    public void createFlashcard(Long collectionId, String question, String answer) {
        Flashcard flashcard = new Flashcard();
        flashcard.setCollectionId(collectionId);
        flashcard.setQuestion(question);
        flashcard.setAnswer(answer);
        flashcard.setCreatedAt(LocalDateTime.now());
        flashcardRepository.save(flashcard);
    }

    // This method will return a flashcard by id
    public Flashcard getFlashcardById(Long id) {
        return flashcardRepository.findById(id).orElse(null);
    }

    // This method will edit a flashcard
    public Flashcard editFlashcard(Flashcard flashcard, String question, String answer) {
        flashcard.setQuestion(question);
        flashcard.setAnswer(answer);
        resetFlashcard(flashcard);
        return flashcard;
    }

    // This method will delete a flashcard
    public void deleteFlashcard(Flashcard flashcard) {
        flashcardRepository.delete(flashcard);
    }

    // This method will answer a flashcard
    public Flashcard answerFlashcard(Flashcard flashcard, Boolean isCorrect) {
        flashcard.setAnsweredAt(LocalDateTime.now());
        flashcard.setIsCorrect(isCorrect);
        flashcardRepository.save(flashcard);
        return flashcard;
    }
}
