package com.sharif.blog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sharif.blog.payloads.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto userDto);
	
	UserDto updateUser(UserDto userDto, Integer userId);
	
	List<UserDto> getAllUsers();
	
	UserDto getUserById(int id);
	
	void deleteUser(int id);
}
