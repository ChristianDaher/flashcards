package com.example.flashcards.interceptor;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.flashcards.model.Flashcard;
import com.example.flashcards.service.FlashcardService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// This is the flashcard interceptor, it handles all the routes that start with /collection/{collectionId}/flashcard/{flashcardId}
@Component
public class FlashcardInterceptor implements HandlerInterceptor {

    private final FlashcardService flashcardService;

    @Autowired
    public FlashcardInterceptor(FlashcardService flashcardService) {
        this.flashcardService = flashcardService;
    }

    // This method will forward the request to the error page with the error message
    private boolean forwardWithError(HttpServletRequest request, HttpServletResponse response, String collectionId,
            String errorMessage)
            throws ServletException, IOException {
        request.setAttribute("errorMessage", errorMessage);
        request.setAttribute("backLink", "/collection/" + collectionId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/error.jsp");
        dispatcher.forward(request, response);
        return false;
    }

    // This method will check if the collection id is valid once a request is made to a route that starts with /collection/{collectionId}/flashcard/{flashcardId}
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String path = request.getServletPath();
        String[] pathParts = path.split("/");
        String collectionId = pathParts[2];
        Long flashcardId;
        try {
            flashcardId = Long.parseLong(pathParts[4]);
        } catch (NumberFormatException e) {
            return forwardWithError(request, response, collectionId, "Flashcard id not a number");
        }
        Flashcard flashcard = flashcardService.getFlashcardById(flashcardId);
        if (flashcard == null) {
            return forwardWithError(request, response, collectionId, "Flashcard id not found");
        }
        return true;
    }

}