package com.sharif.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.sharif.blog.entities.User;
import com.sharif.blog.exceptions.ResourceNotFoundException;
import com.sharif.blog.payloads.UserDto;
import com.sharif.blog.repository.UserRepo;
import com.sharif.blog.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User savedUser = userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User id", "cannot able to find", userId));
		user.setName(userDto.getName());
		user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setGender(userDto.getGender());
		user.setDateOfBirth(userDto.getDateOfBirth());
		user.setPhone(userDto.getPhone());
		User savedUser = this.userRepo.save(user);
		UserDto savedDto = this.userToDto(savedUser);
		return savedDto;
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = userRepo.findAll();
		List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public UserDto getUserById(int id) {
	    User user =	this.userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User id", "cannot able to find", id));
	    return this.userToDto(user);
	}

	@Override
	public void deleteUser(int id) {
		User user =  userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User id", "cannot able to find", id));
		userRepo.delete(user);
	}
	
	public User dtoToUser(UserDto userDto) {
		User user = new User();
		user.setId(userDto.getId());
		user.setAbout(userDto.getAbout());
		user.setDateOfBirth(userDto.getDateOfBirth());
		user.setEmail(userDto.getEmail());
		user.setGender(userDto.getGender());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		user.setPhone(userDto.getPhone());
		return user;	
	}
	
	public UserDto userToDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setAbout(user.getAbout());
		userDto.setDateOfBirth(user.getDateOfBirth());
		userDto.setEmail(user.getEmail());
		userDto.setGender(user.getGender());
		userDto.setName(user.getName());
		userDto.setPassword(user.getPassword());
		userDto.setPhone(user.getPhone());
		return userDto;	
	}
}
