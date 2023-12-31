package com.example.flashcards.service;

import com.example.flashcards.model.User;
import com.example.flashcards.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// This handles all the business logic related to users
@Service
public class UserService {

    public final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // This method returns user by id
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElse(null);
    }
}
