package com.springboot.myuser.controller;


import com.springboot.myuser.entity.UserMaster;
import com.springboot.myuser.exception.ResourceNotFoundException;
import com.springboot.myuser.repository.UserMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class UserMasterController {

    @Autowired
    private UserMasterRepository userMasterRepository;

    // create user rest api
    @PostMapping("/users")
    public String createUser(@RequestBody UserMaster userMaster) {
         userMasterRepository.save(userMaster);
         return "Hi " + userMaster.getFirstName() + ", You have been registered successfully!!! Welcome to our application...";
    }

    // get all users
    @GetMapping("/users")
    public List<UserMaster> getAllUsers() {
        return userMasterRepository.findAll();
    }

    // get user by id rest api
    @GetMapping("/users/{id}")
    public ResponseEntity<UserMaster> getUserById(@PathVariable Long id) {
        UserMaster userMaster = userMasterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
        return ResponseEntity.ok(userMaster);
    }

    // update employee rest api
    @PutMapping("/users/{id}")
    public ResponseEntity<UserMaster> updateUser(@PathVariable Long id, @RequestBody UserMaster userMaster) {
        UserMaster userMaster1 = userMasterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));

        userMaster1.setFirstName(userMaster.getFirstName());
        userMaster1.setLastName(userMaster.getLastName());
        userMaster1.setDepartment(userMaster.getDepartment());
        userMaster1.setDob(userMaster.getDob());
        userMaster1.setEmailId(userMaster.getEmailId());

        UserMaster updatedUser = userMasterRepository.save(userMaster1);
        return ResponseEntity.ok(updatedUser);
    }

    // delete employee rest api
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id) {
        UserMaster userMaster = userMasterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));

        userMasterRepository.delete(userMaster);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
