package com.example.parkingmanagement.service;

import com.example.parkingmanagement.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    User findByEmail(String email);
    List<User> findAll();
    User saveUser(User user);
    void deleteUser(Long id);
    User updateUser(User user);
    Optional<User> getById(Long id);
	User findByEmailAndPassword(String email, String password);
    
}
