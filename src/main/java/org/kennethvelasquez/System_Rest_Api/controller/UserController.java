package org.kennethvelasquez.System_Rest_Api.controller;

import org.kennethvelasquez.System_Rest_Api.model.User;
import org.kennethvelasquez.System_Rest_Api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/user/")
public class UserController implements UserService {
    private static ArrayList<User>listUsers = new ArrayList<User>();

    @PostMapping("/")
    @Override
    public ResponseEntity addUser(@RequestBody User user) {
        listUsers.add(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/")
    @Override
    public ResponseEntity getAllUsers() {
        return ResponseEntity.ok(listUsers.stream().toList());
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity getUserById(@PathVariable Long id) {
        Optional<User> user = listUsers.stream().filter(userFind -> userFind.getId()==id).findFirst();
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity updateUser(@RequestBody User user, @PathVariable Long id) {
        Optional<User> userSearch = listUsers.stream().filter(userFind -> userFind.getId()==id).findFirst();
        if (userSearch.isPresent()) {
            userSearch.get().setName(user.getName());
            userSearch.get().setLastname(user.getLastname());
            userSearch.get().setEmail(user.getEmail());
            userSearch.get().setAge(user.getAge());
            return ResponseEntity.ok(userSearch.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity deleteUserById(@PathVariable Long id) {
        Optional <User> userSearch = listUsers.stream().filter(userFind -> userFind.getId()==id).findFirst();
        if(userSearch.isPresent()) {
            listUsers.remove(userSearch.get());
            return ResponseEntity.ok("Se ha eliminado el usuario: "+userSearch.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
