package com.example.demo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.demo.domain.Post;
import com.example.demo.repository.PostRepository;
import com.example.demo.services.exception.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public Post findById(String id) {
        Optional<Post> post = repository.findById(id);
        return post.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public List<Post> findByTitle(String text){
        return repository.searchTitle(text);
    }

    public List<Post> fullSearch(String text, Date minimumDate, Date maximumDate){
        maximumDate = new Date(maximumDate.getTime() + 24 * 60 * 60 * 1000);
        return repository.fullSearch(text, minimumDate, maximumDate);
    }
}        