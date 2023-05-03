package com.sharif.blog.controllers;

import com.sharif.blog.entities.User;
import com.sharif.blog.payloads.ApiResponse;
import com.sharif.blog.payloads.UserDto;
import com.sharif.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto userDto1 = this.userService.createUser(userDto);
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> allUsers= this.userService.getAllUsers();
        return new ResponseEntity<>(allUsers,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id){
        UserDto userDto = this.userService.getUserById(id);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer id) {
        UserDto updatedUser = this.userService.updateUser(userDto,id);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer id){
        this.userService.deleteUser(id);
        return new ResponseEntity<>(new ApiResponse("Deleted Successfully",true, new Date()),HttpStatus.OK);
    }
}
