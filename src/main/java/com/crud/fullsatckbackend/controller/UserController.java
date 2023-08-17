package com.crud.fullsatckbackend.controller;

import com.crud.fullsatckbackend.exception.UserNotFoundException;
import com.crud.fullsatckbackend.model.User;
import com.crud.fullsatckbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173") // Isso permite que o frontend acesse o backend.
public class UserController {

  @Autowired // Isso indica que o Spring deve injetar uma instância de UserRepository aqui.
  private UserRepository userRepository; // Isso é semelhante a uma injeção de dependência.

  @PostMapping("/user")
  User newUser(@RequestBody User newUser) {
    return userRepository.save(newUser);
  }

  @GetMapping("/users")
  List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @GetMapping("/user/{id}")
  User getUserById(@PathVariable Long id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException(id));
  }

  @PutMapping("/user/{id}")
  User uppdateUser(@RequestBody User newUser, @PathVariable Long id) {
    return userRepository.findById(id)
        .map(user -> {
          user.setUsername(newUser.getUsername());
          user.setName(newUser.getName());
          user.setEmail(newUser.getEmail());
          return userRepository.save(user);
        })
        .orElseGet(() -> {
          newUser.setId(id);
          return userRepository.save(newUser);
        });
  }

  @DeleteMapping("/user/{id}")
  String deleteUser(@PathVariable Long id) {
    if (!userRepository.existsById(id)) {
      throw new UserNotFoundException(id);
    }
    userRepository.deleteById(id);
    return "User deleted successfully";
  }

}
