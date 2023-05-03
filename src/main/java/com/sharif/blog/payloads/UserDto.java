package com.sharif.blog.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {


	private int id;

	@NotEmpty
	@Size(min = 3,message = "Username must not be empty abd must be  atleast above 4 characters")
	private String name;

	@Email
	private String email;

	@NotNull
	private String phone;

	@NotBlank
	@Size(min = 6,max = 25,message = "password must be above6 and below 25 chars")
	private String password;
	
	private String about;
	
	private String gender;
	
	private String dateOfBirth;
}
