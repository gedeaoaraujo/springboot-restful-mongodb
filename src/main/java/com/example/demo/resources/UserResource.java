package com.example.demo.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.demo.domain.User;
import com.example.demo.dto.UserDTO;
import com.example.demo.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

     @Autowired
     private UserService service;

     @GetMapping
     public ResponseEntity<List<UserDTO>> findAll(){
          List<User> list = service.findAll();
          List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
          return ResponseEntity.ok().body(listDto);
     }

     @RequestMapping(value = "/{id}", method = RequestMethod.GET)
     public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        User object = service.findById(id);
        return ResponseEntity.ok().body(new UserDTO(object));
   }
    
}