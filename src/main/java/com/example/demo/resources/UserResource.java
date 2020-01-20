package com.example.demo.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.domain.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

   @GetMapping
   public ResponseEntity<List<User>> findAll() {
        List<User> list = new ArrayList<>();
        list.add(new User("1", "Maria Brown", "maria@gmail.com"));
        list.add(new User("2", "Jack Sparrow", "sparow@gmail.com"));
        return ResponseEntity.ok().body(list);
   }
    
}