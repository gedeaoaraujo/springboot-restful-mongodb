package com.example.demo.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.domain.Post;
import com.example.demo.domain.User;
import com.example.demo.dto.UserDTO;
import com.example.demo.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

     @GetMapping(value = "/{id}")
     public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        User object = service.findById(id);
        return ResponseEntity.ok().body(new UserDTO(object));
     }

     @PostMapping
     public ResponseEntity<Void> insert(@RequestBody UserDTO userDTO) {
        User object = service.fromDTO(userDTO);
        object = service.insert(object);
        URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(object.getId())
                    .toUri();

        return ResponseEntity.created(uri).build();
     }
    
     @DeleteMapping(value = "/{id}")
     public ResponseEntity<UserDTO> deleteById(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
     }

     @PutMapping(value = "/{id}")
     public ResponseEntity<Void> update(@RequestBody UserDTO userDTO, @PathVariable String id) {
        User object = service.fromDTO(userDTO);
        object.setId(id);
        object = service.update(object);

        return ResponseEntity.noContent().build();
     }

     @GetMapping(value = "/{id}/posts")
     public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
        User user = service.findById(id);
        return ResponseEntity.ok().body(user.getPosts());
        
     } 
}