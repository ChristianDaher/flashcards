package com.example.flashcards.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.flashcards.interceptor.CollectionInterceptor;
import com.example.flashcards.interceptor.FlashcardInterceptor;

// This is the middleware file, all requests will go through here first
@Component
public class InterceptorAppConfig implements WebMvcConfigurer {

    @Autowired
    CollectionInterceptor collectionInterceptor;
    @Autowired
    FlashcardInterceptor flashcardInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registery) {
        registery.addInterceptor(collectionInterceptor).addPathPatterns("/collection/{collectionId}/**") // Add the interceptor to all routes that start with /collection/{collectionId}
                .excludePathPatterns("/collection/create"); // Exclude the create route from the interceptor
        registery.addInterceptor(flashcardInterceptor)
                .addPathPatterns("/collection/{collectionId}/flashcard/{flashcardId}/**") // Add the interceptor to all routes that start with /collection/{collectionId}/flashcard/{flashcardId}
                .excludePathPatterns("/collection/{collectionId}/flashcard/create"); // Exclude the create route from the interceptor
    }
}
