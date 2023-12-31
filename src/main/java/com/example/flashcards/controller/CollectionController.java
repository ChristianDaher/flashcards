package com.example.flashcards.controller;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.flashcards.model.User;
import com.example.flashcards.model.Collection;
import com.example.flashcards.model.Flashcard;
import com.example.flashcards.service.CollectionService;
import com.example.flashcards.service.FlashcardService;
import com.example.flashcards.service.UserService;
import org.springframework.ui.Model;
import com.example.flashcards.util.InputUtil;

// This is the collection controller, it handles all the routes that start with /collection
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

    // Route to view the create a collection page
    @GetMapping("/create")
    public String create() {
        return "collection/create";
    }

    // Route to actually create a collection
    @PostMapping("/create")
    public String store(@RequestParam("title") String title, @RequestParam("category") String category) {
        title = InputUtil.sanitize(title);
        category = InputUtil.sanitize(category);
        User user = userService.getUserById(1L); // Should be changed later ofc
        collectionService.createCollection(user.getId(), title, category);
        return "redirect:/";
    }

    // Route to view a collection
    @GetMapping("/{collectionId}")
    public String view(@PathVariable Long collectionId, Model model) {
        Collection collection = collectionService.getCollectionById(collectionId);
        List<Flashcard> flashcards = flashcardService.getFlashcardsByCollectionId(collectionId);
        flashcards.sort(Comparator.comparing(Flashcard::getCreatedAt).reversed());
        model.addAttribute("collection", collection);
        model.addAttribute("flashcards", flashcards);
        return "collection/view";
    }

    // Route to edit a collection
    @PutMapping("/{collectionId}")
    public ResponseEntity<Collection> edit(@PathVariable Long collectionId, @RequestParam("title") String title,
            @RequestParam("category") String category) {
        title = InputUtil.sanitize(title);
        category = InputUtil.sanitize(category);
        Collection collection = collectionService.getCollectionById(collectionId);
        Collection editedCollection = collectionService.editCollection(collection, title, category);
        return new ResponseEntity<>(editedCollection, HttpStatus.OK);
    }

    // Route to delete a collection and all its flashcards
    @DeleteMapping("/{collectionId}")
    public ResponseEntity<Collection> delete(@PathVariable Long collectionId) {
        Collection collection = collectionService.getCollectionById(collectionId);
        collectionService.deleteCollection(collection);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Route to reset a collection's flashcards
    @PostMapping("/{collectionId}/reset")
    public String reset(@PathVariable Long collectionId) {
        List<Flashcard> flashcards = flashcardService.getFlashcardsByCollectionId(collectionId);
        for (Flashcard flashcard : flashcards) {
            flashcardService.resetFlashcard(flashcard);
        }
        return "redirect:/collection/" + collectionId;
    }

    // Route to play a collection
    @GetMapping("/{collectionId}/play")
    public String play(@PathVariable Long collectionId, Model model) {
        String view = "collection/play";
        List<Flashcard> flashcards = flashcardService.getFlashcardsByCollectionId(collectionId);
        List<Flashcard> unasweredFlashcards = flashcards != null ? flashcardService.getUnansweredFlashcards(flashcards)
                : null;

        if (flashcards == null || flashcards.isEmpty()) {
            model.addAttribute("errorMessage", "Collection has no flashcards");
            view = "error";
        } else if (unasweredFlashcards == null || unasweredFlashcards.isEmpty()) {
            model.addAttribute("errorMessage",
                    "All the flashcards inside this collection are answered, <br/> please reset the collection to play again");
            model.addAttribute("backLink", "/collection/" + collectionId);
            view = "error";
        } else {
            Random random = new Random();
            Flashcard randomFlashcard = unasweredFlashcards.get(random.nextInt(unasweredFlashcards.size()));
            model.addAttribute("flashcard", randomFlashcard);
        }

        return view;
    }

}
