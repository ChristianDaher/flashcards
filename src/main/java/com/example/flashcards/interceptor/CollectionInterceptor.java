package com.example.flashcards.interceptor;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.flashcards.model.Collection;
import com.example.flashcards.service.CollectionService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CollectionInterceptor implements HandlerInterceptor {

    private final CollectionService collectionService;

    @Autowired
    public CollectionInterceptor(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    private boolean forwardWithError(HttpServletRequest request, HttpServletResponse response, String errorMessage)
            throws ServletException, IOException {
        request.setAttribute("errorMessage", errorMessage);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/error.jsp");
        dispatcher.forward(request, response);
        return false;
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String path = request.getServletPath();
        String[] pathParts = path.split("/");
        Long collectionId;
        try {
            collectionId = Long.parseLong(pathParts[2]);
        } catch (NumberFormatException e) {
            return forwardWithError(request, response, "Collection id not a number");
        }
        Collection collection = collectionService.getCollectionById(collectionId);
        if (collection == null) {
            return forwardWithError(request, response, "Collection id not found");
        }
        return true;
    }

}