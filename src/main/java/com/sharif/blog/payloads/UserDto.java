package com.sharif.blog.payloads;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	private int id;
	
	private String name;
	
	private String email;
	
	private String phone;
	
	private String password;
	
	private String about;
	
	private String gender;
	
	private String dateOfBirth;
}