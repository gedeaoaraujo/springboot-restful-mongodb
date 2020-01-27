package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.domain.User;
import com.example.demo.dto.UserDTO;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.exception.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(String id) {
        Optional<User> user = repository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public User insert(User user) {
        return repository.insert(user);
    }

    public void delete(String id) {
        findById(id);
        repository.deleteById(id);
    }

    public User update(User user) {
        Optional<User> newUser = repository.findById(user.getId());
        updateUser(newUser, user);
        return repository.save(newUser.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado")));
    }

    private void updateUser(Optional<User> newUser, User user) {
        newUser.get().setName(user.getName());
        newUser.get().setEmail(user.getEmail());
    }

    public User fromDTO(UserDTO userDTO) {
        return new User(
            userDTO.getId(),
            userDTO.getName(), 
            userDTO.getEmail()
            );
    }
}        