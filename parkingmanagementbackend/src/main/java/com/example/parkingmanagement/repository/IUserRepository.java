package com.example.parkingmanagement.repository;

import com.example.parkingmanagement.model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {
    User findByEmailAndPassword(String email, String password);
    
    User findByEmail(String email);
    
    List<User> findAll();
    Optional<User> findById(Long id);
    
    
    void deleteById(Long id);
}
