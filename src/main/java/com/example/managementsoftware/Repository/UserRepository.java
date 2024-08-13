package com.example.managementsoftware.Repository;

import com.example.managementsoftware.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // FindBy methods
     User findUserById(int id);// get user by id

 User findUserByEmail(String email); //Get user by email

 User findUserByUsernameAndPassword(String username, String password);//Check if username and password are correct


 // ------ JPQL -----

     @Query("select u from User u where u.role=?1 ")//Get All users with specific role
     List<User> ListUsersByRole(String role);

     @Query("select u from User u where u.age>=?1")//Get All users with specific age or above
     List<User> SearchAgeOrAbove(int age);


}
