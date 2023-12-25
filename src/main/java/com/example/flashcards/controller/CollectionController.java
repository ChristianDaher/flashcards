package com.example.flashcards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.flashcards.model.User;
import com.example.flashcards.model.Collection;
import com.example.flashcards.service.CollectionService;
import com.example.flashcards.service.UserService;
import org.springframework.ui.Model;

@Controller
public class CollectionController {

    private final UserService userService;
    private final CollectionService collectionService;

    @Autowired
    public CollectionController(UserService userService, CollectionService collectionService) {
        this.userService = userService;
        this.collectionService = collectionService;
    }

    @GetMapping("/collection/create")
    public String create() {
        return "collection/create";
    }

    @PostMapping("/collection/create")
    public String store(@RequestParam("title") String title, @RequestParam("category") String category) {
        User user = userService.getUserById(1L); // Should be changed later ofc
        collectionService.createCollection(user.getId(), title, category);
        return "redirect:/";
    }

    @GetMapping("/collection/{collectionId}")
    public String view(@PathVariable Long collectionId, Model model) {
        Collection collection = collectionService.getCollectionById(collectionId);
        if (collection == null) {
            return "collection/not-found";
        }
        model.addAttribute("collection", collection);
        return "collection/view";
    }
}
