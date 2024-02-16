package com.spring.aws.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.aws.entity.User;

public interface UserRepository  extends JpaRepository<User, Integer>{
    // hello raja
}
