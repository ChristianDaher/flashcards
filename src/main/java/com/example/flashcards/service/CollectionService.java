package com.example.flashcards.service;

import com.example.flashcards.model.Collection;
import com.example.flashcards.model.Flashcard;
import com.example.flashcards.repository.CollectionRepository;
import com.example.flashcards.repository.FlashcardRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// This handles all the business logic related to collections
@Service
public class CollectionService {

    public final CollectionRepository collectionRepository;
    public final FlashcardRepository flashcardRepository;

    @Autowired
    public CollectionService(CollectionRepository collectionRepository, FlashcardRepository flashcardRepository) {
        this.collectionRepository = collectionRepository;
        this.flashcardRepository = flashcardRepository;
    }

    // This method will return all the collections of a user
    public List<Collection> getCollectionsByUserId(Long userId) {
        List<Collection> collections = collectionRepository.findAllByUserId(userId);
        for (Collection collection : collections) {
            int flashcardCount = flashcardRepository.countByCollectionId(collection.getId());
            collection.setFlashcardCount(flashcardCount);
        }
        return collections;
    }

    // This method will return grouped collections by category
    public Map<String, List<Collection>> groupCollectionsByCategory(List<Collection> collections) {
        return collections.stream()
                .collect(Collectors.groupingBy(collection -> collection.getCategory().toLowerCase(), TreeMap::new,
                        Collectors.toList()));
    }

    // This method will return a collection by id with its flashcard count, answered flashcard count and correctly answered flashcard count
    public Collection getCollectionById(Long id) {
        Collection collection = collectionRepository.findById(id).orElse(null);
        if (collection == null) {
            return null;
        }
        List<Flashcard> flashcards = flashcardRepository.findAllByCollectionId(id);
        int answeredFlashcardCount = 0;
        int correctlyAnsweredFlashcardCount = 0;
        for (Flashcard flashcard : flashcards) {
            if (flashcard.getAnsweredAt() != null) {
                answeredFlashcardCount++;
                if (flashcard.getIsCorrect() != null && flashcard.getIsCorrect()) {
                    correctlyAnsweredFlashcardCount++;
                }
            }
        }
        collection.setFlashcardCount(flashcards.size());
        collection.setAnsweredFlashcardCount(answeredFlashcardCount);
        collection.setCorrectlyAnsweredFlashcardCount(correctlyAnsweredFlashcardCount);
        return collection;
    }

    // This method will create a new collection
    public void createCollection(Long userId, String title, String category) {
        Collection collection = new Collection();
        collection.setUserId(userId);
        collection.setTitle(title);
        collection.setCategory(category.toLowerCase());
        collection.setCreatedAt(LocalDateTime.now());
        collectionRepository.save(collection);
    }

    // This method will edit a given collection
    public Collection editCollection(Collection collection, String title, String category) {
        collection.setTitle(title);
        collection.setCategory(category.toLowerCase());
        collectionRepository.save(collection);
        return collection;
    }

    // This method will delete a collection
    public void deleteCollection(Collection collection) {
        collectionRepository.delete(collection);
    }
}
