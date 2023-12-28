package com.example.flashcards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.flashcards.model.Flashcard;
import com.example.flashcards.service.FlashcardService;

@Controller
@RequestMapping("/collection/{collectionId}/flashcard")
public class FlashcardController {

    private final FlashcardService flashcardService;

    @Autowired
    public FlashcardController(FlashcardService flashcardService) {
        this.flashcardService = flashcardService;
    }

    @PostMapping("/create")
    public String store(@PathVariable("collectionId") Long collectionId, @RequestParam("question") String question,
            @RequestParam("answer") String answer) {
        flashcardService.createFlashcard(collectionId, question, answer);
        return "redirect:/collection/" + collectionId;
    }

    @PutMapping("/{flashcardId}")
    public ResponseEntity<Flashcard> edit(@PathVariable("flashcardId") Long flashcardId,
            @RequestParam("question") String question,
            @RequestParam("answer") String answer) {
        Flashcard flashcard = flashcardService.getFlashcardById(flashcardId);
        if (flashcard == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Flashcard editedFlashcard = flashcardService.editFlashcard(flashcard, question, answer);
        return new ResponseEntity<>(editedFlashcard, HttpStatus.OK);
    }

    @DeleteMapping("/{flashcardId}")
    public ResponseEntity<Flashcard> delete(@PathVariable("flashcardId") Long flashcardId) {
        Flashcard flashcard = flashcardService.getFlashcardById(flashcardId);
        if (flashcard == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        flashcardService.deleteFlashcard(flashcard);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
