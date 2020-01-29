package com.example.demo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import com.example.demo.domain.Post;
import com.example.demo.domain.User;
import com.example.demo.dto.AuthorDTO;
import com.example.demo.dto.CommentDTO;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Gray", "bob@gmail.com");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post post1 = new Post(null, simpleDateFormat.parse("21/03/2018"), "Partiu Viagem", "Vou viajar para SãoPaulo, abraços", new AuthorDTO(maria));
        Post post2 = new Post(null, simpleDateFormat.parse("21/03/2018"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));

        CommentDTO c1 = new CommentDTO("Boa viagem mano!", simpleDateFormat.parse("21/03/2018"), new AuthorDTO(alex));
        CommentDTO c2 = new CommentDTO("Aproveite", simpleDateFormat.parse("22/03/2018"), new AuthorDTO(bob));
        CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", simpleDateFormat.parse("23/03/2018"), new AuthorDTO(alex));

        post1.getComments().addAll(Arrays.asList(c1, c2));
        post2.getComments().addAll(Arrays.asList(c3));

        postRepository.saveAll(Arrays.asList(post1, post2));

        maria.getPosts().addAll(Arrays.asList(post1, post2));
        userRepository.save(maria);
    }
}