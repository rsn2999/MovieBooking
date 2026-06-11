package com.system.moviebooking.repository;

import com.system.moviebooking.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
    User findByUserEmail(String userEmail);
}
