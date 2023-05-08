package com.sharif.blog.entities;

import javax.persistence.*;

import org.hibernate.annotations.GeneratorType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="name", nullable = false)
	private String name;
	
	private String email;
	
	private String phone;
	
	private String password;
	
	private String about;
	
	private String gender;
	
	private String dateOfBirth;

	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy="user")
	private List<Post> posts = new ArrayList<>();

}
