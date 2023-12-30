package com.example.flashcards.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.flashcards.interceptor.CollectionInterceptor;
import com.example.flashcards.interceptor.FlashcardInterceptor;

@Component
public class InterceptorAppConfig implements WebMvcConfigurer {

    @Autowired
    CollectionInterceptor collectionInterceptor;
    @Autowired
    FlashcardInterceptor flashcardInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registery) {
        registery.addInterceptor(collectionInterceptor).addPathPatterns("/collection/{collectionId}/**")
                .excludePathPatterns("/collection/create");
        registery.addInterceptor(flashcardInterceptor)
                .addPathPatterns("/collection/{collectionId}/flashcard/{flashcardId}/**")
                .excludePathPatterns("/collection/{collectionId}/flashcard/create");
    }
}
