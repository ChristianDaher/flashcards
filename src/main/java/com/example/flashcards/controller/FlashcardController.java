package com.example.flashcards.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.flashcards.model.Flashcard;
import com.example.flashcards.service.FlashcardService;
import com.example.flashcards.util.InputUtil;

// This is the flashcard controller, it handles all the routes that start with /collection/{collectionId}/flashcard
@Controller
@RequestMapping("/collection/{collectionId}/flashcard")
public class FlashcardController {

    private final FlashcardService flashcardService;

    @Autowired
    public FlashcardController(FlashcardService flashcardService) {
        this.flashcardService = flashcardService;
    }

    // Route to create a flashcard
    @PostMapping("/create")
    public String store(@PathVariable("collectionId") Long collectionId, @RequestParam("question") String question,
            @RequestParam("answer") String answer) {
        question = InputUtil.sanitize(question);
        answer = InputUtil.sanitize(answer);
        flashcardService.createFlashcard(collectionId, question, answer);
        return "redirect:/collection/" + collectionId;
    }

    // Route to edit a flashcard
    @PutMapping("/{flashcardId}")
    public ResponseEntity<Flashcard> edit(@PathVariable("flashcardId") Long flashcardId,
            @RequestParam("question") String question,
            @RequestParam("answer") String answer) {
        question = InputUtil.sanitize(question);
        answer = InputUtil.sanitize(answer);
        Flashcard flashcard = flashcardService.getFlashcardById(flashcardId);
        Flashcard editedFlashcard = flashcardService.editFlashcard(flashcard, question, answer);
        return new ResponseEntity<>(editedFlashcard, HttpStatus.OK);
    }

    // Route to delete a flashcard
    @DeleteMapping("/{flashcardId}")
    public ResponseEntity<Flashcard> delete(@PathVariable("flashcardId") Long flashcardId) {
        Flashcard flashcard = flashcardService.getFlashcardById(flashcardId);
        flashcardService.deleteFlashcard(flashcard);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Route to mark a flashcard as correct or wrong
    @PatchMapping("/{flashcardId}/answer")
    public ResponseEntity<Flashcard> markAsCorrect(@PathVariable("flashcardId") Long flashcardId,
            @RequestBody Map<String, Boolean> answerMap) {
        Flashcard flashcard = flashcardService.getFlashcardById(flashcardId);
        Boolean answer = answerMap.get("answer");
        if (answer == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Flashcard editedFlashcard = flashcardService.answerFlashcard(flashcard, answer);
        return new ResponseEntity<>(editedFlashcard, HttpStatus.OK);
    }
}
