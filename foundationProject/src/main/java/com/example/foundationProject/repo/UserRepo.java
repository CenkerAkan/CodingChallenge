package com.example.foundationProject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.foundationProject.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
    User findByEmail(String email);
}
