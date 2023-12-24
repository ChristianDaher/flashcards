package com.example.flashcards.service;

import com.example.flashcards.model.Collection;
import com.example.flashcards.repository.CollectionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectionService {

    public final CollectionRepository collectionRepository;

    @Autowired
    public CollectionService(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    public List<Collection> getCollectionsByUserId(Long userId) {
        return collectionRepository.findAllByUserId(userId);
    }
}
