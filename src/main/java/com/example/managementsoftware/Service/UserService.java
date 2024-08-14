package com.example.managementsoftware.Service;


import com.example.managementsoftware.Api.ApiException;
import com.example.managementsoftware.Model.User;
import com.example.managementsoftware.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll(); // return all coffee database as array
    }


    public void addUser(User coffee) {
        userRepository.save(coffee); // add
    }


    public void UpdateUser(Integer id,User user) {
        User u=userRepository.findUserById(id);

        if(u ==null) {
            throw new ApiException("User not found");
        }
        u.setName(user.getName());
        u.setUsername(user.getUsername());
        u.setEmail(user.getEmail());
        u.setPassword(user.getPassword());
        u.setAge(user.getAge());
        u.setRole(user.getRole());

        userRepository.save(u);

    }


    public void deleteUser(Integer id) {
        User u=userRepository.findUserById(id);
        if(u == null){
            throw new ApiException("User not found");
        }
        userRepository.deleteById(id);
    }

    //Check if username and password are correct
    public User CheckUsernameAndPassword(String email, String password) {
        User u=userRepository.findUserByUsernameAndPassword(email, password);
        if(u == null){
        throw new ApiException("User not found");
        }
        return u;
    }

     //Get user by email
    public User SearchUserByEmail(String email) {
        User u=userRepository.findUserByEmail(email);
        if(u == null){
            throw new ApiException("User not found");
        }
        return u;
    }


    //Get All users with specific role
    public List<User> SearchUserByRole(String role) {
        List<User> userList=userRepository.ListUsersByRole(role);

        if(userList == null){
             throw new ApiException("User not found");
        }
        return userList;
    }


    //Get All users with specific age or above
   public List<User> SearchAgeOrAbove(int age) {
        List<User> users=userRepository.SearchAgeOrAbove(age);
        if(users == null){
          throw new ApiException("User not found");
        }
        return users;
   }


}
