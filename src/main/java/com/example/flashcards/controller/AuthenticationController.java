package com.example.flashcards.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthenticationController {

    @GetMapping("/login")
    public String viewLogin() {
        return "authentication/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        // User user = (User) userService.loadUserByUsername(email);
        // if (user == null || !userService.isValidUser(user, password)) {
        //     model.addAttribute("errorMessage", "Wrong credentials");
        //     return "authentication/login";
        // }
        // Authentication authentication = authenticationManager
        //         .authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        // SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:/";
    }

    @GetMapping("/register")
    public String viewRegister() {
        return "authentication/register";
    }

    @PostMapping("/register")
    public String register() {
        return "redirect:/";
    }
}
