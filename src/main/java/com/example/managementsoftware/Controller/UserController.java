package com.example.managementsoftware.Controller;


import com.example.managementsoftware.Model.User;
import com.example.managementsoftware.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;


    // get all users
    @GetMapping("/get")
    public ResponseEntity getUser() {
        List<User> users = userService.getUsers();
        return ResponseEntity.status(200).body(users);
    }


    // add new users
    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user , Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body("User added successfully");
    }


    // update user by id
    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id ,@Valid @RequestBody User user , Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.UpdateUser(id,user);
        return ResponseEntity.status(200).body("User updated successfully");
    }


    // delete user by id
  @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.status(200).body("User deleted successfully");
  }


  @GetMapping("/login/{username}/{password}")
  public ResponseEntity login(@PathVariable String username, @PathVariable String password) {
        if(userService.CheckUsernameAndPassword(username,password) == null){
            return ResponseEntity.status(400).body("Username or password is incorrect");
        }
        return ResponseEntity.status(200).body(userService.CheckUsernameAndPassword(username,password));
  }


    @GetMapping("/email/{email}")//Get user by email
    public ResponseEntity getUserByEmail(@PathVariable String email) {
        return ResponseEntity.status(200).body(userService.SearchUserByEmail(email));
    }


    @GetMapping("/role/{role}")//Get All users with specific role
    public ResponseEntity getUserByRole(@PathVariable String role) {
        List<User> users = userService.SearchUserByRole(role);

        if(users.isEmpty()){
            return ResponseEntity.status(400).body("Not Found User with Specific role " );
        }
        return ResponseEntity.status(200).body(users);
    }

    @GetMapping("/age/{age}")//Get All users with specific age or above
    public ResponseEntity getUserByAge(@PathVariable Integer age) {
        List<User> users=userService.SearchAgeOrAbove(age);
        if(users.isEmpty()){
            return ResponseEntity.status(400).body("Not Found User with Specific age and above ");
        }
        return ResponseEntity.status(200).body(users);
    }

}
