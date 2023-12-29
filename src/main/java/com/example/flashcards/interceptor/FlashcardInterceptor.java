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

@Component
public class FlashcardInterceptor implements HandlerInterceptor {

    private final FlashcardService flashcardService;

    @Autowired
    public FlashcardInterceptor(FlashcardService flashcardService) {
        this.flashcardService = flashcardService;
    }

    private boolean forwardWithError(HttpServletRequest request, HttpServletResponse response, String collectionId,
            String errorMessage)
            throws ServletException, IOException {
        request.setAttribute("errorMessage", errorMessage);
        request.setAttribute("backLink", "/collection/" + collectionId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/error.jsp");
        dispatcher.forward(request, response);
        return false;
    }

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