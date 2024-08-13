package com.example.managementsoftware.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotEmpty(message = "Name cannot be null")
    @Size(min = 5 ,max = 10 , message = "name length must be more thane 5-10")
    @Column(columnDefinition = "varchar(10) NOT NULL")
    private String name;

    @NotEmpty(message = "UserName cannot be null")
    @Size(min = 5 , max = 16,message = ":user name length must be 5-16")
    @Column(columnDefinition = "varchar(16) UNIQUE NOT NULL ")
    private String username;

    @NotEmpty(message = "Password cannot be null")
    @Column(columnDefinition = "varchar(20) NOT NULL")
    private String password;

    @NotEmpty(message = "email cannot be null ")
    @Email(message = "Email must be valid format ")
    @Column(columnDefinition = "varchar(25) UNIQUE NOT NULL ")
    private String email;

    @NotEmpty(message = "Role cannot be empty ")
    @Pattern(regexp = "^(user|admin)$", message = " Position Must be either User or Admin only. ")
    //@Column(columnDefinition = "varchar(5) NOT NULL CHECK(role='user' or role='admin') ")
    private String role;

    @NotNull(message = "Age cannot be null")
    @Positive(message = "Age must be a integer")
    private int age;
}
