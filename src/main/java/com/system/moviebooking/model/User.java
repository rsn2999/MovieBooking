package com.system.moviebooking.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "users")
public class User {

    @Id
    private String userId;

    private String userName;

    private String userEmail;

    private String userPwd;

    private String role;
}