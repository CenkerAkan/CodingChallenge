package com.example.foundationProject.controller;

import java.util.*;

import com.example.foundationProject.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.foundationProject.repo.UserRepo;


@RestController
public class UserController {
    @Autowired
    private UserRepo userRepo;


    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers(){
        try {
            List<User> userList = new ArrayList<>();
            userRepo.findAll().forEach(userList::add);
            if (userList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(userList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/getUserById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        Optional<User> UserData = userRepo.findById(id);

        if(UserData.isPresent()){
            return new ResponseEntity<>(UserData.get(),HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/signUp")
    public ResponseEntity<User> signUp(@RequestBody User user){
        String hashedPassword = String.valueOf(user.getPassword().hashCode());
        user.setPassword(hashedPassword);

        User userObj = userRepo.save(user);
        return new ResponseEntity<>(userObj, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User User) {
        User user = userRepo.findByEmail(User.getEmail());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        int inputHashCode = User.getPassword().hashCode();

        String storedHashedPassword = user.getPassword();

        if (String.valueOf(inputHashCode).equals(storedHashedPassword)) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }

    @PutMapping("/updateUserById/{id}")
    public ResponseEntity<User> updateUserById(@PathVariable Long id, @RequestBody User newUserData) {
        Optional<User> oldUserDataOptional = userRepo.findById(id);

        if (oldUserDataOptional.isPresent()) {
            User oldUserData = oldUserDataOptional.get();
            oldUserData.setEmail(newUserData.getEmail());
            oldUserData.setPassword(String.valueOf(newUserData.getPassword().hashCode()));

            User updatedUserData = userRepo.save(oldUserData);

            return new ResponseEntity<>(updatedUserData, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteUserById/{id}")
    public ResponseEntity<Object> deleteUserById(@PathVariable Long id){
        userRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/domainCounts")
    public ResponseEntity<Map<String, Integer>> getDomainCounts() {
        List<User> users = userRepo.findAll();
        Map<String, Integer> domainCounts = new HashMap<>();

        for (User user : users) {
            String email = user.getEmail();
            String domain = extractDomain(email);
            domainCounts.put(domain, domainCounts.getOrDefault(domain, 0) + 1);
        }

        return new ResponseEntity<>(domainCounts, HttpStatus.OK);
    }

    private String extractDomain(String email) {
        return email.substring(email.lastIndexOf("@") + 1);
    }
}
