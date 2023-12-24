package com.example.flashcards.controller;

import com.example.flashcards.model.Collection;
import com.example.flashcards.model.User;
import com.example.flashcards.service.CollectionService;
import com.example.flashcards.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private final UserService userService;
    private final CollectionService collectionService;

    @Autowired
    public MainController(UserService userService, CollectionService collectionService) {
        this.userService = userService;
        this.collectionService = collectionService;
    }

    @GetMapping("/")
    public String index(Model model) {
        // Change id to authenticated user id after implementing authentication
        User user = userService.getUserById(1L);
        List<Collection> collections = collectionService.getCollectionsByUserId(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("collections", collections);
        return "index";
    }
}
