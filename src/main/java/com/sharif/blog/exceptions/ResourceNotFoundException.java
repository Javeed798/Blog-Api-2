package com.sharif.blog.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;

public class ResourceNotFoundException extends RuntimeException{
	
	String resouceName;
	
	String fieldName;
	
	long fieldValue;


	public ResourceNotFoundException(String resouceName, String fieldName, long fieldValue) {
		super(String.format("%s is not found with %s : %l doesnt find",resouceName,fieldName,fieldValue));
		this.resouceName = resouceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
}
