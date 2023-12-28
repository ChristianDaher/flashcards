package com.example.flashcards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
}
