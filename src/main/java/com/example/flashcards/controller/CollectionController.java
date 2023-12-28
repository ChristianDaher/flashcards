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
        title = title.trim().replace("\n", " ").replace("\r", "");
        category = category.trim().replace("\n", " ").replace("\r", "");
        User user = userService.getUserById(1L); // Should be changed later ofc
        collectionService.createCollection(user.getId(), title, category);
        return "redirect:/";
    }

    @GetMapping("/{collectionId}")
    public String view(@PathVariable Long collectionId, Model model) {
        Collection collection = collectionService.getCollectionById(collectionId);
        if (collection == null) {
            model.addAttribute("errorMessage", "Collection id not found");
            return "collection/error";
        }
        List<Flashcard> flashcards = flashcardService.getFlashcardsByCollectionId(collectionId);
        flashcards.sort(Comparator.comparing(Flashcard::getCreatedAt).reversed());
        model.addAttribute("collection", collection);
        model.addAttribute("flashcards", flashcards);
        return "collection/view";
    }

    @PutMapping("/{collectionId}")
    public ResponseEntity<Collection> edit(@PathVariable Long collectionId, @RequestParam("title") String title,
            @RequestParam("category") String category) {
        title = title.trim().replace("\n", " ").replace("\r", "");
        category = category.trim().replace("\n", " ").replace("\r", "");
        Collection collection = collectionService.getCollectionById(collectionId);
        if (collection == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Collection editedCollection = collectionService.editCollection(collection, title, category);
        return new ResponseEntity<>(editedCollection, HttpStatus.OK);
    }

    @DeleteMapping("/{collectionId}")
    public ResponseEntity<Collection> delete(@PathVariable Long collectionId) {
        Collection collection = collectionService.getCollectionById(collectionId);
        if (collection == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        collectionService.deleteCollection(collection);
        return new ResponseEntity<>(HttpStatus.OK);
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
        String view = "collection/play";
        Collection collection = collectionService.getCollectionById(collectionId);
        List<Flashcard> flashcards = collection != null ? flashcardService.getFlashcardsByCollectionId(collectionId)
                : null;
        List<Flashcard> unasweredFlashcards = flashcards != null ? flashcardService.getUnansweredFlashcards(flashcards)
                : null;

        if (collection == null) {
            model.addAttribute("errorMessage", "Collection id not found");
            view = "collection/error";
        } else if (flashcards == null || flashcards.isEmpty()) {
            model.addAttribute("errorMessage", "Collection has no flashcards");
            view = "collection/error";
        } else if (unasweredFlashcards == null || unasweredFlashcards.isEmpty()) {
            model.addAttribute("errorMessage",
                    "All the flashcards inside this collection are answered, <br/> please reset the collection to play again");
            view = "collection/error";
        } else {
            Random random = new Random();
            Flashcard randomFlashcard = unasweredFlashcards.get(random.nextInt(unasweredFlashcards.size()));
            model.addAttribute("flashcard", randomFlashcard);
        }

        return view;
    }

}
