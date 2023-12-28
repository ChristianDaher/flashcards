package com.example.flashcards.controller;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.flashcards.model.User;
import com.example.flashcards.model.Collection;
import com.example.flashcards.model.Flashcard;
import com.example.flashcards.service.CollectionService;
import com.example.flashcards.service.FlashcardService;
import com.example.flashcards.service.UserService;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/collection")
public class CollectionController {

    private final UserService userService;
    private final CollectionService collectionService;
    private final FlashcardService flashcardService;

    @Autowired
    public CollectionController(UserService userService, CollectionService collectionService,
            FlashcardService flashcardService) {
        this.userService = userService;
        this.collectionService = collectionService;
        this.flashcardService = flashcardService;
    }

    @GetMapping("/create")
    public String create() {
        return "collection/create";
    }

    @PostMapping("/create")
    public String store(@RequestParam("title") String title, @RequestParam("category") String category) {
        User user = userService.getUserById(1L); // Should be changed later ofc
        collectionService.createCollection(user.getId(), title, category);
        return "redirect:/";
    }

    @GetMapping("/{collectionId}")
    public String view(@PathVariable Long collectionId, Model model) {
        Collection collection = collectionService.getCollectionById(collectionId);
        if (collection == null) {
            return "collection/not-found";
        }
        List<Flashcard> flashcards = flashcardService.getFlashcardsByCollectionId(collectionId);
        flashcards.sort(Comparator.comparing(Flashcard::getCreatedAt).reversed());
        model.addAttribute("collection", collection);
        model.addAttribute("flashcards", flashcards);
        return "collection/view";
    }

    @PostMapping("/{collectionId}/reset")
    public String reset(@PathVariable Long collectionId) {
        List<Flashcard> flashcards = flashcardService.getFlashcardsByCollectionId(collectionId);
        for (Flashcard flashcard : flashcards) {
            flashcardService.resetFlashcard(flashcard);
        }
        return "redirect:/collection/" + collectionId;
    }

    @GetMapping("/{collectionId}/play")
    public String play(@PathVariable Long collectionId, Model model) {
        // Need more error handling in case of wrong ids and empty arrays
        List<Flashcard> flashcards = flashcardService.getFlashcardsByCollectionId(collectionId);
        List<Flashcard> unasweredFlashcards = flashcardService.getUnansweredFlashcards(flashcards);
        if (!unasweredFlashcards.isEmpty()) {
            Random random = new Random();
            Flashcard randomFlashcard = unasweredFlashcards.get(random.nextInt(unasweredFlashcards.size()));
            model.addAttribute("flashcard", randomFlashcard);
            return "collection/play";
        }
        return "redirect:/";
    }

}
