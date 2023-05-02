package com.sharif.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sharif.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
