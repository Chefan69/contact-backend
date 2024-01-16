package com.codersbay.backendfinalproject.controller;


import com.codersbay.backendfinalproject.converter.DTOConverter;
import com.codersbay.backendfinalproject.dto.UserDTO;
import com.codersbay.backendfinalproject.exception.ContactNotFoundException;
import com.codersbay.backendfinalproject.exception.UserNotFoundException;
import com.codersbay.backendfinalproject.model.Contact;
import com.codersbay.backendfinalproject.model.User;
import com.codersbay.backendfinalproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.codersbay.backendfinalproject.converter.DTOConverter.convertToUserEntity;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository userRepository;

// new testing (works)
    private boolean isUsernameAvailable(String username) {
        return userRepository.findByUsername(username) == null;
    }

/*

    // SignUp for SingUn Up for User (User Account Creation) 111
    @PostMapping("/signup")
    public UserDTO signUp(@RequestBody UserDTO userDTO) {
        User user = convertToUserEntity(userDTO);
        User savedUser = userRepository.save(user);
        return DTOConverter.convertToUserDTO(savedUser);
    }

*/


    @GetMapping("/users")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }



    //signIn method for Sign In for specific User
    @PostMapping("/signin")
    public User signIn(@RequestBody UserDTO user) {
        User existingUser = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if(existingUser == null) {
            throw new UserNotFoundException();
        }
        return existingUser;
    }




    // retrieve user with specific userId
    @GetMapping("/users/{userId}")
    public User getUserByUserId(@PathVariable Long userId) {
        User userWithUserId = userRepository.findById(userId).get();
        return userWithUserId;
    }






    // deleteUser method to Delete User Account of a User with a specific user id
    @DeleteMapping("/users/{userId}")
    String deleteUser(@PathVariable Long userId) {
        if(!userRepository.existsById(userId)) {
            throw new UserNotFoundException();
        }
        userRepository.deleteById(userId);
        return "User with id " + userId + " has been deleted successfully.";
    }


/*


    // Check if Username is already given 11111
    @GetMapping("/check-username/{username}")
    public ResponseEntity<Map<String, Boolean>> checkUsernameAvailability(@PathVariable String username) {
        boolean isUsernameAvailable = userRepository.findByUsername(username) == null;
        Map<String, Boolean> response = new HashMap<>();
        response.put("available", isUsernameAvailable);
        return ResponseEntity.ok(response);
    }

*/






/*
    // not allowed
    @DeleteMapping("/users/deleteAll")
    String deleteAllUsers(){
        userRepository.deleteAll();
        return "All users deleted";
    }
*/


// for username availability check
    @GetMapping("/check-username/{username}")
    public ResponseEntity<Map<String, Boolean>> checkUsernameAvailability(@PathVariable String username) {
        boolean isUsernameAvailable = isUsernameAvailable(username);
        Map<String, Boolean> response = new HashMap<>();
        response.put("available", isUsernameAvailable);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserDTO userDTO) {
        if (!isUsernameAvailable(userDTO.getUsername())) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Benutzername bereits vergeben");
            return ResponseEntity.badRequest().body(response);
        }

        User user = convertToUserEntity(userDTO);
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(DTOConverter.convertToUserDTO(savedUser));
    }




}
