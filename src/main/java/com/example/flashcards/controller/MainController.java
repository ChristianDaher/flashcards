package com.example.flashcards.controller;

import com.example.flashcards.model.Collection;
import com.example.flashcards.model.User;
import com.example.flashcards.service.CollectionService;
import com.example.flashcards.service.UserService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// This is the main controller, it handles the main route
@Controller
public class MainController {

    private final UserService userService;
    private final CollectionService collectionService;

    @Autowired
    public MainController(UserService userService, CollectionService collectionService) {
        this.userService = userService;
        this.collectionService = collectionService;
    }

    // Route to view the main page,  it will show all the collections of the user
    @GetMapping(value = { "/", "/index", "/home", "/collections" })
    public String index(Model model) {
        // Change id to authenticated user id after implementing authentication
        User user = userService.getUserById(1L);
        List<Collection> collections = collectionService.getCollectionsByUserId(user.getId());
        Map<String, List<Collection>> groupedCollections = collectionService.groupCollectionsByCategory(collections);
        model.addAttribute("user", user);
        model.addAttribute("groupedCollections", groupedCollections);
        return "index";
    }
}
